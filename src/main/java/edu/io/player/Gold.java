package edu.io.player;

public class Gold {

    private double amount = 0.0;

    public Gold() {}

    public Gold(double amount) {
        if (amount < 0) throw new IllegalArgumentException();
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }

    public void gain(double amt) {
        if (amt < 0) throw new IllegalArgumentException();
        amount += amt;
    }

    public void lose(double amt) {
        if (amt < 0) throw new IllegalArgumentException();
        if (amount - amt < 0) throw new IllegalArgumentException();
        amount -= amt;
    }
}
