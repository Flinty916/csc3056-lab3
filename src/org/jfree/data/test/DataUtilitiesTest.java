package org.jfree.data.test;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThrows;


public class DataUtilitiesTest {

    private DefaultKeyedValues2D createSampleData() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(10, "Row1", "Column1");
        data.addValue(20, "Row1", "Column2");
        data.addValue(30, "Row1", "Column3");
        data.addValue(40, "Row2", "Column1");
        data.addValue(50, "Row2", "Column2");
        data.addValue(60, "Row2", "Column3");
        data.addValue(70, "Row3", "Column1");
        data.addValue(80, "Row3", "Column2");
        data.addValue(90, "Row3", "Column3");

        return data;
    }

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

    // Test cases for calculateColumnTotal()
    @Test
    public void testCalculateColumnTotal_NullInput() {
        try {
            DataUtilities.calculateColumnTotal(null, 0);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // expected result
        }
    }

    @Test
    public void testCalculateColumnTotal_OutOfBounds() {
        DefaultKeyedValues2D data = createSampleData();
        int columnIndexOutOfBounds = -1;

        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            DataUtilities.calculateColumnTotal(data, columnIndexOutOfBounds);
        });

        String expectedMessage = "Column index out of bounds: " + columnIndexOutOfBounds;
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testCalculateColumnTotal_NullColumn() {
        DefaultKeyedValues2D nullValues = new DefaultKeyedValues2D();
        nullValues.addValue(null, 0, 0);
        nullValues.addValue(null, 1, 0);
        assertEquals("Wrong sum returned. It should be 0.0",
                0.0, DataUtilities.calculateColumnTotal(nullValues, 0), 0.0000001d);
    }

    @Test
    public void testCalculateColumnTotal_PositiveColumn() {
        assertEquals("Wrong sum returned. It should be 5.0",
                5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
    }

    @Test
    public void testCalculateColumnTotal_NegativeColumn() {
        DefaultKeyedValues2D negativeValues = new DefaultKeyedValues2D();
        negativeValues.addValue(-1, 0, 0);
        negativeValues.addValue(-4, 1, 0);
        assertEquals("Wrong sum returned. It should be -5.0",
                -5.0, DataUtilities.calculateColumnTotal(negativeValues, 0), 0.0000001d);
    }

    @Test
    public void testCalculateColumnTotal_MixedColumn() {
        DefaultKeyedValues2D mixedValues = new DefaultKeyedValues2D();
        mixedValues.addValue(1, 0, 0);
        mixedValues.addValue(-4, 1, 0);
        assertEquals("Wrong sum returned. It should be -3.0",
                -3.0, DataUtilities.calculateColumnTotal(mixedValues, 0), 0.0000001d);
    }

    // Test cases for calculateRowTotal()
    @Test
    public void testCalculateRowTotal_NullInput() {
        try {
            DataUtilities.calculateRowTotal(null, 0);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // expected result
        }
    }

    @Test
    public void testCalculateRowTotal_OutOfBounds() {
        DefaultKeyedValues2D data = createSampleData();
        int rowIndexOutOfBounds = -1;

        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            DataUtilities.calculateRowTotal(data, rowIndexOutOfBounds);
        });

        String expectedMessage = "Row index out of bounds: " + rowIndexOutOfBounds;
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testCalculateRowTotal_NullRow() {
        DefaultKeyedValues2D nullValues = new DefaultKeyedValues2D();
        nullValues.addValue(null, 0, 0);
        nullValues.addValue(null, 0, 1);
        assertEquals("Wrong sum returned. It should be 0.0",
                0.0, DataUtilities.calculateRowTotal(nullValues, 0), 0.0000001d);
    }

    @Test
    public void testCalculateRowTotal_PositiveRow() {
        assertEquals("Wrong sum returned. It should be 1.0",
                1.0, DataUtilities.calculateRowTotal(values2D, 0), 0.0000001d);
    }

    @Test
    public void testCalculateRowTotal_NegativeRow() {
        DefaultKeyedValues2D negativeValues = new DefaultKeyedValues2D();
        negativeValues.addValue(-1, 0, 0);
        negativeValues.addValue(-4, 0, 1);
        assertEquals("Wrong sum returned. It should be -5.0",
                -5.0, DataUtilities.calculateRowTotal(negativeValues, 0), 0.0000001d);
    }

    @Test
    public void testCalculateRowTotal_MixedRow() {
        DefaultKeyedValues2D mixedValues = new DefaultKeyedValues2D();
        mixedValues.addValue(1, 0, 0);
        mixedValues.addValue(-4, 0, 1);
        assertEquals("Wrong sum returned. It should be -3.0",
                -3.0, DataUtilities.calculateRowTotal(mixedValues, 0), 0.0000001d);
    }

    // Test cases for createNumberArray()
    @Test
    public void testCreateNumberArray_NullInput() {
        try {
            DataUtilities.createNumberArray(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // expected result
        }
    }

    @Test
    public void testCreateNumberArray_EmptyInput() {
        Number[] result = DataUtilities.createNumberArray(new double[0]);
        assertNotNull("Result should not be null", result);
        assertEquals("Wrong length of result array", 0, result.length);
    }

    @Test
    public void testCreateNumberArray_PositiveInput() {
        double[] testData = {1.0, 2.0};
        Number[] result = DataUtilities.createNumberArray(testData);
        assertNotNull("Result should not be null", result);
        assertEquals("Wrong length of result array", testData.length, result.length);
        for (int i = 0; i < testData.length; i++) {
            assertEquals("Wrong value in result array", testData[i], result[i].doubleValue(), 0.0000001d);
        }
    }

    @Test
    public void testCreateNumberArray_NegativeInput() {
        double[] testData = {-1.0, -2.0};
        Number[] result = DataUtilities.createNumberArray(testData);
        assertNotNull("Result should not be null", result);
        assertEquals("Wrong length of result array", testData.length, result.length);
        for (int i = 0; i < testData.length; i++) {
            assertEquals("Wrong value in result array", testData[i], result[i].doubleValue(), 0.0000001d);
        }
    }

    @Test
    public void testCreateNumberArray_MixedInput() {
        double[]
                testData = {1.0, -2.0};
        Number[] result = DataUtilities.createNumberArray(testData);
        assertNotNull("Result should not be null", result);
        assertEquals("Wrong length of result array", testData.length, result.length);
        for (int i = 0; i < testData.length; i++) {
            assertEquals("Wrong value in result array", testData[i], result[i].doubleValue(), 0.0000001d);
        }
    }

}