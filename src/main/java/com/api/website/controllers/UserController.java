package com.api.website.controllers;


import com.api.website.models.User;
import com.api.website.repositories.UserRepository;
import com.api.website.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> listAll(Model model) {
//        List<User> listUsers = userRepository.findAll();
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);

        return new ResponseEntity<List<User>>(listUsers, HttpStatus.OK);
    }

    @GetMapping("/users/lastname/{lastname}")
    public ResponseEntity<User> findByLastname(@PathVariable String lastname) {
        return new ResponseEntity<User>(userService.findByLastname(lastname), HttpStatus.OK);
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        User userByEmail = userService.findByEmail(email);
        System.out.println("User by Email" + userByEmail);
        if(userByEmail != null){
            System.out.println("HttpStatus.OK" + userByEmail);
            return new ResponseEntity<User>(userService.findByEmail(email), HttpStatus.OK);
        } else if ( userByEmail == null){
            System.out.println("HttpStatus.NOT_FOUND" + userByEmail);
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        System.out.println("HttpStatus.BAD_REQUEST" + userByEmail);
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/users/create")
    public ResponseEntity<User> create(@RequestBody User user){
        User newUser = user;
        System.out.println("Create User" + newUser);
        userRepository.save(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

}