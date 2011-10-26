package org.bukkit.metadata;

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
        mds.addMetadata("subject", "key", new FixedMetadataValue(null, 10));

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

        mds.addMetadata("subject", "key", new LazyMetadataValue(null, new Callable<Object>() {
            public Object call() throws Exception {
                counter.increment();
                return 10;
            }
        }));

        assertTrue(mds.hasMetadata("subject", "key"));
        mds.getMetadata("subject", "key").get(0).asInt();
        mds.getMetadata("subject", "key").get(0).asInt();
        mds.invalidateAll(null);
        mds.getMetadata("subject", "key").get(0).asInt();
        mds.getMetadata("subject", "key").get(0).asInt();
        assertEquals(counter.value(), 2);
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
