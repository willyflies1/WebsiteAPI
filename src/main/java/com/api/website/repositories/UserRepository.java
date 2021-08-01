package com.api.website.repositories;

import com.api.website.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("users")
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT user FROM users user WHERE user.email = ?1")
    public User getByEmail(String email);

    @Query("SELECT user FROM users user WHERE user.lastname = ?1")
    public User getByLastname(String lastname);
}