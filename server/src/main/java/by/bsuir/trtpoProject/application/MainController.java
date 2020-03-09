package by.bsuir.trtpoProject.application;

import by.bsuir.trtpoProject.controller.Student;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @ResponseBody
    @RequestMapping(value = "/signIn", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> greeting(){
        Gson gson=new Gson();
        Student student=new Student("Carl", 15, new Date());
        String json=gson.toJson(student);
        System.out.println(json);
        //System.out.println(name);

        Map<Object, Object> response=new HashMap<>();

        //need for working with js
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Access-Control-Allow-Origin", "*");

        //return new ResponseEntity<>(student, httpHeaders, HttpStatus.OK);
        return new ResponseEntity<>(student, httpHeaders, HttpStatus.OK);
    }
}
