package org.example.tictactoe;

import org.example.tictactoe.enums.Symbol;
import org.example.tictactoe.models.Board;
import org.example.tictactoe.models.Move;

public abstract class Player {
    protected String name;
    protected Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public abstract Move makeMove(Board board);
}
