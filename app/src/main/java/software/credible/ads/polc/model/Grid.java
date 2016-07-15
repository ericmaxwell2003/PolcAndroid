package software.credible.ads.polc.model;

import java.util.Arrays;

public class Grid {

    private Row [] rows;

    /**
     * Create a grid from the given input stream in the format of row1col1,row1col2,row1col3;row2col1,row2col2,row2col3,
     * where this would create 2 rows with 3 columns each.
     * This implementation is not as robust as I would make it in a
     * production setting.  For example, spaces and disallowed characters will cause errors.
     * @param gridString String to parse into a Grid.
     */
    public Grid(String gridString) {

        String [] rowStrings = gridString.split(";");
        rows = new Row[rowStrings.length];

        for(int i = 0; i < rowStrings.length; i++) {

            String [] colStrings = rowStrings[i].split(",");
            int [] rowValues = new int[colStrings.length];
            for(int j = 0; j < colStrings.length; j++) {
                rowValues[j] = Integer.parseInt(colStrings[j]);
            }
            rows[i] = new Row(rowValues);
        }
    }

    public Grid(Row... rows) {
        this.rows = rows;
    }

    public Row[] getRows() {
        if(rows == null) {
            rows = new Row[0];
        }
        return rows;
    }

    public boolean isValid() {
        return isValidLength() && isContentValid();
    }

    public int size() {
        return getRows().length;
    }

    public int reducedRowIdxFor(int row) {
        row = row % size();
        if(row < 0) {
            row = size() + row;
        }
        return row % size();
    }

    public int valueAt(int row, int col) {
        return getRows()[reducedRowIdxFor(row)].getCols()[col];
    }

    private boolean isContentValid() {
        int lastRowSize = this.size() > 0 ? getRows()[0].size() : 0;
        for(Row thisRow : getRows()) {
            if(!thisRow.isValid() || lastRowSize != thisRow.size()) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidLength() {
        return getRows().length >= 1 && getRows().length <= 10;
    }

    @Override
    public String toString() {
        return Arrays.toString(rows);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grid grid = (Grid) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(rows, grid.rows);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rows);
    }
}
