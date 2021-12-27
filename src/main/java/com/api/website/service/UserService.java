package com.api.website.service;

import com.api.website.model.User;
import com.api.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByLastname(String lastname){
        return userRepository.getByLastname(lastname);
    }

    public User findByEmail(String email){
        return userRepository.getByEmail(email);
    }

    public User findById(UUID id) { return userRepository.getByUUID(id); }

//    public Optional<User> getUserById(String id) {
//        return userRepository.findById(id);
//    }
}
