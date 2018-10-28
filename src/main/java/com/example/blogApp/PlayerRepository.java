package com.example.blogApp;

import org.springframework.data.repository.CrudRepository;

import com.example.blogApp.Player;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PlayerRepository extends CrudRepository<Player, Integer> {

}