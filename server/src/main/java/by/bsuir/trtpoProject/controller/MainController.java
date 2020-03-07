package by.bsuir.trtpoProject.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class MainController {

    @GetMapping("/tattoo4u")
    public @ResponseBody String greeting(@RequestParam(name="name", required = false, defaultValue = "World") String name, Model model){
        Gson gson=new Gson();
        Student student=new Student("Carl", 15, new Date());
        String json=gson.toJson(student);
        System.out.println(json);
        return json;
    }
}
