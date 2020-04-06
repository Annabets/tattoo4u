package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.controller.controllerExceptionExtn.IncorrectDataInputException;
import by.bsuir.tattoo4u.dto.request.StudioRegistrationRequestDto;
import by.bsuir.tattoo4u.dto.response.StudioResponseDto;
import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.service.ServiceException;
import by.bsuir.tattoo4u.service.StudioService;
import by.bsuir.tattoo4u.service.TokenService;
import by.bsuir.tattoo4u.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
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

    @Autowired
    public StudioController(StudioService studioService, UserService userService, TokenService tokenService) {
        this.studioService = studioService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/studio")
    @PreAuthorize("hasAuthority('MASTER')")
    public ResponseEntity<?> registerStudio(@RequestBody StudioRegistrationRequestDto requestDto,
                                            @RequestHeader("Authorization") String token) {
        if (requestDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Studio studio = requestDto.getStudio();
        try {
            token = token.substring(7);
            String ownerName = tokenService.getUsername(token);
            User owner = userService.getByUsername(ownerName);

            studio.setOwner(owner);
            studioService.add(studio);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/studio")
    public ResponseEntity<?> takeStudios(@RequestParam(defaultValue = "") String name,
            @PageableDefault(sort = {"rating"}, direction = Sort.Direction.DESC) Pageable pageable) {
        List<StudioResponseDto> studios;
        try {
            if(name == null || name.equals("")) {
                studios = studioService.takeStudios(pageable);
            } else {
                studios = studioService.takeByName(name, pageable);
            }
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(studios, HttpStatus.OK);
    }
}
