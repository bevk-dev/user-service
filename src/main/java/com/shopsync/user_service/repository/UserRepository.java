package com.shopsync.user_service.repository;

import com.shopsync.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAuth0Id(String auth0Id);

    boolean existsByAuth0Id(String auth0Id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
