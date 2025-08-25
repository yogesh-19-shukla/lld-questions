package org.example.linkedIn;

import java.util.Set;

public class User {

    final String id;
    final String email;
    final String passwordHash; // demo only; use BCrypt in prod
    final Set<String> roles;

    public User(String id, String email, String passwordHash, Set<String> roles) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.roles = roles;
    }
}
