package org.bukkit.util;

import java.util.Iterator;
import java.util.List;

public class CollectionUtil {
    /**
     * Removes all elements of the given list which do not start with the
     * specified search string.
     *
     * @param needle String to search for
     * @param haystack List of strings to filter
     * @return the modified list of strings
     * @throws UnsupportedOperationException if the list is immutable and
     * contains a string which does not start with the specified search string.
     */
    public static List<String> filterPartialMatches(String needle, List<String> haystack) {
        Iterator<String> iterator = haystack.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (!startsWithIgnoreCase(s, needle)) {
                iterator.remove();
            }
        }

        return haystack;
    }

    private static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str.length() < prefix.length()) {
            return false;
        }
        return str.substring(0, prefix.length()).equalsIgnoreCase(prefix);
    }
}
