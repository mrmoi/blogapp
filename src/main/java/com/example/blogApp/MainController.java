package com.example.blogApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/")
public class MainController {


    @GetMapping(value={"/", "/index"})
    public String getHomePage(Model model){
        return "index";
    }

    @GetMapping(value={"/home"})
    public String getHome(Model model){
        return "home";
    }

    @GetMapping(value="/login")
    public String getLoginPage(Model model){
        return "login";
    }

    @GetMapping(value="/logout-success")
    public String getLogoutPage(Model model){
        return "logout";
    }

}
