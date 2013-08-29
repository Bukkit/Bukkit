package org.bukkit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class EffectTest {
    @Test
    public void getById() {
        for (Effect effect : Effect.values()) {
            if (effect.getType() != Effect.Type.PARTICLE) {
                assertThat(Effect.getById(effect.getIdInt()), is(effect));
            } else {
                assertThat(Effect.getByName(effect.getIdString()), is(effect));
            }
        }
    }
}
