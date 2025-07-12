package org.example.chess.pieces;

import org.example.chess.enums.Color;
import org.example.chess.models.Board;
import org.example.chess.models.Position;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color){
        super(color);
    }

    @Override
    public List<Position> getValidMoves(Position from, Board board) {
        return null;
        // provide logic here
    }

    @Override
    public String getSymbol() {
        return null;
    }
}
