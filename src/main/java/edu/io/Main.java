package edu.io;

import edu.io.player.Player;
import edu.io.token.PlayerToken;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        Player player = new Player();
        game.join(player);

        Board board = game.board();

        PlayerToken token = null;
        for (int r = 0; r < board.size(); r++) {
            for (int c = 0; c < board.size(); c++) {
                if (board.peekToken(c, r) instanceof PlayerToken t) {
                    token = t;
                    break;
                }
            }
        }

        if (token == null) {
            System.out.println("PLAYER TOKEN NOT FOUND");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            board.display();
            System.out.print("> ");
            String cmd = scanner.nextLine();

            switch (cmd) {
                case "w" -> token.move(PlayerToken.Move.UP);
                case "s" -> token.move(PlayerToken.Move.DOWN);
                case "a" -> token.move(PlayerToken.Move.LEFT);
                case "d" -> token.move(PlayerToken.Move.RIGHT);
                case "q" -> { return; }
            }
        }
    }
}


