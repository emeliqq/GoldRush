package project.gold_rush.token;

import project.gold_rush.Board;

/**
 * Pionek gracza. Po stworzeniu automatycznie umieszcza się na podanej planszy.
 * Zawiera prostą metodę move (można rozbudować później).
 */
public class PlayerToken extends Token {
    public enum Move { NONE, UP, DOWN, LEFT, RIGHT }

    private final Board board;
    private int col;
    private int row;

    public PlayerToken(Board board, int col, int row) {
        super(Label.PLAYER_TOKEN_LABEL);
        if (board == null) throw new IllegalArgumentException("board cannot be null");
        if (!board.inBounds(col, row)) throw new IllegalArgumentException("Initial position outside board");
        this.board = board;
        this.col = col;
        this.row = row;
        board.placeToken(col, row, this);
    }

    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }

    /** Prosty ruch — nadpisuje pole docelowe; rzuca IllegalArgumentException gdy poza planszą */
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
            throw new IllegalArgumentException("Cannot move outside the board");
        }

        // zostawiamy puste pole na starym miejscu
        board.setEmptyAt(col, row);

        // aktualizujemy pozycję i umieszczamy siebie
        col = newCol;
        row = newRow;
        board.placeToken(col, row, this);
    }
}
