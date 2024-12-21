package com.example.core.data.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.core.data.Enum.RoleUser;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleUser role;

    private boolean isActive;

    public User(String email, String login, String password, RoleUser role) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    // Constructeur sans paramètres requis par JPA
    public User() {}
}
