package software.credible.ads.polc.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution implements Comparable<Solution> {

    public static final int MAX_ALLOWED_COST = 50;

    private int totalCost;
    private boolean isValid = true;
    private List<Integer> path = new ArrayList<>();

    public Solution() {
    }

    public Solution(List<Integer> path, int totalCost, boolean isValid) {
        this.path = path;
        this.totalCost = totalCost;
        this.isValid = isValid;
    }

    public List<Integer> getPath() {
        return new ArrayList<>(path);
    }

    public Solution copy() {
        return new Solution(getPath(), totalCost, isValid);
    }

    public Solution addPathElement(int pathElement, int additionalCost) {
        if((totalCost + additionalCost) <= MAX_ALLOWED_COST) {
            path.add(pathElement);
            totalCost += additionalCost;
        } else {
            isValid = false;
        }
        return this;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public boolean isValid() {
        return isValid;
    }

    public String didMakeItThroughGrid() {
        return isValid ? "Yes" : "No";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solution solution = (Solution) o;

        return path != null ? path.equals(solution.path) : solution.path == null;

    }

    @Override
    public int hashCode() {
        return path != null ? path.hashCode() : 0;
    }

    @Override
    public int compareTo(Solution o) {

        if(o == null) {
            return -1;
        }

        int isValid = Boolean.compare(isValid(), o.isValid());
        if(isValid != 0) {
            return isValid;
        }

        int pathLengthComparison = Integer.compare(o.getPath().size(), getPath().size());
        if(pathLengthComparison != 0) {
            return pathLengthComparison;
        }

        return Integer.compare(getTotalCost(), o.getTotalCost());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(didMakeItThroughGrid());
        sb.append("\n");
        sb.append(getTotalCost());
        sb.append("\n");
        sb.append(Arrays.toString(path.toArray()));
        return sb.toString();
    }
}
