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

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUsername(user.getUsername());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        usersRepository.save(user);
    }
}


/*

    User n = new User();
            n.setFirstName(firstName);
                    n.setLastName(lastName);
                    n.setEmail(email);
                    n.setUsername(username);
                    n.setPassword(password);
                    m.addUser(n);
                    userRepository.save(n);*/
