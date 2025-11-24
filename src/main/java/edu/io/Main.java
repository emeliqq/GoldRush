package edu.io;

import edu.io.token.PlayerToken;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        Player player = new Player();
        game.join(player);
        game.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String cmd = scanner.nextLine();

            switch (cmd) {
                case "w" -> player.token().move(PlayerToken.Move.UP);
                case "s" -> player.token().move(PlayerToken.Move.DOWN);
                case "a" -> player.token().move(PlayerToken.Move.LEFT);
                case "d" -> player.token().move(PlayerToken.Move.RIGHT);
            }

            game.board().display();
        }
    }
}

