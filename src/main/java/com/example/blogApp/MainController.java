package com.example.blogApp;

import com.example.blogApp.users.User;
import com.example.blogApp.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/*
@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}*/

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

    @Autowired
    private UsersRepository userRepository;

/*    @GetMapping(value="/register")
    public String getRegisterPage(Model model){
        return "register";
    }*/

/*    @PostMapping(value="/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewUser (
                      @ModelAttribute User user
                      ,@RequestParam String firstName
                      ,@RequestParam String lastName
                      ,@RequestParam String email
                      ,@RequestParam String username
                      ,@RequestParam String password
                      )
        {

            // @ResponseBody means the returned String is the response, not a view name
            // @RequestParam means it is a parameter from the GET or POST request

            User n = new User();
            n.setFirstName(firstName);
            n.setLastName(lastName);
            n.setEmail(email);
            n.setUsername(username);
            n.setPassword(password);
            userRepository.save(n);
            return "Saved";
        }*/



        @RequestMapping(value = "/register", method = RequestMethod.GET)
        public ModelAndView showForm() {

            return new ModelAndView("register", "user", new User());
        }

        @RequestMapping(value = "/add", method = RequestMethod.POST)
        public String submit(@RequestBody
                             @Valid @ModelAttribute("user")User user,
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
            /*model.addAttribute("firstName", user.getFirstName());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("username", user.getEmail());
            model.addAttribute("password", user.getPassword());*/

            User n = new User();
            n.setFirstName(firstName);
            n.setLastName(lastName);
            n.setEmail(email);
            n.setUsername(username);
            n.setPassword(password);
            userRepository.save(n);
            return "index";
        }

/*    @GetMapping(value="/users")
    public String getUsersPage(Model model, @ModelAttribute("user")User user){
            model.addAttribute("users", new User());
            model.addAttribute("firstName", user.getFirstName());
            return "users";
    }*/

    @GetMapping(value="/users")
    public String getUsersPage(Model model) {
        /*List<User> list = userRepository.findAll();
        map.addAttribute("result",list);*/

        /*User user = new User();
        map.addAttribute("user",user);
        return "users";*/

        //return "users";

/*        User user = new User();
        ModelAndView mav = new ModelAndView();
        mav.addObject("firstName", user.getFirstName());
        mav.setViewName("users");
        return mav;*/

        // get all users
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
    // This returns a JSON or XML with the users
    return userRepository.findAll();
    }
}
