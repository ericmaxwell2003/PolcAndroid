package software.credible.ads.polc.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import software.credible.ads.polc.model.Grid;
import software.credible.ads.polc.model.Row;
import software.credible.ads.polc.model.Solution;
import software.credible.ads.polc.solver.PolcSolver;
import software.credible.ads.polc.solver.SimplePolcSolver;
import software.credible.ui.R;

public class MainActivity extends AppCompatActivity {

    private PolcSolver solver;
    private int [] gridRowIds = {R.id.gridRow1, R.id.gridRow2, R.id.gridRow3, R.id.gridRow4, R.id.gridRow5 };

    private TextView solutionValid;
    private TextView solutionCost;
    private TextView solutionPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        solver = new SimplePolcSolver();

        solutionValid = (TextView) findViewById(R.id.solutionValid);
        solutionCost = (TextView) findViewById(R.id.solutionCost);
        solutionPath = (TextView) findViewById(R.id.solutionPath);
    }

    public void calculate(View view) {
        Grid grid = captureGridFromUi();
        if(grid != null && grid.isValid()) {
            Solution solution = solver.findPathOfLowestCost(grid);
            updateUiWithSolution(solution);
        } else {
            toast("Invalid Grid");
        }
    }

    public void clearGrid(View view) {
        solutionValid.setText("");
        solutionCost.setText(String.valueOf(0));
        solutionPath.setText("[]");
        for(int gridRowId : gridRowIds) {
            ((EditText)findViewById(gridRowId)).setText("");
        }
    }

    private void updateUiWithSolution(Solution solution) {
        solutionValid.setText(String.valueOf(solution.isValid()));
        solutionCost.setText(String.valueOf(solution.calculateTotalCost()));
        solutionPath.setText(Arrays.toString(solution.getPath().toArray()));
    }

    private Grid captureGridFromUi() {
        List<Row> rowsInGrid = new ArrayList<>();
        for(int gridRowId : gridRowIds) {
            Row rowEntry = getRow(gridRowId);
            if(rowEntry != null) {
                rowsInGrid.add(rowEntry);
            } else {
                break;
            }
        }
        return (rowsInGrid.isEmpty()) ? null : new Grid(rowsInGrid.toArray(new Row[1]));
    }

    private Row getRow(int resId) {
        int[] rowData = null;
        EditText editText = (EditText) findViewById(resId);
        if(editText != null && !TextUtils.isEmpty(editText.getText())) {
            String rowDataStr = editText.getText().toString();
            String [] rowDataArr = rowDataStr.split(",");
            rowData = new int[rowDataArr.length];
            for(int i = 0; i < rowDataArr.length; i++) {
                try {
                    rowData[i] = Integer.parseInt(rowDataArr[i].trim());
                } catch (NumberFormatException e) {
                    toast("Bad Grid Data Found");
                }
            }
        }
        return (rowData == null) ? null : new Row(rowData);
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
