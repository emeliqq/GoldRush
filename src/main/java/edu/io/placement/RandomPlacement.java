package edu.io.placement;

import edu.io.Board;
import edu.io.token.EmptyToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlacement implements PlacementStrategy {

    private final Random random = new Random();

    @Override
    public Board.Coords getSquare(Board board) {
        List<Board.Coords> free = new ArrayList<>();
        for (int r = 0; r < board.size(); r++) {
            for (int c = 0; c < board.size(); c++) {
                if (board.peekToken(c, r) instanceof EmptyToken) {
                    free.add(new Board.Coords(c, r));
                }
            }
        }
        if (free.isEmpty()) throw new IllegalStateException();
        return free.get(random.nextInt(free.size()));
    }
}
