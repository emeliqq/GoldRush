package edu.io.player;

public class NoTool implements Tool {
    @Override
    public boolean isWorking() {
        return false;
    }

    @Override
    public boolean isBroken() {
        return true;
    }
}
