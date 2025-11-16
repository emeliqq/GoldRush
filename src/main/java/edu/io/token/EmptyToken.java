package edu.io.token;

/** Token reprezentujący puste pole na planszy */
public class EmptyToken extends Token {
    public EmptyToken() {
        super(Label.EMPTY_TOKEN_LABEL);
    }
}
