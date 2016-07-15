package software.credible.ads.polc.solver;

import software.credible.ads.polc.model.Grid;
import software.credible.ads.polc.model.Solution;

/**
 * The intention of this interface is to define the contract of a PolcSolver, with the idea that we may want additional
 * PolcSolvers in the future.
 */
public interface PolcSolver {

    Solution findPathOfLowestCost(Grid grid);

}
