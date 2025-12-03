package edu.io.token;

import edu.io.player.Tool;

public class SluiceboxToken extends Token implements Tool {

    private double gainFactor = 1.2;
    private int durability = 5;

    public SluiceboxToken() {
        super(Label.SLUICEBOX_TOKEN_LABEL);
    }

    public double gainFactor() {
        return gainFactor;
    }

    public int durability() {
        return durability;
    }

    @Override
    public boolean isWorking() {
        return durability > 0;
    }

    @Override
    public boolean isBroken() {
        return durability == 0;
    }

    public void useWith(GoldToken gold) {
        if (!isWorking()) return;

        gainFactor -= 0.04;
        if (gainFactor < 0) gainFactor = 0;

        durability--;
    }

    public void useIdle(Token t) {

    }
}
