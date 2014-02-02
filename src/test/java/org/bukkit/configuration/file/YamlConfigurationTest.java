package org.bukkit.configuration.file;

import org.junit.Test;
import static org.junit.Assert.*;

public class YamlConfigurationTest extends FileConfigurationTest {

    @Override
    public YamlConfiguration getConfig() {
        return new YamlConfiguration();
    }

    @Override
    public String getTestHeaderInput() {
        return "This is a sample\nheader.\n\nNewline above should be commented.\n\n";
    }

    @Override
    public String getTestHeaderResult() {
        return "# This is a sample" + LINE_SEPARATOR + "# header." + LINE_SEPARATOR + "# " + LINE_SEPARATOR + "# Newline above should be commented." + LINE_SEPARATOR + LINE_SEPARATOR;
    }

    @Override
    public String getTestValuesString() {
        return "integer: -2147483648" + LINE_SEPARATOR +
            "string: String Value" + LINE_SEPARATOR +
            "long: 9223372036854775807" + LINE_SEPARATOR +
            "true-boolean: true" + LINE_SEPARATOR +
            "false-boolean: false" + LINE_SEPARATOR +
            "vector:" + LINE_SEPARATOR +
            "  ==: Vector" + LINE_SEPARATOR +
            "  x: 12345.67" + LINE_SEPARATOR +
            "  y: 64.0" + LINE_SEPARATOR +
            "  z: -12345.6789" + LINE_SEPARATOR +
            "list:" + LINE_SEPARATOR +
            "- 1" + LINE_SEPARATOR +
            "- 2" + LINE_SEPARATOR +
            "- 3" + LINE_SEPARATOR +
            "- 4" + LINE_SEPARATOR +
            "- 5" + LINE_SEPARATOR +
            "'42': The Answer" + LINE_SEPARATOR;
    }

    @Test
    public void testSaveToStringWithIndent() {
        YamlConfiguration config = getConfig();
        config.options().indent(9);

        config.set("section.key", 1);

        String result = config.saveToString();
        String expected = "section:" + LINE_SEPARATOR + "         key: 1" + LINE_SEPARATOR;

        assertEquals(expected, result);
    }
}
