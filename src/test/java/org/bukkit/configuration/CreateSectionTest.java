package org.bukkit.configuration;

import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateSectionTest extends MemoryConfigurationTest {
    @Test
    public void testSectionMap() {
        Configuration config = getConfig();
        Map<String, Object> testMap = getTestValues();

        config.createSection("test.path", testMap);

        assertEquals(testMap, config.getConfigurationSection("test.path").getValues(false));
    }

    //Map in a map will never become true, since sections aren't recursively translated as maps.
//    @Test
//    public void testMapInAMap() {
//        Configuration config = getConfig();
//        Map<String, Object> testMap = getTestValues();
//        testMap.put("mapinamap", getTestValues());
//
//        config.createSection("test.path", testMap);
//
//        assertEquals(testMap, config.getConfigurationSection("test.path").getValues(false));
//    }

    @Test
    public void testSectionInAMap() {
        Configuration config = getConfig();
        Map<String, Object> testMap = getTestValues();

        ConfigurationSection section = config.createSection("test.path", testMap).createSection("sectioninamap", testMap);
        testMap.put("sectioninamap", section);

        assertEquals(testMap, config.getConfigurationSection("test.path").getValues(false));
    }
}
