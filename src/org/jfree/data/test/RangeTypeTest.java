package org.jfree.data.test;

import junit.framework.TestCase;
import org.jfree.data.RangeType;

public class RangeTypeTest extends TestCase {

    public void testToString() {
        assertEquals("RangeType.FULL should have toString() value 'RangeType.FULL'", "RangeType.FULL", RangeType.FULL.toString());
        assertEquals("RangeType.POSITIVE should have toString() value 'RangeType.POSITIVE'", "RangeType.POSITIVE", RangeType.POSITIVE.toString());
        assertEquals("RangeType.NEGATIVE should have toString() value 'RangeType.NEGATIVE'", "RangeType.NEGATIVE", RangeType.NEGATIVE.toString());
    }

    public void testEquals() {
        assertTrue("RangeType.FULL should be equal to itself", RangeType.FULL.equals(RangeType.FULL));
        assertTrue("RangeType.POSITIVE should be equal to itself", RangeType.POSITIVE.equals(RangeType.POSITIVE));
        assertTrue("RangeType.NEGATIVE should be equal to itself", RangeType.NEGATIVE.equals(RangeType.NEGATIVE));

        assertFalse("RangeType.FULL should not be equal to RangeType.POSITIVE", RangeType.FULL.equals(RangeType.POSITIVE));
        assertFalse("RangeType.FULL should not be equal to RangeType.NEGATIVE", RangeType.FULL.equals(RangeType.NEGATIVE));
        assertFalse("RangeType.POSITIVE should not be equal to RangeType.NEGATIVE", RangeType.POSITIVE.equals(RangeType.NEGATIVE));
    }

    public void testHashCode() {
        assertEquals("RangeType.FULL should have the same hashCode() as 'RangeType.FULL'.hashCode()", "RangeType.FULL".hashCode(), RangeType.FULL.hashCode());
        assertEquals("RangeType.POSITIVE should have the same hashCode() as 'RangeType.POSITIVE'.hashCode()", "RangeType.POSITIVE".hashCode(), RangeType.POSITIVE.hashCode());
        assertEquals("RangeType.NEGATIVE should have the same hashCode() as 'RangeType.NEGATIVE'.hashCode()", "RangeType.NEGATIVE".hashCode(), RangeType.NEGATIVE.hashCode());
    }
}
