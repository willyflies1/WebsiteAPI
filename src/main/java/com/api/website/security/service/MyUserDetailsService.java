package com.api.website.security.service;

import com.api.website.model.dto.UserDto;
import com.api.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Username: " + username);
        // Get user from db... if found... return user else throw exception
        UserDto userDto = userRepository.findByUsername(username);
        if (userDto != null) {
            System.out.println("Found user!");
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            userDto.getRoles().forEach(role ->
                    authorities.add(new SimpleGrantedAuthority(role.getName()))
            );
            User user = new User(userDto.getUsername(), userDto.getPassword(), authorities);
            return user;
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }
}
