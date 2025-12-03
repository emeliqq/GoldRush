package edu.io.player;

public class Anvil implements Tool {

    @Override
    public boolean isWorking() {
        return true;
    }

    @Override
    public boolean isBroken() {
        return false;
    }
}
