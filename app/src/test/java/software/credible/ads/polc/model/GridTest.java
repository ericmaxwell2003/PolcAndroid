package software.credible.ads.polc.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {

    @Test
    public void testGridWithOneRowIsValid() {
        assertTrue(new Grid(makeValidRow()).isValid());
    }

    @Test
    public void testGridWithZeroRowsIsInvalid() {
        assertFalse(new Grid().isValid());
    }

    @Test
    public void testGridWithTwoRowIsValid() {
        assertTrue(new Grid(makeValidRow(), makeValidRow()).isValid());
    }

    @Test
    public void testGridTenRowsIsValid() {
        assertTrue(new Grid(arrayOfValidRowsWithSize(10)).isValid());
    }

    @Test
    public void testGridWith11RowsIsInvalid() {
        assertFalse(new Grid(arrayOfValidRowsWithSize(11)).isValid());
    }

    @Test
    public void testThatGridCannotBeValidIfAnyOfItsRowsAreInvalid() {
        assertFalse(new Grid(makeValidRow(), makeInvalidRow()).isValid());
    }

    @Test
    public void whenRowsVaryInLengthThenTheyAreInvalid() {
        assertFalse(new Grid(
                new Row(1, 2, 3, 4, 5),
                new Row(1, 2, 3, 4, 5, 6)
        ).isValid());
    }

    @Test
    public void whenValueAtIsCalledThenItReturnsTheValueAtTheSpecifiedCoordinates() {
        Grid testGrid = new Grid(
            new Row( 1,  2,  3,  4,  5),
            new Row( 6,  7,  8,  9, 10),
            new Row(11, 12, 13, 14, 15)
        );
        assertEquals(9, testGrid.valueAt(1, 3));
    }

    @Test
    public void whenValueAtIsCalledWithRowIndexOutOfBoundsThenItReturnsTheValueAtTheWrappedCoordinates() {
        Grid testGrid = new Grid(
                new Row( 1,  2,  3,  4,  5),
                new Row( 6,  7,  8,  9, 10),
                new Row(11, 12, 13, 14, 15)
        );
        assertEquals(9, testGrid.valueAt(4, 3));
        assertEquals(9, testGrid.valueAt(7, 3));
    }

    @Test
    public void whenValueAtIsCalledWithRowIndexNegativeThenItReturnsTheValueAtTheWrappedCoordinates() {
        Grid testGrid = new Grid(
                new Row( 1,  2,  3,  4,  5),
                new Row( 6,  7,  8,  9, 10),
                new Row(11, 12, 13, 14, 15)
        );
        assertEquals(9, testGrid.valueAt(-2, 3));
    }

    @Test
    public void whenValueAtIsCalledWithRowIndexMinusFourOn3x5GridThenItReturnsTheValueFromTheLastRow() {
        Grid testGrid = new Grid(
                new Row( 1,  2,  3,  4,  5),
                new Row( 6,  7,  8,  9, 10),
                new Row(11, 12, 13, 14, 15)
        );
        assertEquals(14, testGrid.valueAt(-4, 3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenValueAtIsCalledWithColumnIndexOutOfBoundsThenItThrowsAnArrayIndexOutOfBoundsException() {
        Grid testGrid = new Grid(
                new Row( 1,  2,  3,  4,  5),
                new Row( 6,  7,  8,  9, 10),
                new Row(11, 12, 13, 14, 15)
        );
        testGrid.valueAt(1, 5);
    }

    @Test
    public void whenCreateFromStringIsCalledThenTheAppropriateGridIsCreated() {

        Grid expectedGrid = new Grid(
                new Row(3, 4, 1, 2, 8, 6),
                new Row(6, 1, 8, 2, 7, 4),
                new Row(5, 9, 3, 9, 9, 5),
                new Row(8, 4, 1, 3, 2, 6),
                new Row(3, 7, 2, 1, 2, 3)
        );

        Grid actualGrid = new Grid("3,4,1,2,8,6;6,1,8,2,7,4;5,9,3,9,9,5;8,4,1,3,2,6;3,7,2,1,2,3");

        assertEquals(expectedGrid, actualGrid);

    }

    private Row[] arrayOfValidRowsWithSize(int size) {
        Row [] rows = new Row[size];
        for(int i=0; i < rows.length; i++) {
            rows[i] = makeValidRow();
        }
        return rows;
    }

    private Row makeInvalidRow() {
        return new Row(1, 2, 3, 5);
    }

    private Row makeValidRow() {
        return new Row(1, 2, 3, 4, 5);
    }

}
