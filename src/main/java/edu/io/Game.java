package edu.io;

import edu.io.token.PlayerToken;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Board board;
    private final List<Player> players = new ArrayList<>();

    public Game() {
        this.board = new Board();
    }

    public void join(Player player) {
        PlayerToken token = new PlayerToken(player, board);
        player.assignToken(token);
        players.add(player);
    }

    public Board board() {
        return board;
    }

    public void start() {
    }
}

