package org.example.cricinfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlayerRepository {

    private final Map<String, Player> players = new HashMap<>();

    public void save(Player player) { players.put(player.getId(), player); }

    public Optional<Player> findById(String id) {
        return Optional.ofNullable(players.get(id));
    }
}