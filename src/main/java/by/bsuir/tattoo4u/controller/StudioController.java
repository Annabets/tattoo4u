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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "api")
public class StudioController {
    private final StudioService studioService;
    private final UserService userService;
    private final TokenService tokenService;
    private HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    public StudioController(StudioService studioService, UserService userService, TokenService tokenService) {
        this.studioService = studioService;
        this.userService = userService;
        this.tokenService = tokenService;
        httpHeaders.set("Access-Control-Allow-Origin", "*");
    }

    @PostMapping("/studioReg")
    public ResponseEntity<?> registerStudio(@RequestBody StudioRegistrationRequestDto requestDto,
                                            @RequestHeader HttpHeaders header) {
        if (requestDto == null) {
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        Studio studio = requestDto.getStudio();
        try {
            String token = header.getFirst("authorization");
            token = token.substring(7);
            String ownerName = tokenService.getUsername(token);
            User owner = userService.getByUsername(ownerName);

            studio.setOwner(owner);
            studioService.add(studio);
        } catch (ServiceException ex) {

        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/getStudios")
    public ResponseEntity<?> takeStudios(
            @PageableDefault(sort = {"rating"}, direction = Sort.Direction.DESC) Pageable pageable) {
        List<StudioResponseDto> studios;
        try {
            studios = studioService.takeStudios(pageable);
            return new ResponseEntity<>(studios, httpHeaders, HttpStatus.OK);
        } catch (ServiceException ex) {
            throw new IncorrectDataInputException(ex);
        }
    }

    @GetMapping("/getStudiosByName")
    public ResponseEntity<?> takeStudiosByName(@RequestBody String request, @PageableDefault Pageable pageable) {
        List<StudioResponseDto> studios;
        try {
            JSONObject json = new JSONObject(request);
            String name = json.getString("name");

            studios = studioService.takeByName(name, pageable);
            return new ResponseEntity<>(studios, httpHeaders, HttpStatus.OK);
        } catch (ServiceException ex) {
            throw new IncorrectDataInputException(ex);
        } catch (JSONException ex) {
            throw new IncorrectDataInputException(ex);
        }
    }
}
