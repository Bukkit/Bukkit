package org.bukkit.metadata;

import static org.junit.Assert.assertEquals;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.TestPlugin;
import org.junit.Test;

public class MetadataValueAdapterTest {
    private TestPlugin plugin = new TestPlugin("x");

    @Test
    public void testIncrementingAdapter() {
        IncrementingMetaValue mv = new IncrementingMetaValue(plugin);
        // check getOwningPlugin
        assertEquals(mv.getOwningPlugin(), this.plugin);

        // check the various value-making methods
        assertEquals(mv.asInt(), 1);
        assertEquals(mv.asLong(), 2L);
        assertEquals(mv.asFloat(), 3.0, 0.001);
        assertEquals(mv.asByte(), 4);
        assertEquals(mv.asDouble(), 5.0, 0.001);
        assertEquals(mv.asShort(), 6);
        assertEquals(mv.asString(), "7");
    }

    /** Silly Metadata implementation that increments every time value() is called */
    class IncrementingMetaValue extends MetadataValueAdapter {
        private int internalValue = 0;

        protected IncrementingMetaValue(Plugin owningPlugin) {
            super(owningPlugin);
        }

        public Object value() {
            return ++internalValue;
        }

        public void invalidate() {
            internalValue = 0;
        }
    }
}
