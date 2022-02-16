package com.api.website.repository;

import com.api.website.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository("users")
public interface UserRepository extends JpaRepository<UserDto, Integer> {

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    public UserDto findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    public UserDto findByUsername(String username);

//    @Transactional
//    @Query("INSERT INTO users(id, username, email, password)" +
//            "VALUES (?1, ?2, ?3, ?4)" +
//            "RETURNING id")
//    public String createUser(UUID id, String username, String email, String password);

    // Requires @Transactional & @Modifying when modifying an existing record
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users WHERE id = ?1", nativeQuery = true)
    public Integer deleteById(UUID id);

    @Query(value = "SELECT * FROM users WHERE id = ?1", nativeQuery = true)
    public UserDto findByUUID(UUID id);
}