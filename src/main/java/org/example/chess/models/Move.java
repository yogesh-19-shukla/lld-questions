package org.example.chess.models;

import org.example.chess.pieces.Piece;

public class Move {

    private Position from;
    private Position to;
    private Piece pieceCaptured;
    private Piece pieceMoved;

    public Move(Position from, Position to, Piece pieceCaptured, Piece pieceMoved) {
        this.from = from;
        this.to = to;
        this.pieceCaptured = pieceCaptured;
        this.pieceMoved = pieceMoved;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public Piece getPieceCaptured() {
        return pieceCaptured;
    }

    public Piece getPieceMoved() {
        return pieceMoved;
    }
}
