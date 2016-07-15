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

    private static final String TAG = MainActivity.class.getName();

    private PolcSolver solver;

    private TextView grid;
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

        grid = (EditText) findViewById(R.id.grid);
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
        grid.setText("");
    }

    private void updateUiWithSolution(Solution solution) {
        solutionValid.setText(solution.didMakeItThroughGrid());
        solutionCost.setText(String.valueOf(solution.getTotalCost()));
        solutionPath.setText(Arrays.toString(solution.getPath().toArray()));
    }

    private Grid captureGridFromUi() {
        try {
            String gridData = grid.getText().toString().replaceAll("\\n",";").replaceAll("\\s","");
            return (gridData.isEmpty()) ? null : new Grid(gridData);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
