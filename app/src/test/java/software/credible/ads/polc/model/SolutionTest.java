package software.credible.ads.polc.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class SolutionTest {

    private Solution solution;

    @Before
    public void setup() {
        solution = new Solution();
    }

    @Test
    public void whenWeAddPathElementThenItAddsItToTheEndOfTheList() {
        assertEquals(0, solution.getPath().size());

        solution.addPathElement(10, 1);
        assertEquals(1, solution.getPath().size());
        assertEquals(10, solution.getPath().get(0).intValue());

        solution.addPathElement(15, 1);
        assertEquals(2, solution.getPath().size());
        assertEquals(15, solution.getPath().get(1).intValue());
    }

    @Test
    public void whenCalculateCostIsCalledThenItDynamicallyCalculatesItBasedOnTheSumOfThePathElements() {
        assertEquals(0, solution.getPath().size());
        assertEquals(0, solution.getTotalCost());

        solution.addPathElement(1, 10);
        assertEquals(10, solution.getTotalCost());

        solution.addPathElement(1, 15);
        assertEquals(25, solution.getTotalCost());
    }

    @Test
    public void whenNegativeNumberIsAddedToThePathThenTheCostIsReduced() {
        assertEquals(0, solution.getPath().size());
        solution.addPathElement(1, 10);
        solution.addPathElement(1, 15);
        assertEquals(25, solution.getTotalCost());

        solution.addPathElement(1, -20);
        assertEquals(5, solution.getTotalCost());
    }

    @Test
    public void whenZerorIsAddedToThePathThenTheCostIsUnchanged() {
        assertEquals(0, solution.getPath().size());
        solution.addPathElement(1, 10);
        solution.addPathElement(1, 15);
        assertEquals(25, solution.getTotalCost());

        solution.addPathElement(1, 0);
        assertEquals(25, solution.getTotalCost());
    }

    @Test
    public void whenPathSumLessThanOrEqualToFiftyThenSolutionIsValid() {
        assertEquals(0, solution.getPath().size());

        solution.addPathElement(1, 49);
        assertTrue(solution.isValid());

        solution.addPathElement(1, 1);
        assertTrue(solution.isValid());
    }

    @Test
    public void whenPathSumGreaterThanFiftyThenSolutionIsInvalid() {
        assertEquals(0, solution.getPath().size());

        solution.addPathElement(1, 49);
        assertTrue(solution.isValid());

        solution.addPathElement(1, 2);
        assertFalse(solution.isValid());
    }

    @Test
    public void whenPathsAreEqualThenSolutionsAreEqual() {
        Solution solutionLeft = new Solution();
        solutionLeft.addPathElement(1, 10);
        solutionLeft.addPathElement(1, 12);
        solutionLeft.addPathElement(1, 13);

        Solution solutionRight = new Solution();
        solutionRight.addPathElement(1, 10);
        solutionRight.addPathElement(1, 12);
        solutionRight.addPathElement(1, 13);

        assertEquals(solutionLeft, solutionRight);
    }

    @Test
    public void whenPathsHaveSameElementsButInDifferentOrderThenSolutionsAreNotEqual() {
        Solution solutionLeft = new Solution();
        solutionLeft.addPathElement(10, 1);
        solutionLeft.addPathElement(12, 1);
        solutionLeft.addPathElement(13, 1);

        Solution solutionRight = new Solution();
        solutionRight.addPathElement(12, 1);
        solutionRight.addPathElement(10, 1);
        solutionRight.addPathElement(13, 1);

        assertNotEquals(solutionLeft, solutionRight);
    }

    @Test
    public void whenPathsHaveSameCostButDifferentElementsThenSolutionsAreNotEqual() {
        Solution solutionLeft = new Solution();
        solutionLeft.addPathElement(1, 10);
        solutionLeft.addPathElement(1, 12);
        solutionLeft.addPathElement(1, 13);

        Solution solutionRight = new Solution();
        solutionRight.addPathElement(1, 10 + 12);
        solutionRight.addPathElement(1, 13);

        assertNotEquals(solutionLeft, solutionRight);
    }

    @Test
    public void whenSolutionsArePlacedInSortedSetThenTheyAreOrderedFromLeastToGreatestCost() {
        Solution s1 = new Solution();
        s1.addPathElement(1, 13);

        Solution s2 = new Solution();
        s2.addPathElement(1, 10 + 13);

        Solution s3 = new Solution();
        s3.addPathElement(1, 2);

        Solution s4 = new Solution();
        s4.addPathElement(1, -2);

        Set<Solution> orderedSet = new TreeSet<>(Arrays.asList(s1, s2, s3, s4));

        Iterator sortedSolutions = orderedSet.iterator();
        assertEquals(s4, sortedSolutions.next());
        assertEquals(s3, sortedSolutions.next());
        assertEquals(s1, sortedSolutions.next());
        assertEquals(s2, sortedSolutions.next());
    }

    @Test
    public void whenCopyIsCalledThenThePathArrayIsDuplicatedSoElementsDoNotReferenceEachOther() {

        solution.addPathElement(10, 1);
        solution.copy().addPathElement(15, 1);

        assertEquals(1, solution.getPath().size());
        assertEquals(10, solution.getPath().get(0).intValue());
    }
}
