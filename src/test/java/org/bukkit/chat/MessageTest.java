package org.bukkit.chat;

import static org.junit.Assert.*;

import org.bukkit.Achievement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Test;

public class MessageTest {

    @Test
    public void testClone() {
        final Message message = Message.of("Bukkit is awesome!")
                .append(new ItemStack(Material.COOKIE, 42), ChatColor.RED + "Here your awesome cookie!")
                .append(Achievement.BAKE_CAKE, ChatColor.UNDERLINE + "Here your awesome cake!")
                .append(Click.ofOpenUrl("http://www.bukkit.org"), "Click here for more information.");
        assertEquals(message, message.clone());
    }

    @Test
    public void testSerialization() {
        final Message message = Message.of("Bukkit is awesome!")
                .append(new ItemStack(Material.COOKIE, 42), ChatColor.RED + "Here your awesome cookie!")
                .append(Achievement.BAKE_CAKE, ChatColor.UNDERLINE + "Here your awesome cake!")
                .append(Click.ofOpenUrl("http://www.bukkit.org"), "Click here for more information.");
        final Message message2 = Message.deserialize(message.serialize());
        assertEquals(message, message2);
    }
}
