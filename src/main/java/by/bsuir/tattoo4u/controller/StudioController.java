package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.request.AddingMasterRequestDto;
import by.bsuir.tattoo4u.dto.request.StudioFeedbackRequestDto;
import by.bsuir.tattoo4u.dto.request.StudioRegistrationRequestDto;
import by.bsuir.tattoo4u.dto.response.*;
import by.bsuir.tattoo4u.entity.*;
import by.bsuir.tattoo4u.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "api")
public class StudioController {
    private final StudioService studioService;
    private final UserService userService;
    private final TokenService tokenService;
    private final PhotoService photoService;

    @Autowired
    public StudioController(
            StudioService studioService,
            UserService userService,
            TokenService tokenService,
            PhotoService photoService
    ) {
        this.studioService = studioService;
        this.userService = userService;
        this.tokenService = tokenService;
        this.photoService = photoService;
    }

    @PostMapping("/studio")
    @PreAuthorize("hasAuthority('MASTER')")
    public ResponseEntity<?> registerStudio(
            @ModelAttribute StudioRegistrationRequestDto requestDto,
            @RequestHeader("Authorization") String token
    ) {

        if (requestDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            token = token.substring(7);
            String ownerName = tokenService.getUsername(token);
            User owner = userService.getByUsername(ownerName);

            PhotoUpload photoUpload = new PhotoUpload(requestDto.getFile());
            Photo photo = photoService.save(photoUpload);

            Studio studio = requestDto.getStudio();
            studio.setOwner(owner);
            owner.setStudio(studio);
            studio.setPhoto(photo);

            studioService.save(studio);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/studio")
    @PreAuthorize("hasAnyAuthority('MASTER')")
    public ResponseEntity<?> removeStudio(@RequestParam Long studioId) {
        try {
            studioService.removeStudio(studioId);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/studio")
    public ResponseEntity<?> takeStudioById(@RequestParam Long studioId,
                                            @RequestHeader("Authorization") String bearerToken) {
        StudioWithMastersResponseDto studio;

        User user = null;
        if(bearerToken != null) {
            String username = tokenService.getUsername(tokenService.clearBearerToken(bearerToken));
            user = userService.getByUsername(username);
        }
        try {
            StudioResponseDto tempStudio;
            if(user == null) {
                tempStudio = studioService.takeStudioResponseDtoById(studioId);
            } else {
                tempStudio = studioService.takeStudioByIdWithFavourites(studioId, user);
            }
            List<MasterResponseDto> masters = studioService.takeMasters(studioId);

            studio = new StudioWithMastersResponseDto(tempStudio, masters);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(studio, HttpStatus.OK);
    }

    @GetMapping("/studios")
    public ResponseEntity<?> takeStudios(@RequestParam(defaultValue = "") String name,
                                         @PageableDefault(sort = {"rating"}, direction = Sort.Direction.DESC) Pageable pageable,
                                         @RequestHeader("Authorization") String bearerToken) {
        List<StudioResponseDto> studios;
        User user = null;
        if(bearerToken != null) {
            String username = tokenService.getUsername(tokenService.clearBearerToken(bearerToken));
            user = userService.getByUsername(username);
        }
        try {
            if (name == null || name.equals("")) {
                if(user != null) {
                    studios = studioService.takeStudiosWithFavourites(pageable, user);
                } else {
                    studios = studioService.takeStudios(pageable);
                }
            } else {
                if(user != null) {
                    studios = studioService.takeStudiosByNameWithFavourites(name, pageable, user);
                } else {
                    studios = studioService.takeByName(name, pageable);
                }
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(studios, HttpStatus.OK);
    }

    @PostMapping("/master")
    @PreAuthorize("hasAuthority('MASTER')")
    public ResponseEntity<?> addMaster(@RequestBody AddingMasterRequestDto requestDto) {
        User master = userService.getById(requestDto.getMasterId());
        try {
            studioService.addMaster(master, requestDto.getStudioId());
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/mastersInStudio")
    public ResponseEntity<?> takeMastersInStudio(@RequestParam Long studioId) {
        List<MasterResponseDto> masters;
        try {
            masters = studioService.takeMasters(studioId);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(masters, HttpStatus.OK);
    }

    @DeleteMapping("/master")
    @PreAuthorize("hasAuthority('MASTER')")
    public ResponseEntity<?> dismissMasterFromStudio(@RequestBody AddingMasterRequestDto requestDto) {
        User master = userService.getById(requestDto.getMasterId());
        try {
            studioService.removeMaster(master, requestDto.getStudioId());
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/feedback")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> addFeedback(@RequestBody StudioFeedbackRequestDto requestDto,
                                         @RequestHeader("Authorization") String bearerToken) {
        StudioFeedback studioFeedback = requestDto.getStudioFeedback();
        String username = tokenService.getUsername(tokenService.clearBearerToken(bearerToken));

        User user = userService.getByUsername(username);
        StudioWithRatingAndFeedbackResponseDto feedbackResponseDto;
        try {
            Studio studio = studioService.takeStudioById(requestDto.getStudioId());
            studioFeedback.setStudio(studio);
            studioFeedback.setUsername(username);
            studioFeedback.setUserPhoto(user.getPhoto().getUrl());

            studioService.addFeedback(studioFeedback);

            List<StudioFeedbackResponseDto> feedbacks = studioService.takeStudiosFeedbacks(requestDto.getStudioId());
            feedbackResponseDto = new StudioWithRatingAndFeedbackResponseDto(studio.getRating(), feedbacks);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(feedbackResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/feedback")
    public ResponseEntity<?> getFeedback(@RequestParam Long id) {
        List<StudioFeedbackResponseDto> feedbacks;
        try {
            feedbacks = studioService.takeStudiosFeedbacks(id);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(feedbacks, HttpStatus.CREATED);
    }
}
