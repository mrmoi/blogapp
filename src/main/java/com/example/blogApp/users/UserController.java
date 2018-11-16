package com.example.blogApp.users;

import com.example.blogApp.auth.AuthGroup;
import com.example.blogApp.auth.AuthGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
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
                         @ModelAttribute("authGroup")AuthGroup authGroup,
                         BindingResult result,
                         ModelMap model,
                         @RequestParam String firstName
            ,@RequestParam String lastName
            ,@RequestParam String email
            ,@RequestParam String username
            ,@RequestParam String password
    ) {
        if (result.hasErrors()) {
            return "error";
        }

        // Create the user's role
        AuthGroup m = new AuthGroup();
        m.setUsername(username);
        m.setAuthGroup("USER");
        authGroupRepository.save(m);

        // Create the user
        User n = new User();
        n.setFirstName(firstName);
        n.setLastName(lastName);
        n.setEmail(email);
        n.setUsername(username);
        n.setPassword(password);

        userRepository.save(n);

        return "index";

    }

    @GetMapping(value="/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getUsersPage(Model model) {

        // get all users
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }
}
