package org.bukkit.util;

import static org.junit.Assert.*;

import org.bukkit.ChatColor;
import org.junit.Test;

public class ChatPaginatorTest {
    @Test
    public void wordWrapTest() {
        // Second word length is one too large to fit in the line
        assertArrayEquals(new String[] {
                ChatColor.WHITE + "foo",
                ChatColor.WHITE + "bar"
        }, ChatPaginator.wordWrap("foo bar", 6));

        // Single-line wordWrapped text does not have ChatColor applied to it
        assertArrayEquals(new String[] {
                "foo bar"
        }, ChatPaginator.wordWrap("foo bar", 7));

        // Second word can't fit in first line; third word can't fit in second
        assertArrayEquals(new String[] {
                ChatColor.WHITE + "foo",
                ChatColor.WHITE + "bar",
                ChatColor.WHITE + "baz"
        }, ChatPaginator.wordWrap("foo bar baz", 6));

        // Third word can't fit in the first line
        assertArrayEquals(new String[] {
                ChatColor.WHITE + "foo bar",
                ChatColor.WHITE + "baz"
        }, ChatPaginator.wordWrap("foo bar baz", 7));

        // Words that are too long are forcibly broken up mid-word into new lines
        assertArrayEquals(new String[] {
                ChatColor.WHITE + "foobar",
                ChatColor.WHITE + "baz"
        }, ChatPaginator.wordWrap("foobarbaz", 6));
    }

    @Test
    public void wordWrapColorTest() {
        // ChatColor at beginning of line to be applied to each line
        // Additionally, "foo" without the ChatColor should be exactly the right line length
        assertArrayEquals(new String[] {
                ChatColor.GOLD + "foo",
                ChatColor.GOLD + "bar"
        }, ChatPaginator.wordWrap(ChatColor.GOLD + "foo bar", 3));

        // Mid-word ChatColor to be applied to the next line
        // Additionally, "foo" without the additional two ChatColors should be exactly the right line length
        assertArrayEquals(new String[] {
                ChatColor.GOLD + "fo" + ChatColor.YELLOW + "o",
                ChatColor.YELLOW + "bar"
        }, ChatPaginator.wordWrap(ChatColor.GOLD + "fo" + ChatColor.YELLOW + "o bar", 3));
    }
}
