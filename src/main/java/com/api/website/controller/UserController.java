package com.api.website.controller;


import com.api.website.model.User;
import com.api.website.repository.UserRepository;
import com.api.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> listAll(Model model) {
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

    @PutMapping("/users/{id}")
    public ResponseEntity<User> modifyUser(@PathVariable UUID id, @RequestBody User user) {
        User userToModify = userRepository.getByUUID(id);
//                .orElseThrow(()) -> new ResourceNotFoundException("User not found for this id :: " + id));
        userToModify.setFirstName(user.getFirstname());
        userToModify.setLastName(user.getLastname());
        userToModify.setEmail(user.getEmail());
        userToModify.setPassword(user.getPassword());
        final User updatedUser = userToModify;
        userRepository.save(updatedUser);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteById(@PathVariable UUID id) {
        userRepository.deleteById(id);
        return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("test/user")
    public ResponseEntity<String> user(){
        return new ResponseEntity<String>("Success!", HttpStatus.OK);
    }

}