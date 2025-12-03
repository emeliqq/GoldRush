package edu.io.token;

import edu.io.player.Tool;
import edu.io.player.Repairable;

public class PickaxeToken extends Token implements Tool, Repairable {

    private final double gainFactor;
    private int durability;
    private boolean idle = false;

    public PickaxeToken() {
        this(1.5, 3);
    }

    public PickaxeToken(double gainFactor) {
        this(gainFactor, 3);
    }

    public PickaxeToken(double gainFactor, int durability) {
        super(Label.PICKAXE_TOKEN_LABEL);
        if (gainFactor <= 0) throw new IllegalArgumentException();
        if (durability <= 0) throw new IllegalArgumentException();
        this.gainFactor = gainFactor;
        this.durability = durability;
    }

    public double gainFactor() {
        return gainFactor;
    }

    public int durability() {
        return durability;
    }

    @Override
    public boolean isWorking() {
        return durability > 0 && !idle;
    }

    @Override
    public boolean isBroken() {
        return durability == 0;
    }

    public boolean isIdle() {
        return idle && durability > 0;
    }

    public void use() {
        if (durability > 0) durability--;
        idle = false;
    }

    public PickaxeToken useWith(Token token) {
        if (token instanceof GoldToken) {
            idle = false;
            if (durability > 0) durability--;
        } else {
            idle = true;
        }
        return this;
    }

    @Override
    public void repair() {
        durability += 1;
    }

    public PickaxeToken ifWorking(Runnable r) {
        if (isWorking()) r.run();
        return this;
    }

    public PickaxeToken ifBroken(Runnable r) {
        if (isBroken()) r.run();
        return this;
    }

    public PickaxeToken ifIdle(Runnable r) {
        if (isIdle()) r.run();
        return this;
    }
}

