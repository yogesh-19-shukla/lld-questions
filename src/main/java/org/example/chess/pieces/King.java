package org.example.chess.pieces;

import org.example.chess.enums.Color;
import org.example.chess.models.Board;
import org.example.chess.models.Cell;
import org.example.chess.models.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color){
        super(color);
    }

    @Override
    public List<Position> getValidMoves(Position from, Board board) {
        List<Position> moves = new ArrayList<>();
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for(int i=0;i<8;i++){
            int nrow = from.getRow() + dx[i];
            int ncol = from.getCol() + dy[i];
            Position pos = new Position(nrow, ncol);
            if(pos.isValid()){
                Cell cell = board.getCell(nrow, ncol);
                if(cell.isEmpty() || cell.getPiece().getColor() != this.color) {
                    moves.add(pos);
                }
            }
        }
        return moves;
    }

    @Override
    public String getSymbol() {
        return color == Color.WHITE ? "♔" : "♚";
    }
}
