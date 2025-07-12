package org.example.chess.pieces;

import org.example.chess.enums.Color;
import org.example.chess.models.Board;
import org.example.chess.models.Position;

import java.util.List;

public abstract class Piece {
    protected Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract List<Position> getValidMoves(Position from, Board board);
    public abstract String getSymbol();

}
