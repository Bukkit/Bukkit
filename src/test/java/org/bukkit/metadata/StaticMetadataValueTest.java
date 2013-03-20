package org.bukkit.metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.TestPlugin;
import org.junit.Test;

public class StaticMetadataValueTest {
    private Plugin plugin = new TestPlugin("X");
    private StaticMetadataValue subject;

    @Test
    public void testNumberTypes() {
        subject = new StaticMetadataValue(plugin, new Integer(5));
        assertEquals(new Integer(5), subject.value());
        assertEquals(5, subject.asInt());
        assertEquals(true, subject.asBoolean());
        assertEquals(5, subject.asByte());
        assertEquals(5.0, subject.asFloat(), 0.1e-8);
        assertEquals(5L, subject.asLong());
        assertEquals(5, subject.asShort());
        assertEquals("5", subject.asString());
    }

    @Test
    public void testInvalidateDoesNothing() {
        Object o = new Object();
        subject = new StaticMetadataValue(plugin, o);
        subject.invalidate();
        assertSame(o, subject.value());
    }
}
