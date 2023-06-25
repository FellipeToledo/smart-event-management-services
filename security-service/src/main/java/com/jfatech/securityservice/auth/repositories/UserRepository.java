package com.jfatech.securityservice.auth.repositories;

import com.jfatech.securityservice.auth.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Fellipe Toledo
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
