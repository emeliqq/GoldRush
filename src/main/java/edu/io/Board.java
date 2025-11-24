package edu.io;

import edu.io.placement.PlacementStrategy;
import edu.io.placement.SequentialPlacement;
import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board {

    public record Coords(int col, int row) {}

    public final int size;

    private final Token[][] grid;
    private final EmptyToken emptyToken;
    private PlacementStrategy placementStrategy;

    public Board() {
        this(5);
    }

    public Board(int size) {
        if (size <= 0) throw new IllegalArgumentException();
        this.size = size;
        this.grid = new Token[size][size];
        this.emptyToken = new EmptyToken();
        this.placementStrategy = new SequentialPlacement();
        clean();
    }

    public void setPlacementStrategy(PlacementStrategy strategy) {
        if (strategy == null) throw new IllegalArgumentException();
        this.placementStrategy = strategy;
    }

    public Coords getAvailableSquare() {
        return placementStrategy.getSquare(this);
    }

    public int size() {
        return size;
    }

    public boolean inBounds(int col, int row) {
        return col >= 0 && col < size && row >= 0 && row < size;
    }

    public void setEmptyAt(int col, int row) {
        if (!inBounds(col, row)) throw new IllegalArgumentException();
        grid[row][col] = emptyToken;
    }

    public boolean placeToken(int col, int row, Token token) {
        if (!inBounds(col, row) || token == null) return false;
        grid[row][col] = token;
        return true;
    }

    public Token square(int col, int row) {
        if (!inBounds(col, row)) return null;
        return grid[row][col];
    }

    public Token peekToken(int col, int row) {
        return square(col, row);
    }

    public void clean() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = emptyToken;
            }
        }
    }

    public void display() {
        for (int r = 0; r < size; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < size; c++) {
                Token t = grid[r][c];
                sb.append(t == null ? "?" : t.label());
                if (c < size - 1) sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }
}
