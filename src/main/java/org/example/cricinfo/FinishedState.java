package org.example.cricinfo;

public class FinishedState implements MatchState {

    @Override
    public void processBall(Match match, Ball ball) {
        System.out.println("ERROR: Cannot process a ball for a finished match.");
    }
}