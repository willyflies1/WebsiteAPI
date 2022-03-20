package com.api.website.repository;

import com.api.website.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("roles")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "SELECT * FROM roles WHERE name = ?1", nativeQuery = true)
    public Role findByName(String name);
}
