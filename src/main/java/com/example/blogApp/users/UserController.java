package com.example.blogApp.users;

import com.example.blogApp.auth.AuthGroup;
import com.example.blogApp.auth.AuthGroupRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping(value="/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getUsers() {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("firstName", "Welcome" + user.getFirstName());
        modelAndView.addObject("adminMessage", "Welcome Administrator");
        modelAndView.addObject("allUsers", userService.findAllUsers());
        modelAndView.setViewName("users");
        return modelAndView;
        // return new ModelAndView("welcomePage", "model", model);
    }

}

/*@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private AuthGroupRepository authGroupRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showForm() {

        return new ModelAndView("register", "user", new User());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String submit(@RequestBody
                         @Valid @ModelAttribute("user")User user,
                         BindingResult result,
                         @ModelAttribute("authGroup")AuthGroup authGroup,
                         ModelMap model,
             @RequestParam String firstName
            ,@RequestParam String lastName
            ,@RequestParam String email
            ,@RequestParam String username
            ,@RequestParam String password
    ) {
        if (result.hasErrors()) {
            return "register";
        } else {

            ModelAndView modelAndView = new ModelAndView();

            // Create the user's role
            AuthGroup m = new AuthGroup();
            m.setUsername(username);
            m.setAuthGroup("ADMIN");
            authGroupRepository.save(m);

            // Create the user
            User n = new User();
            n.setFirstName(firstName);
            n.setLastName(lastName);
            n.setEmail(email);
            n.setUsername(username);
            n.setPassword(password);
            m.addUser(n);
            userRepository.save(n);

            modelAndView.addObject("successMessage", "User has been registered successfully.");

            return "index";
            // Add message when user has successfully been registered.
        }
    }

    @GetMapping(value="/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getUsersPage(Model model) {

        // get all users
        List<User> users = userRepository.findAll();
        List<AuthGroup> authGroups = authGroupRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("authGroups", authGroups);
        return "users";

        // return new ModelAndView("welcomePage", "model", model);

    }
}*/
