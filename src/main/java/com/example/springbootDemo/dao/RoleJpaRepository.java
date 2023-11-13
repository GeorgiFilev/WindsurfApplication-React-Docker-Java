package com.example.springbootDemo.dao;

import com.example.springbootDemo.model.ERole;
import com.example.springbootDemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
