package com.api.website.service;

import com.api.website.model.dto.UserDto;
import com.api.website.model.Role;

import java.util.List;
import java.util.UUID;

//@Service("userService")
public interface UserService {

    List<UserDto> getUsers();
    UserDto findByUsername(String username);
    UserDto findByEmail(String email);
    UserDto findById(UUID id);
    UserDto saveUser(UserDto user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);

//    @Autowired
//    private UserRepository userRepository;

//    List<UserDto> getUsers() {
//        return userRepository.findAll();
//    }

//    public UserDto findByUsername(String username){
//        return userRepository.getByUsername(username);
//    }

//    public UserDto findByEmail(String email){
//        return userRepository.getByEmail(email);
//    }

//    public UserDto findById(UUID id) { return userRepository.getByUUID(id); }

//    public Optional<User> getUserById(String id) {
//        return userRepository.findById(id);
//    }
}
