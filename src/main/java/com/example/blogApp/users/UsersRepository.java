package com.example.blogApp.users;

import com.example.blogApp.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
    User findByEmail(String email);
}



