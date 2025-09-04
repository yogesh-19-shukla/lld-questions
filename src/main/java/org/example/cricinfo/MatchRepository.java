package org.example.cricinfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MatchRepository {
    private final Map<String, Match> matches = new HashMap<>();

    public void save(Match match) {
        matches.put(match.getId(), match);
    }

    public Optional<Match> findById(String id) {
        return Optional.ofNullable(matches.get(id));
    }
}
