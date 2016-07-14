package software.credible.ads.polc.solver;

import software.credible.ads.polc.model.Grid;
import software.credible.ads.polc.model.Solution;

import java.util.TreeSet;

public class SimplePolcSolver implements PolcSolver {

    @Override
    public Solution findPathOfLowestCost(Grid grid) {

        if(!grid.isValid()) {
            throw new IllegalArgumentException("This Grid is not Valid!:" + grid);
        }

        TreeSet<Solution> solutions = new TreeSet<>();
        for(int i = 0; i < grid.getRows().length; i++) {
            crawlGrid(i, 0, grid, new Solution(), solutions);
        }
        return solutions.size() > 0 ? solutions.first() : null;

    }

    private void crawlGrid(int currRow, int currCol, Grid grid, Solution possibleSolution, TreeSet<Solution> solutions) {

        if(possibleSolution.isValid()) {

            if (weHaveMadeItThrough(currCol, grid)) {
                solutions.add(possibleSolution);

            } else {
                possibleSolution.addPathElement(grid.valueAt(currRow, currCol));

                crawlGrid(currRow + 1, currCol + 1, grid, possibleSolution.copy(), solutions);
                crawlGrid(currRow    , currCol + 1, grid, possibleSolution.copy(), solutions);
                crawlGrid(currRow - 1, currCol + 1, grid, possibleSolution.copy(), solutions);
            }

        }
    }

    private boolean weHaveMadeItThrough(int col, Grid grid) {
        return col == grid.getRows()[0].size();
    }

}
