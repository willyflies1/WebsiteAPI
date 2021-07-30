package com.api.website.controllers;


import com.api.website.models.User;
import com.api.website.repositories.UserRepository;
import com.api.website.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/users/{lastName}")
    public ResponseEntity<User> findByLastname(@PathVariable String lastName) {
        return new ResponseEntity<User>(userService.findByLastname(lastName), HttpStatus.OK);
    }

}