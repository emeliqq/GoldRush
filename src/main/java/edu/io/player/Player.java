package edu.io.player;

import edu.io.token.GoldToken;
import edu.io.token.PickaxeToken;
import edu.io.token.AnvilToken;
import edu.io.token.Token;
import edu.io.token.PlayerToken;

public class Player {

    public final Gold gold = new Gold();
    private final Shed shed = new Shed();
    private PlayerToken token;

    public Gold gold() {
        return gold;
    }

    public Shed shed() {
        return shed;
    }

    public void assignToken(PlayerToken token) {
        if (token == null) throw new IllegalArgumentException();
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public void gainGold(double amount) {
        gold.gain(amount);
    }

    public void loseGold(double amount) {
        gold.lose(amount);
    }

    public void interactWithToken(Token token) {

        if (token instanceof GoldToken g) {
            double amount = g.amount();

            var tool = shed.getTool();
            if (tool instanceof PickaxeToken p && p.isWorking()) {
                amount *= p.gainFactor();
                p.useWith(g);
                if (p.isBroken()) shed.dropTool();
            }

            gainGold(amount);
            return;
        }

        if (token instanceof PickaxeToken p) {
            shed.add(p);
            return;
        }

        if (token instanceof AnvilToken a) {
            var tool = shed.getTool();
            if (tool instanceof Repairable r) {
                r.repair();
            }
            return;
        }
    }
}
