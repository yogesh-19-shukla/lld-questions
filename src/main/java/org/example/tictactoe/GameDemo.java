package org.example.tictactoe;

import org.example.tictactoe.enums.Symbol;

import java.util.ArrayList;
import java.util.List;

public class GameDemo {

    public static void main(String[] args) {
        Player p1 = new HumanPlayer("Alice", Symbol.X);
        Player p2 = new HumanPlayer("Bob", Symbol.O);

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        Game game = new Game(3, players);
        game.start();
    }
}
