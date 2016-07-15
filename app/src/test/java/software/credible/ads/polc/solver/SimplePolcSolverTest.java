package software.credible.ads.polc.solver;

import org.junit.Before;
import org.junit.Test;
import software.credible.ads.polc.model.Grid;
import software.credible.ads.polc.model.Row;
import software.credible.ads.polc.model.Solution;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SimplePolcSolverTest {

    private SimplePolcSolver simplePolcSolver;

    @Before
    public void setup() {
        simplePolcSolver = new SimplePolcSolver();
    }

    @Test
    public void whenFindPathOfLowestCostOn5x6GridThenChooseCorrectly() throws Exception {

        Grid grid = givenGridWithRows( new Row(3, 4, 1, 2, 8, 6),
                                       new Row(6, 1, 8, 2, 7, 4),
                                       new Row(5, 9, 3, 9, 9, 5),
                                       new Row(8, 4, 1, 3, 2, 6),
                                       new Row(3, 7, 2, 8, 6, 4) );

        Solution solution = simplePolcSolver.findPathOfLowestCost(grid);

        assertEquals("Yes", solution.didMakeItThroughGrid());
        assertEquals(16, solution.getTotalCost());
        assertEquals(Arrays.asList(1,2,3,4,4,5), solution.getPath());
    }

    @Test
    public void whenFindPathOfLowestCostOn5x6GridThatRequiresRowWrappingToBeAccurateThenChooseCorrectly() throws Exception {

        Grid grid = givenGridWithRows(  new Row(3, 4, 1, 2, 8, 6),
                                        new Row(6, 1, 8, 2, 7, 4),
                                        new Row(5, 9, 3, 9, 9, 5),
                                        new Row(8, 4, 1, 3, 2, 6),
                                        new Row(3, 7, 2, 1, 2, 3) );

        Solution solution = simplePolcSolver.findPathOfLowestCost(grid);

        assertEquals("Yes", solution.didMakeItThroughGrid());
        assertEquals(11, solution.getTotalCost());
        assertEquals(Arrays.asList(1,2,1,5,5,5), solution.getPath()); // 121545 is also a valid combination.
    }

    @Test
    public void whenFindPathOfLowestCostOn3x5GridThatHasNoPosibleSolutionsThenItReturnsNull() throws Exception {

        Grid grid = givenGridWithRows(  new Row(19, 10, 19, 10, 19),
                                        new Row(21, 23, 20, 19, 12),
                                        new Row(20, 12, 20, 11, 10) );

        Solution solution = simplePolcSolver.findPathOfLowestCost(grid);

        assertEquals("No", solution.didMakeItThroughGrid());
        assertEquals(48, solution.getTotalCost());
        assertEquals(Arrays.asList(1,1,1), solution.getPath());
    }

    @Test
    public void whenFindPathOfLowestCostOn3x6GridThatRequiresRowWrappingToBeAccurateThenChooseCorrectly() throws Exception {

        Grid grid = givenGridWithRows(
                new Row(5, 9, 3, 9, 9, 5),
                new Row(8, 4, 1, 3, 2, 6),
                new Row(3, 7, 2, 1, 2, 3) );

        Solution solution = simplePolcSolver.findPathOfLowestCost(grid);

        assertEquals("Yes", solution.didMakeItThroughGrid());
        assertEquals(14, solution.getTotalCost());
        assertEquals(Arrays.asList(3, 2, 2, 3, 3, 3), solution.getPath());
    }

    @Test
    public void whenFindPathOfLowestCostOn2x6GridThatRequiresRowWrappingToBeAccurateThenChooseCorrectly() throws Exception {

        Grid grid = givenGridWithRows(
                new Row(5, 9, 3, 9, 9, 5),
                new Row(3, 7, 2, 1, 2, 3) );

        Solution solution = simplePolcSolver.findPathOfLowestCost(grid);

        assertEquals("Yes", solution.didMakeItThroughGrid());
        assertEquals(18, solution.getTotalCost());
        assertEquals(Arrays.asList(2,2,2,2,2,2), solution.getPath());
    }

    @Test
    public void whenFindPathOfLowestCostOn5x6GridThatHasNegativeNumbersThenChooseCorrectly() throws Exception {

        Grid grid = givenGridWithRows(
                new Row(3, 4, 1, 2, 8, 6),
                new Row(6, 1, 8, 2, 7, -100),
                new Row(5, 9, 3, 9, 9, 5),
                new Row(8, 4, 1, 3, 2, 6),
                new Row(3, 7, 2, 1, 2, 3) );

        Solution solution = simplePolcSolver.findPathOfLowestCost(grid);

        assertEquals("Yes", solution.didMakeItThroughGrid());
        assertEquals(-86, solution.getTotalCost());
        assertEquals(Arrays.asList(1, 2, 1, 2, 2, 2), solution.getPath());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWeGiveItAnInvalidGridToStartWithThenThrowsAnIllegalArgumentException() throws Exception {

        Grid grid = givenGridWithRows(  new Row(3, 4, 1, 6),
                new Row(6, 1, 8, 2, 7, 4),
                new Row(5, 9, 3, 9, 9, 5),
                new Row(8, 4, 1, 3, 2, 6),
                new Row(3, 7, 2, 1, 2, 3) );

        simplePolcSolver.findPathOfLowestCost(grid);
    }


    private Grid givenGridWithRows(Row... rows) {
        return new Grid(rows);
    }

}