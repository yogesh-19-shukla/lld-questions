package org.example.tictactoe.strategies;

import org.example.tictactoe.enums.Symbol;
import org.example.tictactoe.models.Board;
import org.example.tictactoe.models.Cell;
import org.example.tictactoe.models.Move;

public class DefaultwinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Board board, Move lastMove) {
        int row = lastMove.getRow();
        int col = lastMove.getCol();
        Symbol symbol = lastMove.getPlayer().getSymbol();
        Cell[][] grid = board.getGrid();
        int size = board.getSize();

        boolean rowWin = true, colWin = true, diag1 = true, diag2 = true;

        for (int i = 0; i < size; i++) {
            if (grid[row][i].getPlayer() == null || grid[row][i].getPlayer().getSymbol() != symbol)
                rowWin = false;

            if (grid[i][col].getPlayer() == null || grid[i][col].getPlayer().getSymbol() != symbol)
                colWin = false;

            if (grid[i][i].getPlayer() == null || grid[i][i].getPlayer().getSymbol() != symbol)
                diag1 = false;

            if (grid[i][size - 1 - i].getPlayer() == null || grid[i][size - 1 - i].getPlayer().getSymbol() != symbol)
                diag2 = false;
        }

        return rowWin || colWin || diag1 || diag2;
    }
}