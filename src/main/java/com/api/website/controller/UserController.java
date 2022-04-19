package com.api.website.controller;


import com.api.website.model.Role;
import com.api.website.model.RoleToUserForm;
import com.api.website.model.User;
import com.api.website.model.dto.UserDto;
import com.api.website.repository.UserRepository;
import com.api.website.security.model.AuthenticationResponse;
import com.api.website.security.service.JwtUtil;
import com.api.website.security.service.MyUserDetailsService;
import com.api.website.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;


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
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        } else if (!passwordEncoder.matches(password, userDto.getPassword())) {
            return new ResponseEntity<String>("Incorrect Password", HttpStatus.BAD_REQUEST);
        }

        try {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            userDto.getRoles().forEach(role ->
                    authorities.add(new SimpleGrantedAuthority(role.getName()))
            );

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,
                            password, authorities)
            );

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);
        final String accessJwt = jwtUtil.generateToken(userDetails);
        final String refreshJwt = jwtUtil.generateRefreshToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(accessJwt, refreshJwt, userDto.convertToUser()));
    }


    @GetMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authrorizationHeader = request.getHeader("Authorization");
        String username = null;
        String refreshJwt = null;
        String[] roles;

        if (authrorizationHeader != null && authrorizationHeader.startsWith("Bearer ")) {
            refreshJwt = authrorizationHeader.substring("Bearer ".length());
            username = jwtUtil.extractUsername(refreshJwt);
            roles = jwtUtil.extractRoles(refreshJwt);
            if (username != null) {
                UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                // Send back the accessToken and refreshToken
                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessJwt", jwtUtil.generateToken(userDetails));
                tokens.put("refreshJwt", refreshJwt);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails), refreshJwt,
                        userService.findByUsername(username).convertToUser()));
            }
        } else {
            throw new RuntimeException("Missing refresh token");
        }
        return ResponseEntity.internalServerError().body("Failed to refresh token due to missing implementation.");
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
        } else if (userService.findByEmail(userDto.getEmail()) != null) {
            return new ResponseEntity<String>("Duplicate email address", HttpStatus.BAD_REQUEST);
        }

        System.out.println("Create User" + newUserDto.toString());

        // ** Create user and add basic user role as default
        userService.saveUser(userDto);
        userService.addRoleToUser(userDto.getUsername(), RoleName.ROLE_USER.toString());
        return new ResponseEntity<UserDto>(newUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/role/all")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(userService.getRoles());
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

    @DeleteMapping("/users/delete")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String authorization) {
        String base24Token = authorization.substring("Bearer ".length());
        String username = jwtUtil.extractUsername(base24Token);
        if (username != null) {
            // username is unique
            User user = userService.deleteUser(username);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Could not extract username from token. ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<UserDto> deleteById(@PathVariable UUID id) {
//        userRepository.deleteById(id);
//        return new ResponseEntity<UserDto>(userService.findById(id), HttpStatus.OK);
//    }
}