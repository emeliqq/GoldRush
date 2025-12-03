package edu.io.player;

import edu.io.token.*;

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
        if (amount < 0) throw new IllegalArgumentException();
        gold.gain(amount);
    }

    public void loseGold(double amount) {
        if (amount < 0) throw new IllegalArgumentException();
        gold.lose(amount);
    }

    public void interactWithToken(Token token) {

        switch (token) {

            case GoldToken g -> {
                var tool = shed.getTool();

                if (tool instanceof PickaxeToken p && p.isWorking()) {
                    usePickaxeOnGold(p, g);
                    return;
                }

                if (tool instanceof SluiceboxToken s && s.isWorking()) {
                    double amount = g.amount() * s.gainFactor();
                    s.useWith(g);
                    gainGold(amount);
                    return;
                }

                gainGold(g.amount());
            }

            case PickaxeToken p -> shed.add(p);

            case SluiceboxToken s -> shed.add(s);

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

    private void usePickaxeOnGold(PickaxeToken p, GoldToken g) {
        double amount = g.amount() * p.gainFactor();
        p.useWith(g);
        if (p.isBroken()) shed.dropTool();
        gainGold(amount);
    }
}

