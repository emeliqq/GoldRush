package edu.io.token;

import edu.io.Board;
import edu.io.Player;

public class PlayerToken extends Token {

    public enum Move { NONE, UP, DOWN, LEFT, RIGHT }

    private final Board board;
    private final Player player;
    private int col;
    private int row;

    public PlayerToken(Player player, Board board) {
        super(Label.PLAYER_TOKEN_LABEL);
        if (player == null) throw new IllegalArgumentException();
        if (board == null) throw new IllegalArgumentException();
        this.player = player;
        this.board = board;
        Board.Coords c = board.getAvailableSquare();
        this.col = c.col();
        this.row = c.row();
        board.placeToken(col, row, this);
    }

    public PlayerToken(Player player, Board board, int col, int row) {
        super(Label.PLAYER_TOKEN_LABEL);
        if (player == null) throw new IllegalArgumentException();
        if (board == null) throw new IllegalArgumentException();
        if (!board.inBounds(col, row)) throw new IllegalArgumentException();
        this.player = player;
        this.board = board;
        this.col = col;
        this.row = row;
        board.placeToken(col, row, this);
    }

    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }

    public void move(Move dir) {
        int newCol = col;
        int newRow = row;

        switch (dir) {
            case UP -> newRow--;
            case DOWN -> newRow++;
            case LEFT -> newCol--;
            case RIGHT -> newCol++;
            case NONE -> { return; }
        }

        if (!board.inBounds(newCol, newRow)) {
            throw new IllegalArgumentException();
        }

        var token = board.peekToken(newCol, newRow);
        player.interactWithToken(token);

        board.setEmptyAt(col, row);
        col = newCol;
        row = newRow;
        board.placeToken(col, row, this);
    }
}
