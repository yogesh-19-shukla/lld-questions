package org.example.chess.models;

import org.example.chess.pieces.Piece;

public class Cell {

    private Position position;
    private Piece piece;

    public Cell(int row, int col) {
        this.position = new Position(row, col);
    }

    public Position getPosition() { return position; }
    public Piece getPiece() { return piece; }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

}
