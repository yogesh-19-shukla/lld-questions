package org.example.chess.models;


import org.example.chess.enums.Color;
import org.example.chess.pieces.*;

public class Board {

    private Cell[][] grid;

    public Board() {
        grid = new Cell[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                grid[i][j] = new Cell(i,j);
            }
        }

        // ♙ Initialize White Pawns
        for(int j=0;j<8;j++){
            grid[1][j].setPiece(new Pawn(Color.WHITE));
        }

        // ♟ Initialize Black Pawns
        for(int j=0;j<8;j++){
            grid[6][j].setPiece(new Pawn(Color.BLACK));
        }

        // ♖ Rooks
        grid[0][0].setPiece(new Rook(Color.WHITE));
        grid[0][7].setPiece(new Rook(Color.WHITE));
        grid[7][0].setPiece(new Rook(Color.BLACK));
        grid[7][7].setPiece(new Rook(Color.BLACK));

        // ♘ Knights
        grid[0][1].setPiece(new Knight(Color.WHITE));
        grid[0][6].setPiece(new Knight(Color.WHITE));
        grid[7][1].setPiece(new Knight(Color.BLACK));
        grid[7][6].setPiece(new Knight(Color.BLACK));

        // ♗ Bishops
        grid[0][2].setPiece(new Bishop(Color.WHITE));
        grid[0][5].setPiece(new Bishop(Color.WHITE));
        grid[7][2].setPiece(new Bishop(Color.BLACK));
        grid[7][5].setPiece(new Bishop(Color.BLACK));

        // ♕ Queens
        grid[0][3].setPiece(new Queen(Color.WHITE));
        grid[7][3].setPiece(new Queen(Color.BLACK));

        // ♔ Kings
        grid[0][4].setPiece(new King(Color.WHITE));
        grid[7][4].setPiece(new King(Color.BLACK));

    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public void movePiece(Position from, Position to) {
        Piece moving = getCell(from.getRow(), from.getCol()).getPiece();
        getCell(to.getRow(), to.getCol()).setPiece(moving);
        getCell(from.getRow(), from.getCol()).setPiece(null);
    }

    public void printBoard() {
        for (int i = 7; i >= 0; i--) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 8; j++) {
                Piece p = grid[i][j].getPiece();
                System.out.print((p != null ? p.getSymbol() : ".") + " ");
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
}
