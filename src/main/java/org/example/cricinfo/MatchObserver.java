package org.example.cricinfo;

public interface MatchObserver {
    void update(Match match, Ball lastBall);
}
