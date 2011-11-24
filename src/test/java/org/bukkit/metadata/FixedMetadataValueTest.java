package org.bukkit.metadata;

import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.*;

/**
 */
public class FixedMetadataValueTest {
    @Test
    public void fixedIntTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, 10);
        assertEquals(metadataValue.asInt(), 10);
    }

    @Test
    public void fixedDoubleTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, 10.5);
        assertEquals(metadataValue.asDouble(), 10.5, 0.01);
    }

    @Test
    public void fixedStringTest() {
        FixedMetadataValue metadataValue = new FixedMetadataValue(null, "TEN");
        assertEquals(metadataValue.asString(), "TEN");
    }
}
