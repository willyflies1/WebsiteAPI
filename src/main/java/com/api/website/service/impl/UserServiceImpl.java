package com.api.website.service.impl;

import com.api.website.controller.LoggingController;
import com.api.website.model.dto.UserDto;
import com.api.website.model.Role;
import com.api.website.repository.RoleRepository;
import com.api.website.repository.UserRepository;
import com.api.website.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDto findByUsername(String username) {
        logger.info("Searching for user by username={} in DB", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDto findByEmail(String email) {
        logger.info("Searching for user by email={} in DB", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDto findById(UUID id) {
        logger.info("Searching for user by UUID={} in DB", id);
        return userRepository.findByUUID(id);
    }

    @Override
    public UserDto saveUser(UserDto user) {
        logger.info("Saving new user to the database");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        logger.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        UserDto user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        logger.info("Adding role {} to user {}", roleName, username);
        user.getRoles().add(role);
        userRepository.save(user);
    }
}
