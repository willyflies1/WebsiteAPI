package com.api.website.repositories;

import com.api.website.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository("users")
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT user FROM users user WHERE user.email = ?1")
    public User getByEmail(String email);

    @Query("SELECT user FROM users user WHERE user.lastname = ?1")
    public User getByLastname(String lastname);

    // Requires @Transactional & @Modifying when modifying an existing record
    @Transactional
    @Modifying
    @Query("DELETE FROM users WHERE id = ?1")
    public Integer deleteById(UUID id);

    @Query("SELECT user FROM users user WHERE user.id = ?1")
    public User getByUUID(UUID id);
}