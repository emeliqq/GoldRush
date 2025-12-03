package edu.io.token;

import edu.io.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SluiceboxTest {

    @Test
    void default_values_are_correct() {
        var s = new SluiceboxToken();
        Assertions.assertEquals(1.2, s.gainFactor(), 0.0001);
        Assertions.assertEquals(5, s.durability());
        Assertions.assertTrue(s.isWorking());
        Assertions.assertFalse(s.isBroken());
    }

    @Test
    void gainFactor_decreases_per_use() {
        var s = new SluiceboxToken();
        s.useWith(new GoldToken());
        Assertions.assertEquals(1.16, s.gainFactor(), 0.0001);
    }

    @Test
    void durability_decreases_per_use() {
        var s = new SluiceboxToken();
        s.useWith(new GoldToken());
        Assertions.assertEquals(4, s.durability());
    }

    @Test
    void breaks_after_5_uses() {
        var s = new SluiceboxToken();
        for (int i = 0; i < 5; i++) {
            s.useWith(new GoldToken());
        }
        Assertions.assertTrue(s.isBroken());
    }

    @Test
    void player_collects_more_gold_with_sluicebox() {
        var player = new Player();
        player.interactWithToken(new SluiceboxToken());
        player.interactWithToken(new GoldToken(2.0));
        Assertions.assertEquals(2.0 * 1.2, player.gold.amount(), 0.0001);
    }

    @Test
    void anvil_does_not_repair_sluicebox() {
        var player = new Player();
        var s = new SluiceboxToken();
        player.interactWithToken(s);

        s.useWith(new GoldToken()); // durability 4

        player.interactWithToken(new AnvilToken());

        Assertions.assertEquals(4, s.durability());
    }
}
