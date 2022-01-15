package com.api.website.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Username: " + username);

        // Get user from db... if found... return user else throw exception
        // mock a known user until db is setup
        User mockUser = new User("chet", "manly", new ArrayList<>());
        if( mockUser.getUsername().equals(username)){
            System.out.println("Found user!");
            return mockUser;
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }
}
