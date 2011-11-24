package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;

import static org.junit.Assert.*;

/**
 */
public class MetadataStoreTest {

    @Test
    public void testMetadataStore() {
        StringMetadataStore mds = new StringMetadataStore();
        mds.setMetadata("subject", "key", new FixedMetadataValue(new MockPlugin(), 10));

        assertTrue(mds.hasMetadata("subject", "key"));
        List<MetadataValue> values = mds.getMetadata("subject", "key");
        assertEquals(values.get(0).asInt(), 10);
    }

    @Test
    public void testMetadataNotPresent() {
        StringMetadataStore mds = new StringMetadataStore();

        assertFalse(mds.hasMetadata("subject", "key"));
        List<MetadataValue> values = mds.getMetadata("subject", "key");
        assertTrue(values.isEmpty());
    }

    @Test
    public void testInvalidateAll() {
        StringMetadataStore mds = new StringMetadataStore();
        final Counter counter = new Counter();

        MockPlugin mockPlugin = new MockPlugin();

        mds.setMetadata("subject", "key", new LazyMetadataValue(mockPlugin, new Callable<Object>() {
            public Object call() throws Exception {
                counter.increment();
                return 10;
            }
        }));

        assertTrue(mds.hasMetadata("subject", "key"));
        mds.getMetadata("subject", "key").get(0).asInt();
        mds.getMetadata("subject", "key").get(0).asInt();
        mds.invalidateAll(mockPlugin);
        mds.getMetadata("subject", "key").get(0).asInt();
        mds.getMetadata("subject", "key").get(0).asInt();
        assertEquals(counter.value(), 2);
    }

    @Test
    public void testMetadataReplace() {
        StringMetadataStore mds = new StringMetadataStore();
        MockPlugin mockPlugin1 = new MockPlugin();
        MockPlugin mockPlugin2 = new MockPlugin();

        mds.setMetadata("subject", "key", new FixedMetadataValue(mockPlugin1, 10));
        mds.setMetadata("subject", "key", new FixedMetadataValue(mockPlugin2, 10));
        mds.setMetadata("subject", "key", new FixedMetadataValue(mockPlugin1, 20));

        for(MetadataValue mv : mds.getMetadata("subject", "key")) {
            if(mv.getOwningPlugin() == mockPlugin1) {
                assertEquals(mv.asInt(), 20);
            }
            if(mv.getOwningPlugin() == mockPlugin2) {
                assertEquals(mv.asInt(), 10);
            }
        }
    }

    @Test
    public void testMetadataRemove() {
        StringMetadataStore mds = new StringMetadataStore();
        MockPlugin mockPlugin1 = new MockPlugin();
        MockPlugin mockPlugin2 = new MockPlugin();

        mds.setMetadata("subject", "key", new FixedMetadataValue(mockPlugin1, 10));
        mds.setMetadata("subject", "key", new FixedMetadataValue(mockPlugin2, 20));
        mds.removeMetadata("subject", "key", mockPlugin1);

        assertTrue(mds.hasMetadata("subject", "key"));
        assertEquals(mds.getMetadata("subject", "key").size(), 1);
        assertEquals(mds.getMetadata("subject", "key").get(0).asInt(), 20);
    }

    private class StringMetadataStore extends MetadataStoreBase<String> implements MetadataStore<String> {
        @Override
        protected String Disambiguate(String subject, String metadataKey) {
            return subject + ":" + metadataKey;
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
