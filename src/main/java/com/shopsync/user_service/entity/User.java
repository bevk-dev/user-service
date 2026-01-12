package com.shopsync.user_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "auth_users")
public class User {

    // Getterji in setterji
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String auth0Id;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;

    // Prazen konstruktor je obvezen za JPA/Hibernate
    public User() {}

    public User(String auth0Id, String email, String name) {
        this.auth0Id = auth0Id;
        this.email = email;
        this.name = name;
    }

}