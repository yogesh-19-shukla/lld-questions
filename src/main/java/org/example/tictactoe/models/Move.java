package org.example.tictactoe.models;

import org.example.tictactoe.Player;

public class Move {

    private int row;
    private int col;
    private Player player;

    public Move(Player player, int row, int col) {
        this.player = player;
        this.row = row;
        this.col = col;
    }

    public Player getPlayer() { return player; }
    public int getRow() { return row; }
    public int getCol() { return col; }
}
