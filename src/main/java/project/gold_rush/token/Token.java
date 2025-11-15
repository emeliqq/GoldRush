package project.gold_rush.token;

/**
 * Bazowy token. Testy oczekują publicznego pola label i jednego konstruktora.
 */
public class Token {
    public final String label;

    public Token(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
