package org.bukkit.chat;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Achievement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.chat.Hover.Type;
import org.bukkit.inventory.ItemStack;
import org.junit.Test;

public class HoverTest {

    @Test
    public void testSerialization() {
        for (Type type : Type.values()) {
            switch (type) {
                case SHOW_ACHIEVEMENT:
                    testSerializationShowAchievement();
                    break;
                case SHOW_ITEM:
                    testSerializationShowItem();
                    break;
                case SHOW_TEXT:
                    testSerializationShowText();
                    break;
                default:
                    throw new IllegalArgumentException("Should never be here!");
            }
        }
    }

    private void testSerializationShowAchievement() {
        final Hover hover = Hover.of(Achievement.BAKE_CAKE);
        final Hover hover2 = Hover.deserialize(hover.serialize());
        assertEquals(hover, hover2);
    }

    private void testSerializationShowItem() {
        final Hover hover = Hover.of(new ItemStack(Material.COOKIE, 42));
        final Hover hover2 = Hover.deserialize(hover.serialize());
        assertEquals(hover, hover2);
    }

    private void testSerializationShowText() {
        final Hover hover = Hover.of("Bukkit is awesome!", "Testing\nImproved code!", ChatColor.RED + "No joke! (:-P)");
        Map<String, Object> map = new HashMap<String, Object>(hover.serialize());
        final Hover hover2 = Hover.deserialize(map);
        assertEquals(hover, hover2);
        map.put("object", Arrays.asList(hover.getText()));
        final Hover hover3 = Hover.deserialize(map);
        assertEquals(hover, hover3);
        map.put("object", "Bukkit is awesome!");
        Hover.deserialize(map);
    }
}
