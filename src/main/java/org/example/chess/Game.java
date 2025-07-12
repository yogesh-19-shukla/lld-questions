package org.example.chess;

import org.example.chess.enums.Color;
import org.example.chess.models.Board;
import org.example.chess.models.Cell;
import org.example.chess.models.Position;

import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;

    public Game(String whiteName, String blackName) {
        this.board = new Board();
        this.whitePlayer = new Player(whiteName, Color.WHITE);
        this.blackPlayer = new Player(blackName, Color.BLACK);
        this.currentPlayer = whitePlayer;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            board.printBoard();
            System.out.println(currentPlayer.getName() + " (" + currentPlayer.getColor() + ")'s turn:");
            System.out.print("Enter move (e.g. e2 e3): ");
            String from = scanner.next();
            String to = scanner.next();

            Position fromPos = parsePosition(from);
            Position toPos = parsePosition(to);

            Cell fromCell = board.getCell(fromPos.getRow(), fromPos.getCol());
            if (fromCell.isEmpty() || fromCell.getPiece().getColor() != currentPlayer.getColor()) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            List<Position> validMoves = fromCell.getPiece().getValidMoves(fromPos, board);
            if (!validMoves.contains(toPos)) {
                System.out.println("Illegal move. Try again.");
                continue;
            }

            board.movePiece(fromPos, toPos);
            switchPlayer();
        }
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
    }

    private Position parsePosition(String input) {
        int col = input.charAt(0) - 'a';
        int row = Character.getNumericValue(input.charAt(1)) - 1;
        return new Position(row, col);
    }
}

