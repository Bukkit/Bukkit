package org.bukkit.chat;

import static org.junit.Assert.*;

import org.bukkit.ChatColor;
import org.bukkit.chat.Click.Type;
import org.junit.Test;

public class ClickTest {

    @Test
    public void testSerialization() {
        for (Type type : Type.values()) {
            switch (type) {
                case OPEN_URL:
                    testSerializationOpenUrl();
                    break;
                case SEND_TEXT:
                    testSerializationSendText();
                    break;
                case SET_TEXT:
                    testSerializationSetText();
                    break;
                default:
                    throw new IllegalArgumentException("Should never be here!");
            }
        }
    }

    private void testSerializationOpenUrl() {
        final Click click = Click.ofOpenUrl("http://www.bukkit.org");
        final Click click2 = Click.deserialize(click.serialize());
        assertEquals(click, click2);
    }

    private void testSerializationSendText() {
        final Click click = Click.ofSendText("Bukkit is awesome! Testing\nImproved code!" + ChatColor.RED + "No joke! (:-P)");
        final Click click2 = Click.deserialize(click.serialize());
        assertEquals(click, click2);
    }

    private void testSerializationSetText() {
        final Click click = Click.ofSetText("Bukkit is awesome! Testing\nImproved code!" + ChatColor.RED + "No joke! (:-P)");
        final Click click2 = Click.deserialize(click.serialize());
        assertEquals(click, click2);
    }
}
