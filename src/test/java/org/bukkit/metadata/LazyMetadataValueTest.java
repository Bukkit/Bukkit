package org.bukkit.metadata;

import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.*;

/**
 */
public class LazyMetadataValueTest {

    @Test
    public void testLazyInt() {
        LazyMetadataValue metadataValue = new LazyMetadataValue(null, new Callable<Object>() {
            public Object call() throws Exception {
                return 10;
            }
        });

        assertEquals(metadataValue.asInt(), 10);
    }

    @Test
    public void testLazyDouble() {
        LazyMetadataValue metadataValue = new LazyMetadataValue(null, new Callable<Object>() {
            public Object call() throws Exception {
                return 10.5;
            }
        });

        assertEquals(metadataValue.asDouble(), 10.5, 0.01);
    }

    @Test
    public void testLazyString() {
        LazyMetadataValue metadataValue = new LazyMetadataValue(null, new Callable<Object>() {
            public Object call() throws Exception {
                return "TEN";
            }
        });

        assertEquals(metadataValue.asString(), "TEN");
    }

    @Test
    public void testCacheStrategyCacheAfterFirstEval() {
        final Counter evals = new Counter();
        LazyMetadataValue metadataValue = new LazyMetadataValue(null, LazyMetadataValue.CacheStrategy.CACHE_AFTER_FIRST_EVAL, new Callable<Object>() {
            public Object call() throws Exception {
                evals.increment();
                return 10;
            }
        });

        metadataValue.asInt();
        metadataValue.asInt();
        assertEquals(metadataValue.asInt(), 10);
        assertEquals(evals.value(), 1);

        metadataValue.invalidate();
        metadataValue.asInt();
        assertEquals(evals.value(), 2);
    }

    @Test
    public void testCacheStrategyNeverCache() {
        final Counter evals = new Counter();
        LazyMetadataValue metadataValue = new LazyMetadataValue(null, LazyMetadataValue.CacheStrategy.NEVER_CACHE, new Callable<Object>() {
            public Object call() throws Exception {
                evals.increment();
                return 10;
            }
        });

        metadataValue.asInt();
        metadataValue.asInt();
        assertEquals(metadataValue.asInt(), 10);
        assertEquals(evals.value(), 3);
    }

    @Test
    public void testCacheStrategyEternally() {
        final Counter evals = new Counter();
        LazyMetadataValue metadataValue = new LazyMetadataValue(null, LazyMetadataValue.CacheStrategy.CACHE_ETERNALLY, new Callable<Object>() {
            public Object call() throws Exception {
                evals.increment();
                return 10;
            }
        });

        metadataValue.asInt();
        metadataValue.asInt();
        assertEquals(evals.value(), 1);

        metadataValue.invalidate();
        metadataValue.asInt();
        assertEquals(evals.value(), 1);
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
