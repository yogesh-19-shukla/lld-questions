package org.example.cricinfo;

public interface MatchFormatStrategy {
    int getTotalInnings();
    int getTotalOvers();
    String getFormatName();
}