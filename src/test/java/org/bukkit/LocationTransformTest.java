package org.bukkit;

import org.bukkit.util.Vector;
import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LocationTransformTest {
    @Test
    public void testTransform() throws Exception {
        final Location[] references = {
                new Location(null, 1, 2, 3, 4, 5),
                new Location(null, 5, 4, 3, 2, 1),
                new Location(null, 827, 2834, 65981, 36, 51),
        };

        final Vector[] vectors =  {
                new Vector(2323, 666, 42),
                new Vector(123, 456, 789),
                new Vector(1e-6, 0, 1e-6),
                new Vector(1e6, 0, 1e6),
                new Vector(1234, 567, 89),
        };

        for (Location reference : references) {
            testTransform(reference);
            for (Vector vector : vectors) {
                testTransform(reference, vector);
            }
        }

        for (Vector vector : vectors) {
            testTransform(vector);
        }
    }

    private void testTransform(Vector vector) {
        // Test conformity with the untransformed coordinate system
        final Location reference = new Location(null, 0, 0, 0, 0, 0);
        assertVectorEquals(vector, reference.toLocalAxis(vector));
        assertVectorEquals(vector, reference.toWorldAxis(vector));
        assertVectorEquals(vector, reference.toLocal(vector));
        assertVectorEquals(vector, reference.toWorld(vector));
    }

    private void testTransform(Location reference, Vector vector) {
        // Test inverse functions
        assertVectorEquals(vector, reference.toWorldAxis(reference.toLocalAxis(vector)));
        assertVectorEquals(vector, reference.toLocalAxis(reference.toWorldAxis(vector)));
        assertVectorEquals(vector, reference.toWorld(reference.toLocal(vector)));
        assertVectorEquals(vector, reference.toLocal(reference.toWorld(vector)));
        assertThat(reference.toWorldAxis(vector).length(), is(closeTo(vector.length(), 1e-12)));
    }

    private void testTransform(Location reference) {
        // Test conformity with Location.getDirection()
        assertVectorEquals(reference.getDirection(), reference.toWorldAxis(new Vector(0, 0, 1)));
    }

    /**
     * Tests vector equality more accurately than {@link org.bukkit.util.Vector#equals(Object)}
     *
     * @param expected expected value
     * @param actual the value to check against <code>expected</code>
     */
    private void assertVectorEquals(Vector expected, Vector actual) {
        assertThat(actual.getX(), is(closeTo(expected.getX(), 1e-9)));
        assertThat(actual.getY(), is(closeTo(expected.getY(), 1e-9)));
        assertThat(actual.getZ(), is(closeTo(expected.getZ(), 1e-9)));
    }
}
