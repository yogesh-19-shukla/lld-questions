package org.example.tictactoe;

import org.example.tictactoe.enums.GameStatus;
import org.example.tictactoe.models.Board;
import org.example.tictactoe.models.Move;
import org.example.tictactoe.strategies.DefaultwinningStrategy;
import org.example.tictactoe.strategies.WinningStrategy;

import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private GameStatus gameStatus;
    private WinningStrategy winningStrategy;
    private int moveCount;

    public Game(int size, List<Player> players) {
        this.board = new Board(size);
        this.players = players;
        this.currentPlayerIndex = 0;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.winningStrategy = new DefaultwinningStrategy();
        this.moveCount = 0;
    }

    public void start() {
        while(gameStatus == GameStatus.IN_PROGRESS) {
            board.printBoard();
            Player currentPlayer = players.get(currentPlayerIndex);
            Move move = currentPlayer.makeMove(board);

            if(!isValidMove(move)) {
                System.out.println("Invalid move, try again!");
                continue;
            }
            board.setMove(move);
            moveCount++;

            if(winningStrategy.checkWinner(board, move)) {
                gameStatus = GameStatus.WIN;
                board.printBoard();
                System.out.println("Player " + currentPlayer.name + " wins!");
                return;
            }

            if(moveCount == board.getSize() * board.getSize()){
                gameStatus = GameStatus.DRAW;
                board.printBoard();
                System.out.println("It's a draw!");
                return;
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    private boolean isValidMove(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        return row >= 0 && row < board.getSize() &&
                col >=0 && col < board.getSize() &&
                board.isCellEmpty(row, col);
    }

}
