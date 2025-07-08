package org.example.tictactoe.models;

import org.example.tictactoe.Player;

public class Board {

    private int size;
    private Cell[][] grid;

    public Board(int size) {
        this.size = size;
        grid = new Cell[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                grid[i][j] = new Cell(i, j);
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col].isCellEmpty();
    }

    public void setMove(Move move) {
        grid[move.getRow()][move.getCol()].setPlayer(move.getPlayer());
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Player p = grid[i][j].getPlayer();
                System.out.print(p != null ? p.getSymbol() : "-");
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public int getSize() { return size; }

    public Cell[][] getGrid() { return grid; }

}
