package com.api.website.service;

import com.api.website.model.Role;
import com.api.website.model.User;
import com.api.website.model.dto.UserDto;

import java.util.List;
import java.util.UUID;

//@Service("userService")
public interface UserService {

    List<UserDto> getUsers();

    UserDto findByUsername(String username);

    UserDto findByEmail(String email);

    UserDto findById(UUID id);

    UserDto saveUser(UserDto user);

    List<Role> getRoles();

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User deleteUser(String username);
}
