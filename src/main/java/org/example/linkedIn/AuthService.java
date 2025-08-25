package org.example.linkedIn;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AuthService {

    private final Stores stores;
    private final Map<String, String> sessions = new ConcurrentHashMap<>(); // token -> userId

    AuthService(Stores stores) { this.stores = stores; }

    public String register(String email, String password) {
        String id = "u-" + UUID.randomUUID();
        if (stores.users.values().stream().anyMatch(u -> u.email.equalsIgnoreCase(email)))
            throw new IllegalArgumentException("email already exists");
        stores.users.put(id, new User(id, email, hash(password), Set.of("USER")));
        stores.profiles.put(id, new Profile(id));
        return id;
    }

    public String login(String email, String password) {
        User u = stores.users.values().stream()
                .filter(x -> x.email.equalsIgnoreCase(email)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        if (!Objects.equals(u.passwordHash, hash(password))) throw new IllegalArgumentException("bad credentials");
        String token = "t-" + UUID.randomUUID();
        sessions.put(token, u.id);
        return token;
    }

    public String authenticate(String token) {
        String uid = sessions.get(token);
        if (uid == null) throw new SecurityException("invalid session");
        return uid;
    }

    private String hash(String s) { return Integer.toHexString(Objects.hash(s)); }
}
