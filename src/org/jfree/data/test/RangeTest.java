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

    //GetLength
    @Test
        public void testGetLength() {
        assertEquals(2, rUnderTest.getLength(), 0.000000001d);
    }

    @Test
    public void testGetLengthWithNegativeBounds() {
        Range r = new Range(-5, -1);
        assertEquals(4, r.getLength(), 0.000000001d);
    }

    //GetCentralValue
    @Test
    public void testGetCentralValue() {
        assertEquals(0, rUnderTest.getCentralValue(), 0.000000001d);
    }

    @Test
    public void testGetCentralValueWithOddLength() {
        Range r = new Range(1, 5);
        assertEquals(3, r.getCentralValue(), 0.000000001d);
    }

    @Test
    public void testGetCentralValueWithEvenLength() {
        Range r = new Range(1, 4);
        assertEquals(2.5, r.getCentralValue(), 0.000000001d);
    }

    //Contains
    @Test
    public void testContainsValueInMiddle() {
        assertTrue(rUnderTest.contains(0));
    }

    @Test
    public void testContainsLowerBound() {
        assertTrue(rUnderTest.contains(-1));
    }

    @Test
    public void testGetLowerBoundNegativeUpperBound() {
        Range r = new Range(-3, 2);
        assertEquals(-3, r.getLowerBound(), 0.000000001d);
    }

    @Test
    public void testContainsWithEqualBounds() {
        Range r = new Range(1, 1);
        assertTrue(r.contains(1));
    }

    @Test
    public void testContainsUpperBound() {
        assertTrue(rUnderTest.contains(1));
    }

    @Test
    public void testGetUpperBoundNegativeLowerBound() {
        Range r = new Range(-2, 3);
        assertEquals(3, r.getUpperBound(), 0.000000001d);
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

    //Combine
    @Test
    public void testCombineWithOverlappingRanges() {
        Range range1 = new Range(-5, 5);
        Range range2 = new Range(0, 10);
        Range combinedRange = Range.combine(range1, range2);
        assertEquals(-5.0, combinedRange.getLowerBound(), 0.000000001d);
        assertEquals(10.0, combinedRange.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testCombineWithNonOverlappingRanges() {
        Range range1 = new Range(-5, 0);
        Range range2 = new Range(5, 10);
        Range combinedRange = Range.combine(range1, range2);
        assertEquals(-5.0, combinedRange.getLowerBound(), 0.000000001d);
        assertEquals(10.0, combinedRange.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testCombineWithSameRange() {
        Range r = new Range(1, 5);
        Range combinedRange = Range.combine(r, r);
        assertEquals(r, combinedRange);
    }

    //Shift
    @Test
    public void testShift() {
        Range shiftedRange = rUnderTest.shift(rUnderTest, 1);
        assertEquals(0, shiftedRange.getLowerBound(), 0.000000001d);
        assertEquals(2, shiftedRange.getUpperBound(), 0.000000001d);

        shiftedRange = rUnderTest.shift(rUnderTest, -1);
        assertEquals(-2, shiftedRange.getLowerBound(), 0.000000001d);
        assertEquals(0, shiftedRange.getUpperBound(), 0.000000001d);

        shiftedRange = rUnderTest.shift(rUnderTest, 0);
        assertEquals(-1, shiftedRange.getLowerBound(), 0.000000001d);
        assertEquals(1, shiftedRange.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testShiftWithPositiveDistance() {
        Range shiftedRange = rUnderTest.shift(rUnderTest, 1);
        assertEquals(0, shiftedRange.getLowerBound(), 0.000000001d);
        assertEquals(2, shiftedRange.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testShiftWithNegativeDistance() {
        Range shiftedRange = rUnderTest.shift(rUnderTest, -1);
        assertEquals(-2, shiftedRange.getLowerBound(), 0.000000001d);
        assertEquals(0, shiftedRange.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testShiftWithZeroLengthRange() {
        Range r = new Range(1, 1);
        Range shiftedRange = r.shift(r, 2);
        assertEquals(3, shiftedRange.getLowerBound(), 0.000000001d);
        assertEquals(3, shiftedRange.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testShiftToZero() {
        Range range = new Range(2, 4);
        Range shiftedRange = Range.shift(range, -2, true);
        assertEquals(0.0, shiftedRange.getLowerBound(), 0.000001d);
        assertEquals(2.0, shiftedRange.getUpperBound(), 0.000001d);
    }

    @Test
    public void testShiftNotToZero() {
        Range range = new Range(2, 4);
        Range shiftedRange = Range.shift(range, -2, false);
        assertEquals(0.0, shiftedRange.getLowerBound(), 0.000001d);
        assertEquals(2.0, shiftedRange.getUpperBound(), 0.000001d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShiftWithInvalidRange() {
        Range r = new Range(1, 5);
        r.shift(new Range(6, 8), 2);
    }

    //Intersect

    @Test
    public void testIntersects() {
        Range intersectingRange = new Range(-0.5, 0.5);
        assertTrue(rUnderTest.intersects(intersectingRange.getLowerBound(), intersectingRange.getUpperBound()));

        intersectingRange = new Range(0.5, 1.5);
        assertFalse(rUnderTest.intersects(intersectingRange.getLowerBound(), intersectingRange.getUpperBound()));

        intersectingRange = new Range(-1.5, -0.5);
        assertTrue(rUnderTest.intersects(intersectingRange.getLowerBound(), intersectingRange.getUpperBound()));

        Range nonIntersectingRange = new Range(2, 3);
        assertTrue(rUnderTest.intersects(nonIntersectingRange.getLowerBound(), nonIntersectingRange.getUpperBound()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntersectsWithInvalidRange() {
        Range r = new Range(1, 5);
        r.intersects(5, 1);
    }

    //Expand

    @Test
    public void testExpandToIncludeInsideRange() {
        Range originalRange = new Range(0, 5);
        Range expandedRange = Range.expandToInclude(originalRange, 2.5);
        assertEquals(originalRange, expandedRange);
    }

    @Test
    public void testExpandToIncludeOutsideRange() {
        Range originalRange = new Range(0, 5);
        Range expandedRange = Range.expandToInclude(originalRange, 7);
        assertEquals(new Range(0, 7), expandedRange);
    }

    @Test
    public void testExpandToIncludeWithExistingRange() {
        Range r = new Range(1, 5);
        Range expandedRange = Range.expandToInclude(r, 2);
        assertEquals(r, expandedRange);
    }

    //Equals

    @Test
    public void testEqualsSameObject() {
        assertTrue(rUnderTest.equals(rUnderTest));
    }

    @Test
    public void testEqualsNullObject() {
        assertFalse(rUnderTest.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(rUnderTest.equals(new Object()));
    }

    @Test
    public void testEqualsDifferentBounds() {
        Range otherRange = new Range(-2, 2);
        assertFalse(rUnderTest.equals(otherRange));
    }

    @Test
    public void testEqualsSameBounds() {
        Range otherRange = new Range(-1, 1);
        assertTrue(rUnderTest.equals(otherRange));
    }

    //Hash Code

    @Test
    public void testHashCode() {
        Range range1 = new Range(-1, 1);
        Range range2 = new Range(-1, 1);
        assertEquals(range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentObjects() {
        Range r1 = new Range(1, 5);
        Range r2 = new Range(-1, 3);
        assertNotSame(r1.hashCode(), r2.hashCode());
    }

    @Test
    public void testConstructorWithNaNBounds() {
        try {
            new Range(Double.NaN, Double.NaN);
        } catch (IllegalArgumentException e) {
            //Expect
        }
    }

    @Test
    public void testConstructorWithInfiniteBounds() {
        try {
            new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        } catch (IllegalArgumentException e) {
            //Expect
        }
    }

    //Constrain
    @Test
    public void testConstrainInsideRange() {
        Range range = new Range(0, 10);
        assertEquals(5.0, range.constrain(5.0), 0.0);
    }

    @Test
    public void testConstrainBelowRange() {
        Range range = new Range(0, 10);
        assertEquals(0.0, range.constrain(-5.0), 0.0);
    }

    @Test
    public void testConstrainAboveRange() {
        Range range = new Range(0, 10);
        assertEquals(10.0, range.constrain(15.0), 0.0);
    }

    //Expand
    @Test
    public void testExpandWithEqualMargins() {
        Range range = new Range(-1, 1);
        Range expandedRange = Range.expand(range, 0.5, 0.5);
        assertEquals(-2.0, expandedRange.getLowerBound(), 0.000000001d);
        assertEquals(2.0, expandedRange.getUpperBound(), 0.000000001d);
    }

    @Test
    public void testExpandWithDifferentMargins() {
        Range range = new Range(-1, 1);
        Range expandedRange = Range.expand(range, 0.25, 0.75);
        assertEquals(-1.5, expandedRange.getLowerBound(), 0.000000001d);
        assertEquals(2.5, expandedRange.getUpperBound(), 0.000000001d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpandWithInvalidRange() {
        try {
            rUnderTest.expand(null, 0, 1);
        } catch (IllegalArgumentException e) {
            //Expect
        }
    }

    //ToString

    @Test
    public void testToString() {
        Range range = new Range(-1, 1);
        String expected = "Range[-1.0,1.0]";
        assertEquals(expected, range.toString());
    }

    @After
    public void tearDown() throws Exception {
    }

}
