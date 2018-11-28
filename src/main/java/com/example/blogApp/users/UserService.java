package com.example.blogApp.users;

import com.example.blogApp.users.User;
import com.example.blogApp.auth.AuthGroup;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.blogApp.auth.AuthGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private UsersRepository usersRepository;
    private AuthGroupRepository authGroupRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UsersRepository usersRepository,
                       AuthGroupRepository authGroupRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.authGroupRepository = authGroupRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public void saveUser(User user, AuthGroup authGroup) {
        authGroup.setUsername(user.getUsername());
        authGroup.setAuthGroup("ADMIN");
        authGroupRepository.save(authGroup);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUsername(user.getUsername());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        authGroup.addUser(user);
        usersRepository.save(user);
    }

    public List findAllUsers() {

        List<User> users = usersRepository.findAll();
        //List<AuthGroup> authGroups = authGroupRepository.findAll();
        //model.addAttribute("users", users);
        //model.addAttribute("authGroups", authGroups);
        return users;
    }
}

