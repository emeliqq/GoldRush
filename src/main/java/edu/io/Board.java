package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

/**
 * Plansza: trzyma tokeny w gridzie; nie zwraca nulli dla poprawnych indeksów.
 * Zawiera record Coords używany przez PlayerToken.
 */
public class Board {

    public record Coords(int col, int row) {}

    // public ponieważ testy odwołują się bezpośrednio do board.size
    public final int size;

    // grid przechowuje obiekty Token (nigdy null dla poprawnych indeksów)
    private final Token[][] grid;
    private final EmptyToken emptyToken;

    public Board() {
        this(5); // domyślny rozmiar zgodny z wcześniejszymi przykładami/testami
    }

    public Board(int size) {
        if (size <= 0) throw new IllegalArgumentException("size must be > 0");
        this.size = size;
        this.grid = new Token[size][size];
        this.emptyToken = new EmptyToken();
        clean();
    }

    /** Czy współrzędne są wewnątrz planszy */
    public boolean inBounds(int col, int row) {
        return col >= 0 && col < size && row >= 0 && row < size;
    }

    /** Ustawia pole (col,row) jako puste (EmptyToken) */
    public void setEmptyAt(int col, int row) {
        if (!inBounds(col, row)) throw new IllegalArgumentException("Out of bounds");
        grid[row][col] = emptyToken;
    }

    /**
     * Umieszcza token na planszy (nadpisuje).
     * Zwraca false gdy poza planszą lub token == null.
     */
    public boolean placeToken(int col, int row, Token token) {
        if (!inBounds(col, row) || token == null) return false;
        grid[row][col] = token;
        return true;
    }

    /** Zwraca token na danej pozycji; dla indeksów poza planszą zwraca null */
    public Token square(int col, int row) {
        if (!inBounds(col, row)) return null;
        return grid[row][col];
    }

    /** Ustawia wszystkie pola jako puste (EmptyToken) */
    public void clean() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = emptyToken;
            }
        }
    }

    /** Proste wyświetlenie planszy (etykiety tokenów) */
    public void display() {
        for (int r = 0; r < size; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < size; c++) {
                Token t = grid[r][c];
                sb.append(t == null ? "?" : t.label);
                if (c < size - 1) sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }
}
