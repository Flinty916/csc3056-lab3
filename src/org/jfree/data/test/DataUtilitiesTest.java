package org.jfree.data.test;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DataUtilitiesTest {

    private Values2D values2D;

    @Before
    public void setUp() {
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
        values2D = testValues;
        testValues.addValue(1, 0, 0);
        testValues.addValue(4, 1, 0);
    }

    @After
    public void tearDown() {
        values2D = null;
    }

    @Test
    public void testValidDataAndColumnColumnTotal() {
        assertEquals("Wrong sum returned. It should be 5.0",
                5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
    }

    @Test
    public void testNullColumnColumnTotal() {
        Values2D nullValues = new DefaultKeyedValues2D();
        assertEquals("Wrong sum returned. It should be 0.0",
                0.0, DataUtilities.calculateColumnTotal(nullValues, 0), 0.0000001d);
    }

    @Test
    public void testNullRowRowTotal() {
        Values2D nullValues = new DefaultKeyedValues2D();
        assertEquals("Wrong sum returned. It should be 0.0",
                0.0, DataUtilities.calculateRowTotal(nullValues, 0), 0.0000001d);
    }

    @Test
    public void testNegativeColumnColumnTotal() {
        DefaultKeyedValues2D negativeValues = new DefaultKeyedValues2D();
        negativeValues.addValue(-1, 0, 0);
        negativeValues.addValue(-4, 1, 0);
        assertEquals("Wrong sum returned. It should be -5.0",
                -5.0, DataUtilities.calculateColumnTotal(negativeValues, 0), 0.0000001d);
    }

    @Test
    public void testNegativeRowRowTotal() {
        DefaultKeyedValues2D negativeValues = new DefaultKeyedValues2D();
        negativeValues.addValue(-1, 0, 0);
        negativeValues.addValue(-4, 0, 1);
        assertEquals("Wrong sum returned. It should be -5.0",
                -5.0, DataUtilities.calculateRowTotal(negativeValues, 0), 0.0000001d);
    }

    @Test
    public void testMixedColumnColumnTotal() {
        DefaultKeyedValues2D mixedValues = new DefaultKeyedValues2D();
        mixedValues.addValue(1, 0, 0);
        mixedValues.addValue(-4, 1, 0);
        assertEquals("Wrong sum returned. It should be -3.0",
                -3.0, DataUtilities.calculateColumnTotal(mixedValues, 0), 0.0000001d);
    }

    @Test
    public void testCreateNumberArray() {
        double[] testData = {1.0, 2.0};
        try {
            Number[] result = DataUtilities.createNumberArray(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // expected result
        }
    }

    @Test
    public void testCreateNumberArray2D () {
        double[][] testData = {{1.0, 2.0}, {3.0, 4.0, 5.0}};
        try {
            Number[][] result = DataUtilities.createNumberArray2D(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // expected result
        }

        try {
            Number[][] result = DataUtilities.createNumberArray2D(testData);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // expected result
        }
    }

    @Test
    public void testGetCumulativePercentages () {
        DefaultKeyedValues testValues = new DefaultKeyedValues();
        testValues.addValue("A", 10.0);
        testValues.addValue("B", 20.0);
        testValues.addValue("C", 30.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(testValues);
        assertEquals("Wrong percentage for A", 0.16666666666666666, result.getValue(0).doubleValue(), 0.0000001d);
        assertEquals("Wrong percentage for B", 0.5, result.getValue(1).doubleValue(), 0.0000001d);
        assertEquals("Wrong percentage for C", 1.0, result.getValue(2).doubleValue(), 0.0000001d);
    }

    @Test
    public void testGetCumulativePercentages_NullInput () {
        try {
            DataUtilities.getCumulativePercentages(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // expected result
        }
    }
}