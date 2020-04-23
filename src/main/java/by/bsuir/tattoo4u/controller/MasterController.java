package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.response.MasterResponseDto;
import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.RoleType;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/masters")
public class MasterController {

    private MasterService masterService;
    private int pageSize=10;

    @Autowired
    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    @GetMapping
    public ResponseEntity<?> getMasterByUsername(@PageableDefault(sort = "rating", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(required = false) String name, @RequestParam(required = false) String page) {

        if(page !=null && !page.isEmpty()){
            int pageNumber=Integer.parseInt(page);
            if(pageNumber<0){
                throw new ControllerException("Page index must not be less than zero");
            }
            pageable=PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "rating");
        }

        List<Master> masterList = new ArrayList<>();
        if (name == null) {
            masterList = masterService.getAll(pageable);
        } else {
            masterList = masterService.getAllUsernameContain(name, pageable);
        }

        List<MasterResponseDto> masterResponseDto = new ArrayList<>();
        for (Master master : masterList) {
            masterResponseDto.add(new MasterResponseDto(master));
        }

        return new ResponseEntity<>(masterResponseDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMasterById(@PathVariable("id") User user) {

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        MasterResponseDto masterResponseDto = null;
        if (user.getRoles().get(0).getName().equals(RoleType.MASTER.name())) {
            masterResponseDto = new MasterResponseDto(user.getMasterInfo());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(masterResponseDto, HttpStatus.OK);
    }
}
