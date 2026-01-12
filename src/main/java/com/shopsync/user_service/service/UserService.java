package com.shopsync.user_service.service;
import com.shopsync.user_service.entity.User;
import com.shopsync.user_service.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Uporabnik z emailom " + email + " ni bil najden."));
    }

    @Transactional
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Napaka: Uporabnik s tem emailom že obstaja.");
        }

        return userRepository.save(user);
    }

    @Transactional
    public User syncUserWithAuth0(String auth0Id, String email, String name) {
        return userRepository.findByAuth0Id(auth0Id)
                .map(existingUser -> {
                    existingUser.setEmail(email);
                    if (name != null && !name.equals("Neznan Uporabnik")) {
                        existingUser.setName(name);
                    } else if (existingUser.getName() == null) {
                        existingUser.setName("Neznan Uporabnik");
                    }
                    return userRepository.save(existingUser);
                })
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setAuth0Id(auth0Id);
                    newUser.setEmail(email);
                    newUser.setName(name);
                    return userRepository.save(newUser);
                });
    }

    public User getUserByAuth0Id(String auth0Id) {
        return userRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("Uporabnik z Auth0 ID " + auth0Id + " ne obstaja."));
    }

    @Transactional
    public User updateName(String auth0Id, String newName) {
        User user = userRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("Uporabnik z Auth0 ID " + auth0Id + " ne obstaja."));

        user.setName(newName);

        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
