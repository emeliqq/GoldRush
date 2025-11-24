package edu.io;

import edu.io.placement.PlacementStrategy;
import edu.io.placement.RandomPlacement;
import edu.io.token.EmptyToken;
import edu.io.token.GoldToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlacementStrategyTest {

    @Test
    void can_set_strategy() {
        Board board = new Board();
        PlacementStrategy strategy = new RandomPlacement();
        board.setPlacementStrategy(strategy);
        var pos = board.getAvailableSquare();
        Assertions.assertTrue(board.peekToken(pos.col(), pos.row()) instanceof EmptyToken);
    }

    @Test
    void random_strategy_returns_free_field() {
        Board board = new Board();
        board.setPlacementStrategy(new RandomPlacement());
        var pos = board.getAvailableSquare();
        Assertions.assertTrue(board.peekToken(pos.col(), pos.row()) instanceof EmptyToken);
    }

    @Test
    void throws_when_board_full() {
        Board board = new Board(1);
        board.placeToken(0,0,new GoldToken());
        board.setPlacementStrategy(new RandomPlacement());
        Assertions.assertThrows(IllegalStateException.class, () -> board.getAvailableSquare());
    }
}
