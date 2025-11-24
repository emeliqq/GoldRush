package edu.io.placement;

import edu.io.Board;
import edu.io.token.EmptyToken;

public class SequentialPlacement implements PlacementStrategy {

    @Override
    public Board.Coords getSquare(Board board) {
        for (int r = 0; r < board.size(); r++) {
            for (int c = 0; c < board.size(); c++) {
                if (board.peekToken(c, r) instanceof EmptyToken) {
                    return new Board.Coords(c, r);
                }
            }
        }
        throw new IllegalStateException();
    }
}
