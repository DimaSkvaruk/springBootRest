package com.example.work.repository;

import com.example.work.model.Role;
import com.example.work.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role,Long> {
    Optional<Role> findByName(Roles name);
}
