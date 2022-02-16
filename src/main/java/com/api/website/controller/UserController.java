package com.api.website.controller;


import com.api.website.model.dto.UserDto;
import com.api.website.model.Role;
import com.api.website.model.RoleToUserForm;
import com.api.website.repository.UserRepository;
import com.api.website.security.model.AuthenticationResponse;
import com.api.website.security.service.JwtUtil;
import com.api.website.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/authenticate")
    public ResponseEntity<?> getAuthenticationToken(
            @RequestHeader("user") String encodedUser) throws Exception {
        // ** Decode header
        String stippedEncodedUser = encodedUser.substring("Basic".length());
        byte[] decodedByte = Base64.decodeBase64(stippedEncodedUser);
        String decodedUser = new String(decodedByte);

        // ** Extract Username & Password from header
        String username = decodedUser.substring(0, decodedUser.indexOf(':'));
        String password = decodedUser.substring(decodedUser.indexOf(':') + 1);
        System.out.println("Decoded User " + decodedUser);

        // ** Grab user from DB if user exists
        UserDto userDto = userService.findByUsername(username);
        if (userDto == null) {
            return new ResponseEntity<String>("Username not found", HttpStatus.BAD_REQUEST);
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,
                            password)
            );

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> listAll(Model model) {
        List<UserDto> listUserDtos = userService.getUsers();
        model.addAttribute("listUsers", listUserDtos.toString());

        return new ResponseEntity<List<UserDto>>(listUserDtos, HttpStatus.OK);
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable String email) {
        UserDto userDtoByEmail = userService.findByEmail(email);
        System.out.println("User by Email" + userDtoByEmail);
        if (userDtoByEmail != null) {
            System.out.println("HttpStatus.OK" + userDtoByEmail);
            return new ResponseEntity<UserDto>(userService.findByEmail(email), HttpStatus.OK);
        } else if (userDtoByEmail == null) {
            System.out.println("HttpStatus.NOT_FOUND" + userDtoByEmail);
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
        System.out.println("HttpStatus.BAD_REQUEST" + userDtoByEmail);
        return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/users/create")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        UserDto newUserDto = userDto;
        URI uri =
                URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/csm/api/v1/users/create").toUriString());
        // ** Grab user from DB if user exists
        UserDto duplicateUser = userService.findByUsername(userDto.getUsername());
        if (duplicateUser != null) {
            return new ResponseEntity<String>("Duplicate username", HttpStatus.BAD_REQUEST);
        } else if (userService.findByEmail(userDto.getEmail()) != null){
            return new ResponseEntity<String>("Duplicate email address", HttpStatus.BAD_REQUEST);
        }

        System.out.println("Create User" + newUserDto.toString());

        userRepository.save(userDto);
        return new ResponseEntity<UserDto>(newUserDto, HttpStatus.CREATED);
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return ResponseEntity.ok().body(userService.saveRole(role));
    }

    @PostMapping("/role/add-to-user")
    public ResponseEntity<?> create(@RequestBody RoleToUserForm roleForm) {
        userService.addRoleToUser(roleForm.getUsername(), roleForm.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> modifyUser(@PathVariable UUID id, @RequestBody UserDto userDto) {
        UserDto userDtoToModify = userRepository.findByUUID(id);
//                .orElseThrow(()) -> new ResourceNotFoundException("User not found for this id :: " + id));
        userDtoToModify.setUsername(userDto.getUsername());
        userDtoToModify.setEmail(userDto.getEmail());
        userDtoToModify.setPassword(userDto.getPassword());
        final UserDto updatedUserDto = userDtoToModify;
        userRepository.save(updatedUserDto);
        return new ResponseEntity<UserDto>(updatedUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDto> deleteById(@PathVariable UUID id) {
        userRepository.deleteById(id);
        return new ResponseEntity<UserDto>(userService.findById(id), HttpStatus.OK);
    }
}