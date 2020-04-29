package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.request.MasterCommentRequestDto;
import by.bsuir.tattoo4u.dto.response.MasterCommentAfterAddingResponseDto;
import by.bsuir.tattoo4u.dto.response.MasterCommentResponseDto;
import by.bsuir.tattoo4u.dto.response.MasterResponseDto;
import by.bsuir.tattoo4u.dto.response.MasterWithCheckingResponseDto;
import by.bsuir.tattoo4u.entity.*;
import by.bsuir.tattoo4u.service.MasterCommentService;
import by.bsuir.tattoo4u.service.MasterService;
import by.bsuir.tattoo4u.service.TokenService;
import by.bsuir.tattoo4u.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/masters")
public class MasterController {

    private MasterService masterService;
    private UserService userService;
    private MasterCommentService masterCommentService;
    private TokenService tokenService;

    public MasterController(MasterService masterService, UserService userService, MasterCommentService masterCommentService, TokenService tokenService) {
        this.masterService = masterService;
        this.userService = userService;
        this.masterCommentService = masterCommentService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<?> getMasterByUsername(@RequestHeader(value = "Authorization", required = false) String bearerToken, @PageableDefault(sort = "rating", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(required = false) String name, @RequestParam(required = false) String page) {

        List<Master> masterList = new ArrayList<>();
        if (name == null) {
            masterList = masterService.getAll(pageable);
        } else {
            masterList = masterService.getAllUsernameContain(name, pageable);
        }

        if(bearerToken!=null) {
            String token = tokenService.clearBearerToken(bearerToken);
            User currentUser = userService.getByUsername(tokenService.getUsername(token));

            List<MasterWithCheckingResponseDto> masterResponseDto = new ArrayList<>();
            for (Master master : masterList) {
                boolean containsThisMaster = new ArrayList<>(currentUser.getFavourites()).contains(master);
                masterResponseDto.add(new MasterWithCheckingResponseDto(master, containsThisMaster));
            }

            return new ResponseEntity<>(masterResponseDto, HttpStatus.OK);
        }else {
            List<MasterResponseDto> masterResponseDto = new ArrayList<>();
            for (Master master : masterList) {
                masterResponseDto.add(new MasterResponseDto(master));
            }

            return new ResponseEntity<>(masterResponseDto, HttpStatus.OK);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMasterById(@RequestHeader(value = "Authorization", required = false) String bearerToken, @PathVariable("id") User user) {

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(bearerToken!=null) {
            String token = tokenService.clearBearerToken(bearerToken);
            User currentUser = userService.getByUsername(tokenService.getUsername(token));

            MasterWithCheckingResponseDto masterResponseDto;
            if (user.getRoles().get(0).getName().equals(RoleType.MASTER.name())) {
                Master master = user.getMasterInfo();
                boolean containsThisMaster = new ArrayList<>(currentUser.getFavourites()).contains(master);
                masterResponseDto = new MasterWithCheckingResponseDto(master, containsThisMaster);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(masterResponseDto, HttpStatus.OK);
        }else {
            MasterResponseDto masterResponseDto;
            if (user.getRoles().get(0).getName().equals(RoleType.MASTER.name())) {
                Master master = user.getMasterInfo();
                masterResponseDto = new MasterResponseDto(master);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(masterResponseDto, HttpStatus.OK);
        }
    }

    @PostMapping("/comments")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> addMasterComment(@RequestHeader("Authorization") String bearerToken, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable, @RequestBody @Validated MasterCommentRequestDto requestDto){

        if(requestDto==null || (requestDto.getComment()==null && requestDto.getRating()==null)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String token=tokenService.clearBearerToken(bearerToken);

        Master master=masterService.getById(requestDto.getMasterId());
        User user=userService.getByUsername(tokenService.getUsername(token));

        if(user==null || master==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        MasterComment masterComment=new MasterComment();
        masterComment.setAuthor(user);
        masterComment.setMaster(master);
        masterComment.setRating(requestDto.getRating());
        masterComment.setComment(requestDto.getComment());

        masterCommentService.add(masterComment);

        List<MasterComment> masterComments=masterCommentService.getByMaster(master, pageable);
        List<MasterCommentResponseDto> masterCommentResponseDtos=new ArrayList<>();
        for(MasterComment comment:masterComments){
            masterCommentResponseDtos.add(new MasterCommentResponseDto(comment));
        }
        Double masterNewRating=masterService.getById(requestDto.getMasterId()).getRating();

        MasterCommentAfterAddingResponseDto responseDto=new MasterCommentAfterAddingResponseDto(masterNewRating, masterCommentResponseDtos);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/comments")
    public ResponseEntity<?> getMasterComment(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam(name = "masterId") User user){

        if(user==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Master master=user.getMasterInfo();
        if(master==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<MasterComment> masterComments=masterCommentService.getByMaster(master, pageable);
        List<MasterCommentResponseDto> responseDto=new ArrayList<>();
        for(MasterComment masterComment:masterComments){
            responseDto.add(new MasterCommentResponseDto(masterComment));
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
