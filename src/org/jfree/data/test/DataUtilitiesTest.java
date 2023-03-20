package org.jfree.data.test;

import org.jfree.data.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

    // Test cases for createNumberArray()
    @Test
    public void testCreateNumberArray_MaxDoubleInput() {
        double[] testData = {Double.MAX_VALUE, Double.MAX_VALUE};
        Number[] result = DataUtilities.createNumberArray(testData);
        assertNotNull("Result should not be null", result);
        assertEquals("Wrong length of result array", testData.length, result.length);
        for (int i = 0; i < testData.length; i++) {
            assertEquals("Wrong value in result array", testData[i], result[i].doubleValue(), 0.0000001d);
        }
    }

    @Test
    public void testCreateNumberArray_MinDoubleInput() {
        double[] testData = {Double.MIN_VALUE, Double.MIN_VALUE};
        Number[] result = DataUtilities.createNumberArray(testData);
        assertNotNull("Result should not be null", result);
        assertEquals("Wrong length of result array", testData.length, result.length);
        for (int i = 0; i < testData.length; i++) {
            assertEquals("Wrong value in result array", testData[i], result[i].doubleValue(), 0.0000001d);
        }
    }

    @Test
    public void testCreateNumberArray_InfinityInput() {
        double[] testData = {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
        Number[] result = DataUtilities.createNumberArray(testData);
        assertNotNull("Result should not be null", result);
        assertEquals("Wrong length of result array", testData.length, result.length);
        for (int i = 0; i < testData.length; i++) {
            assertEquals("Wrong value in result array", testData[i], result[i].doubleValue(), 0.0000001d);
        }
    }

    @Test
    public void testCreateNumberArray_NaNInput() {
        double[] testData = {Double.NaN, Double.NaN};
        Number[] result = DataUtilities.createNumberArray(testData);
        assertNotNull("Result should not be null", result);
        assertEquals("Wrong length of result array", testData.length, result.length);
        for (int i = 0; i < testData.length; i++) {
            assertTrue("Value in result array should be NaN", Double.isNaN(result[i].doubleValue()));
        }
    }

    // Test cases for calculateRowTotal()
    @Test
    public void testCalculateRowTotal_NoRows() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        assertEquals("Wrong sum returned. It should be 0.0",
                0.0, DataUtilities.calculateRowTotal(data, 0), 0.0000001d);
    }

    // Test cases for calculateColumnTotal()
    @Test
    public void testCalculateColumnTotal_NoColumns() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        if (data == null) {
            fail("Data is null");
        }
        assertEquals("Wrong sum returned. It should be 0.0",
                0.0, DataUtilities.calculateColumnTotal(data, 0), 0.0000001d);
    }

    @Test
    public void testCalculateColumnTotal_LastColumn() {
        DefaultKeyedValues2D data = createSampleData();
        int lastColumnIndex = data.getColumnCount() - 1;
        assertEquals("Wrong sum returned. It should be 270.0",
                270.0, DataUtilities.calculateColumnTotal(data, lastColumnIndex));
    }

    // Test cases for calculateColumnTotal()
    @Test
    public void testCalculateColumnTotal_EmptyData() {
        DefaultKeyedValues2D emptyData = new DefaultKeyedValues2D();
        assertEquals("Wrong sum returned. It should be 0.0",
                0.0, DataUtilities.calculateColumnTotal(emptyData, 0), 0.0000001d);
    }

    @Test
    public void testCalculateColumnTotal_LargeData() {
        DefaultKeyedValues2D largeData = new DefaultKeyedValues2D();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                largeData.addValue(1, i, j);
            }
        }
        assertEquals("Wrong sum returned. It should be 1000.0",
                1000.0, DataUtilities.calculateColumnTotal(largeData, 0));
    }

    // Test cases for calculateRowTotal()
    @Test
    public void testCalculateRowTotal_EmptyData() {
        DefaultKeyedValues2D emptyData = new DefaultKeyedValues2D();
        assertEquals("Wrong sum returned. It should be 0.0",
                0.0, DataUtilities.calculateRowTotal(emptyData, 0), 0.0000001d);
    }

    @Test
    public void testCalculateRowTotal_LargeData() {
        DefaultKeyedValues2D largeData = new DefaultKeyedValues2D();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                largeData.addValue(1, i, j);
            }
        }
        assertEquals("Wrong sum returned. It should be 1000.0",
                1000.0, DataUtilities.calculateRowTotal(largeData, 0), 0.0000001d);
    }

    // Test cases for createNumberArray()
    @Test
    public void testCreateNumberArray_LargeInput() {
        double[] largeData = new double[1000000];
        for (int i = 0; i < 1000000; i++) {
            largeData[i] = 1;
        }
        Number[] result = DataUtilities.createNumberArray(largeData);
        assertNotNull("Result should not be null", result);
        assertEquals("Wrong length of result array", largeData.length, result.length);
        for (int i = 0; i < largeData.length; i++) {
            assertEquals("Wrong value in result array", largeData[i], result[i].doubleValue(), 0.0000001d);
        }
    }

    @Test
    public void testCreateNumberArray_NullArray() {
        double[] nullData = null;
        try {
            DataUtilities.createNumberArray(nullData);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // expected result
        }
    }

    @Test
    public void testCreateNumberArray2D_NullData() {
        try {
            DataUtilities.createNumberArray2D(null);
            fail("Expected IllegalArgumentException not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Null 'data' argument.", e.getMessage());
        }
    }

    @Test
    public void testCreateNumberArray2D_ValidData() {
        double[][] input = {{1.0, 2.0, 3.0}, {4.0, 5.0}, {6.0, 7.0, 8.0, 9.0}};
        Number[][] expected = {{1.0, 2.0, 3.0}, {4.0, 5.0}, {6.0, 7.0, 8.0, 9.0}};
        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGetCumulativePercentages_NullData() {
        try {
            DataUtilities.getCumulativePercentages(null);
            fail("Expected IllegalArgumentException not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Null 'data' argument.", e.getMessage());
        }
    }

    @Test
    public void testGetCumulativePercentages() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 10.0);
        data.addValue("B", 20.0);
        data.addValue("C", 30.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.1, result.getValue("A"));
        assertEquals(0.3, result.getValue("B"));
        assertEquals(0.6, result.getValue("C"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentagesWithNullData() {
        DataUtilities.getCumulativePercentages(null);
    }

    @Test
    public void testCreateNumberArray() {
        double[] input = {1.0, 2.0, 3.0, 4.0};
        Number[] expectedOutput = {1.0, 2.0, 3.0, 4.0};
        Number[] output = DataUtilities.createNumberArray(input);
        assertArrayEquals("Unexpected output", expectedOutput, output);
    }
    @Test
    public void testCreateNumberArray1() {
        double[] input = {1.0, 2.0, 3.0, 4.0};
        Number[] expectedOutput = {1.0, 2.0, 3.0, 4.0};
        Number[] output = DataUtilities.createNumberArray(input);
        assertArrayEquals("Unexpected output", expectedOutput, output);
    }
    @Test
    public void testCreateNumberArray2D() {
        double[][] input = {{1.0, 2.0}, {3.0, 4.0}, {5.0, 6.0}};
        Number[][] expectedOutput = {{1.0, 2.0}, {3.0, 4.0}, {5.0, 6.0}};
        Number[][] output = DataUtilities.createNumberArray2D(input);
        assertArrayEquals("Unexpected output", expectedOutput, output);
    }


}