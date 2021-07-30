package com.api.website.services;

import com.api.website.models.User;
import com.api.website.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

//    public Optional<User> getUserById(String id) {
//        return userRepository.findById(id);
//    }
}
