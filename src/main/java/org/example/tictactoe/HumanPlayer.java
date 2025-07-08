package org.example.tictactoe;

import org.example.tictactoe.enums.Symbol;
import org.example.tictactoe.models.Board;
import org.example.tictactoe.models.Move;

import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, Symbol symbol) {
        super(name, symbol);
    }

    @Override
    public Move makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + " (" + symbol + "), enter row and column (0-indexed): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new Move(this, row, col);
    }
}
