package com.example.blogApp.users;

import com.example.blogApp.auth.AuthGroup;
import com.example.blogApp.auth.AuthGroupRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /* *
    * ModelAndView returns both model and view in a single return value.
    * Add the "User" object to the model.
    * Set the "register" view name for the ModelAndView.
    * */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    /*
    *
    * */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user,
                                      @Valid AuthGroup authGroup,
                                      BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            System.out.println("Trying to save the new user");
            userService.saveUser(user, authGroup);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }

    /*
    * Only administrators have access to view all users.
    * Display all users and their respective auth levels.
    * */
    @GetMapping(value="/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView();
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = userService.findUserByEmail(auth.getName());
        //modelAndView.addObject("adminMessage", "Welcome" + user.getFirstName());
        //System.out.println(user.getUsername());
        modelAndView.addObject("allUsers", userService.findAllUsers());
        modelAndView.addObject("allAuthGroups", userService.findAllAuthGroups());
        modelAndView.setViewName("users");
        return modelAndView;
    }

}


