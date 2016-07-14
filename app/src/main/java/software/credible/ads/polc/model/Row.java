package software.credible.ads.polc.model;

import java.util.Arrays;

public class Row {

    private int[] cols;

    public Row(int... cols) {
        this.cols = cols;
    }

    public int[] getCols() {
        if(cols == null) {
            cols = new int[0];
        }
        return cols;
    }

    public int size() {
        return getCols().length;
    }

    public boolean isValid() {
        return getCols().length >= 5 && getCols().length <= 100;
    }

    @Override
    public String toString() {
        return Arrays.toString(cols);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Row row = (Row) o;

        return Arrays.equals(cols, row.cols);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cols);
    }
}
