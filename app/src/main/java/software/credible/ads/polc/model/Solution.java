package software.credible.ads.polc.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution implements Comparable<Solution> {

    private boolean isDirty = true;
    private int lastCostCalculation;

    private List<Integer> path = new ArrayList<>();

    public Solution() {
    }

    public Solution(List<Integer> path) {
        this.path = path;
    }

    public List<Integer> getPath() {
        return new ArrayList<>(path);
    }

    public Solution copy() {
        return new Solution(getPath());
    }

    public Solution addPathElement(int pathElement) {
        path.add(pathElement);
        isDirty = true;
        return this;
    }

    public int calculateTotalCost() {
        if(isDirty) {
            int total = 0;
            for(Integer i : path) {
                total += i;
            }
            lastCostCalculation = total;
            isDirty = false;
        }
        return lastCostCalculation;
    }

    public boolean isValid() {
        return calculateTotalCost() <= 50;
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
        return Integer.compare(calculateTotalCost(), o.calculateTotalCost());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isValid() ? "Yes" : "No");
        sb.append("\n");
        sb.append(calculateTotalCost());
        sb.append("\n");
        sb.append(Arrays.toString(path.toArray()));
        return sb.toString();
    }
}
