package software.credible.ads.polc.solver;

import software.credible.ads.polc.model.Grid;
import software.credible.ads.polc.model.Solution;

public interface PolcSolver {

    Solution findPathOfLowestCost(Grid grid);

}
