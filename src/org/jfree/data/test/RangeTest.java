package org.jfree.data.test;

import junit.framework.TestCase;
import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest extends TestCase {

    private Range rUnderTest;

    @Before
    public void setUp() throws Exception {
        rUnderTest = new Range(-1, 1);
    }

    @Test
    public void testGetLowerBound() {
        assertEquals(-1, rUnderTest.getLowerBound(), 0.000000001d);
    }

    @Test
    public void testGetUpperBound() {
        assertEquals(1, rUnderTest.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testGetLength() {
        assertEquals(2, rUnderTest.getLength(), 0.000000001d);
    }

    @Test
    public void testGetCentralValue() {
        assertEquals(0, rUnderTest.getCentralValue(), 0.000000001d);
    }

    @Test
    public void testContainsValueInMiddle() {
        assertTrue(rUnderTest.contains(0));
    }

    @Test
    public void testContainsLowerBound() {
        assertTrue(rUnderTest.contains(-1));
    }

    @Test
    public void testContainsUpperBound() {
        assertTrue(rUnderTest.contains(1));
    }

    @Test
    public void testContainsValueBelowLowerBound() {
        assertFalse(rUnderTest.contains(-2));
    }

    @Test
    public void testContainsValueAboveUpperBound() {
        assertFalse(rUnderTest.contains(2));
    }

    @Test
    public void testContainsNaN() {
        assertFalse(rUnderTest.contains(Double.NaN));
    }

    @Test
    public void testContainsPositiveInfinity() {
        assertFalse(rUnderTest.contains(Double.POSITIVE_INFINITY));
    }

    @Test
    public void testContainsNegativeInfinity() {
        assertFalse(rUnderTest.contains(Double.NEGATIVE_INFINITY));
    }

    @Test()
    public void testConstructorWithInvalidBounds() {
        try {
            new Range(1, -1);
        } catch (IllegalArgumentException e) {
            //Expect
        }
    }

    @After
    public void tearDown() throws Exception {
    }

}
