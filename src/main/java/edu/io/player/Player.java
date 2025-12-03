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

    private void usePickaxeOnGold(PickaxeToken p, GoldToken g) {
        double amount = g.amount();

        amount *= p.gainFactor();
        p.useWith(g);

        if (p.isBroken()) {
            shed.dropTool();
        }

        gainGold(amount);
    }

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
        switch (token) {

            case GoldToken g -> {
                var tool = shed.getTool();

                if (tool instanceof PickaxeToken p && p.isWorking()) {
                    usePickaxeOnGold(p, g);
                } else {
                    gainGold(g.amount());
                }
            }

            case PickaxeToken p -> {
                shed.add(p);
            }

            case AnvilToken a -> {
                var tool = shed.getTool();
                if (tool instanceof Repairable r) {
                    r.repair();
                }
            }

            default -> {

            }
        }
    }

}
