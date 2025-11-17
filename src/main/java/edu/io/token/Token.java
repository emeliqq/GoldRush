package edu.io.token;

/**
 * Bazowy token. Testy oczekują publicznego pola label i jednego konstruktora.
 */
public abstract class Token {
    private final String label;

    public Token(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
