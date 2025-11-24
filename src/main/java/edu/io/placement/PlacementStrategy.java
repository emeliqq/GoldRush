package edu.io.placement;

import edu.io.Board;

public interface PlacementStrategy {
    Board.Coords getSquare(Board board);
}
