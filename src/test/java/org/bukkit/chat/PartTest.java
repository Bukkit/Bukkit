package org.bukkit.chat;

import static org.junit.Assert.*;

import org.junit.Test;

public class PartTest {

    @Test
    public void testClone() {
        final Part part = Part.of(Click.ofOpenUrl("http://www.bukkit.org"),
            "Click here for more information.",
            "&EClicking this will open http://www.bukkit.org with your browser!"
        );
        assertEquals(part, part.clone());
    }

    @Test
    public void testSerialization() {
        final Part part = Part.of(Click.ofOpenUrl("http://www.bukkit.org"),
            "Click here for more information.",
            "&EClicking this will open http://www.bukkit.org with your browser!"
        );
        final Part part2 = Part.deserialize(part.serialize());
        assertEquals(part, part2);
    }
}
