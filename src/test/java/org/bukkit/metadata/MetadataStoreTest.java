package org.bukkit.metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.TestPlugin;
import org.junit.Test;

public class MetadataStoreTest {
    private Plugin pluginX = new TestPlugin("x");
    private Plugin pluginY = new TestPlugin("y");

    TValueStore store = new TValueStore();

    @Test
    public void testMetadataStore() {
        store.setMetadata(new TValue("subject"), "key", new FixedMetadataValue(pluginX, 10));

        assertTrue(store.hasMetadata(new TValue("subject"), "key"));
        List<MetadataValue> values = store.getMetadata(new TValue("subject"), "key");
        assertEquals(10, values.get(0).value());
    }

    @Test
    public void testMetadataNotPresent() {
        assertFalse(store.hasMetadata(new TValue("subject"), "key"));
        List<MetadataValue> values = store.getMetadata(new TValue("subject"), "key");
        assertTrue(values.isEmpty());
    }

    @Test
    public void testInvalidateAll() {
        final Counter counter = new Counter();

        store.setMetadata(new TValue("subject"), "key", new LazyMetadataValue(pluginX, new Callable<Object>() {
            public Object call() throws Exception {
                counter.increment();
                return 10;
            }
        }));

        assertTrue(store.hasMetadata(new TValue("subject"), "key"));
        store.getMetadata(new TValue("subject"), "key").get(0).value();
        store.invalidateAll(pluginX);
        store.getMetadata(new TValue("subject"), "key").get(0).value();
        assertEquals(2, counter.value());
    }

    @Test
    public void testInvalidateAllButActuallyNothing() {
        final Counter counter = new Counter();

        store.setMetadata(new TValue("subject"), "key", new LazyMetadataValue(pluginX, new Callable<Object>() {
            public Object call() throws Exception {
                counter.increment();
                return 10;
            }
        }));

        assertTrue(store.hasMetadata(new TValue("subject"), "key"));
        store.getMetadata(new TValue("subject"), "key").get(0).value();
        store.invalidateAll(pluginY);
        store.getMetadata(new TValue("subject"), "key").get(0).value();
        assertEquals(1, counter.value());
    }

    @Test
    public void testMetadataReplace() {
        store.setMetadata(new TValue("subject"), "key", new FixedMetadataValue(pluginX, 10));
        store.setMetadata(new TValue("subject"), "key", new FixedMetadataValue(pluginY, 10));
        store.setMetadata(new TValue("subject"), "key", new FixedMetadataValue(pluginX, 20));

        for (MetadataValue mv : store.getMetadata(new TValue("subject"), "key")) {
            if (mv.getOwningPlugin().equals(pluginX)) {
                assertEquals(20, mv.value());
            }
            if (mv.getOwningPlugin().equals(pluginY)) {
                assertEquals(10, mv.value());
            }
        }
    }

    @Test
    public void testMetadataRemove() {
        store.setMetadata(new TValue("subject"), "key", new FixedMetadataValue(pluginX, 10));
        store.setMetadata(new TValue("subject"), "key", new FixedMetadataValue(pluginY, 20));
        store.removeMetadata(new TValue("subject"), "key", pluginX);

        assertTrue(store.hasMetadata(new TValue("subject"), "key"));
        assertEquals(1, store.getMetadata(new TValue("subject"), "key").size());
        assertEquals(20, store.getMetadata(new TValue("subject"), "key").get(0).value());
    }

    @Test
    public void testMetadataRemoveLast() {
        store.setMetadata(new TValue("subject"), "key", new FixedMetadataValue(pluginX, 10));
        store.removeMetadata(new TValue("subject"), "key", pluginX);

        assertFalse(store.hasMetadata(new TValue("subject"), "key"));
        assertEquals(0, store.getMetadata(new TValue("subject"), "key").size());
    }

    @Test
    public void testMetadataRemoveForNonExistingPlugin() {
        store.setMetadata(new TValue("subject"), "key", new FixedMetadataValue(pluginX, 10));
        store.removeMetadata(new TValue("subject"), "key", pluginY);

        assertTrue(store.hasMetadata(new TValue("subject"), "key"));
        assertEquals(1, store.getMetadata(new TValue("subject"), "key").size());
        assertEquals(10, store.getMetadata(new TValue("subject"), "key").get(0).value());
    }

    @Test
    public void testHasMetadata() {
        store.setMetadata(new TValue("subject"), "key", new FixedMetadataValue(pluginX, 10));
        assertTrue(store.hasMetadata(new TValue("subject"), "key"));
        assertFalse(store.hasMetadata(new TValue("subject"), "otherKey"));
    }

    @Test
    public void testProviderUsage() {
        List<MetadataProvider<TValue>> mapping = new ArrayList<MetadataProvider<TValue>>();
        TValueStore store = new TValueStore(mapping);
        assertEquals(store.getMetadata(new TValue("foobar"), "uppercased").size(), 0);
        TValueProvider p = new TValueProvider();
        mapping.add(p);
        assertEquals(0, p.counter.value());
        List<MetadataValue> values = store.getMetadata(new TValue("foobar"), "uppercased");
        assertEquals(1, values.size());
        assertEquals("FOOBAR", values.get(0).asString());
        assertEquals(1, p.counter.value());
        // Check we still get a single value only
        values = store.getMetadata(new TValue("foobar"), "uppercased");
        assertEquals(1, values.size());
        assertEquals("FOOBAR", values.get(0).asString());
        // Check the provider wasn't called twice.
        assertEquals(1, p.counter.value());
        // Try a new previously un-seen value through the provider.
        assertEquals(1, store.getMetadata(new TValue("hello"), "uppercased").size());
        assertEquals(2, p.counter.value());
        mapping.clear();
    }

    private static class TValue implements Metadatable {
        public String value;

        public TValue(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof TValue) {
                return value.equals(((TValue) other).value);
            } else if (other instanceof String) {
                return value.equals(other);
            } else {
                return false;
            }
        }
        
        @Override
        public int hashCode() {
            return value.hashCode();
        }

        public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        }

        public List<MetadataValue> getMetadata(String metadataKey) {
            return null;
        }

        public boolean hasMetadata(String metadataKey) {
            return false;
        }

        public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        }

    }

    private static class TValueStore extends MetadataStoreBase<TValue> implements MetadataStore<TValue> {
        public TValueStore() {
            this(new ArrayList<MetadataProvider<TValue>>());
        }

        public TValueStore(List<MetadataProvider<TValue>> mapping) {
            super(convertMap(mapping));
        }

        private static Map<Class<? extends Metadatable>, List<MetadataProvider<TValue>>> convertMap(List<MetadataProvider<TValue>> mapping) {
            Map<Class<? extends Metadatable>, List<MetadataProvider<TValue>>> lookup = new HashMap<Class<? extends Metadatable>, List<MetadataProvider<TValue>>>();
            lookup.put(TValue.class, mapping);
            return lookup;
        }

        @Override
        protected String disambiguate(TValue subject, String metadataKey) {
            return subject.value + ":" + metadataKey;
        }
    }

    private class TValueProvider implements MetadataProvider<TValue> {
        public final Counter counter = new Counter();

        public MetadataValue getValue(TValue subject, String key) {
            counter.increment();
            if (subject.equals("nodata")) {
                return null;
            } else {
                return new FixedMetadataValue(pluginX, subject.value.toUpperCase());
            }
        }

    }

    private class Counter {
        int c = 0;

        public void increment() {
            c++;
        }

        public int value() {
            return c;
        }
    }
}
