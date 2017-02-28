package lecho.lib.hellocharts.samples;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.samples.lib.gesture.ZoomType;
import lecho.lib.hellocharts.samples.lib.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.samples.lib.model.Axis;
import lecho.lib.hellocharts.samples.lib.model.AxisValue;
import lecho.lib.hellocharts.samples.lib.model.Column;
import lecho.lib.hellocharts.samples.lib.model.ColumnChartData;
import lecho.lib.hellocharts.samples.lib.model.SubcolumnValue;
import lecho.lib.hellocharts.samples.lib.util.ChartUtils;
import lecho.lib.hellocharts.samples.lib.view.Chart;
import lecho.lib.hellocharts.samples.lib.view.ColumnChartView;

public class ColumnChartActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_chart);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    /**
     * A fragment containing a column chart.
     */
    public static class PlaceholderFragment extends Fragment {

        private static final int DEFAULT_DATA = 0;
        private static final int SUBCOLUMNS_DATA = 1;
        private static final int STACKED_DATA = 2;
        private static final int NEGATIVE_SUBCOLUMNS_DATA = 3;
        private static final int NEGATIVE_STACKED_DATA = 4;

        private ColumnChartView chart;
        private ColumnChartData data;
        private boolean hasAxes = true;
        private boolean hasAxesNames = false;
        private boolean hasLabels = true;
        private boolean hasLabelForSelected = true;
        private int dataType = STACKED_DATA;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            View rootView = inflater.inflate(R.layout.fragment_column_chart, container, false);

            chart = (ColumnChartView) rootView.findViewById(R.id.chart);

            chart.setValueSelectionEnabled(hasLabelForSelected);
            generateData();

            return rootView;
        }

        private void generateDefaultData() {
            int numSubcolumns = 1;
            int numColumns = 8;
            // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
            List<Column> columns = new ArrayList<Column>();
            List<SubcolumnValue> values;
            for (int i = 0; i < numColumns; ++i) {

                values = new ArrayList<SubcolumnValue>();
                for (int j = 0; j < numSubcolumns; ++j) {
                    values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
                }

                Column column = new Column(values);
                column.setHasLabels(hasLabels);
                column.setHasLabelsOnlyForSelected(hasLabelForSelected);
                columns.add(column);
            }

            data = new ColumnChartData(columns);

            if (hasAxes) {
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true);
                if (hasAxesNames) {
                    axisX.setName("Axis X");
                    axisY.setName("Axis Y");
                }
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
            } else {
                data.setAxisXBottom(null);
                data.setAxisYLeft(null);
            }

            chart.setColumnChartData(data);

        }

        /**
         * Generates columns with subcolumns, columns have larger separation than subcolumns.
         */
        private void generateSubcolumnsData() {
            int numSubcolumns = 4;
            int numColumns = 4;
            // Column can have many subcolumns, here I use 4 subcolumn in each of 8 columns.
            List<Column> columns = new ArrayList<Column>();
            List<SubcolumnValue> values;
            for (int i = 0; i < numColumns; ++i) {

                values = new ArrayList<SubcolumnValue>();
                for (int j = 0; j < numSubcolumns; ++j) {
                    values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
                }

                Column column = new Column(values);
                column.setHasLabels(hasLabels);
                column.setHasLabelsOnlyForSelected(hasLabelForSelected);
                columns.add(column);
            }

            data = new ColumnChartData(columns);

            if (hasAxes) {
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true);
                if (hasAxesNames) {
                    axisX.setName("Axis X");
                    axisY.setName("Axis Y");
                }
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
            } else {
                data.setAxisXBottom(null);
                data.setAxisYLeft(null);
            }

            chart.setColumnChartData(data);

        }

        /**
         * Generates columns with stacked subcolumns.
         */
        private void generateStackedData() {
            int numSubcolumns = 6;
            // Column can have many stacked subcolumns, here I use 4 stacke subcolumn in each of 4 columns.
            List<Column> columns = new ArrayList<Column>();
            List<SubcolumnValue> values;
            List<AxisValue> xAxisValues = new ArrayList<>();
            List<AxisValue> yAxisValues = new ArrayList<>();

            int[] colors = {Color.parseColor("#ea6953")
                    ,Color.parseColor("#ffbd3e")
                    ,Color.parseColor("#ffffff")
                    ,Color.parseColor("#0c8ee1")
                    ,Color.parseColor("#96d7e3")
                    ,Color.parseColor("#7a60c5")};
            int[][] initValues = {{4,8,10,7,14,20}
                    ,{6,9,11,16,20,18}
                    ,{7,10,9,12,16,22}
                    ,{9,11,7,5,14,18}
                    ,{12,6,8,4,15,18}
                    ,{16,12,10,8,4,15}
                    ,{20,12,4,8,5,10}};
            String[] xLabels = {"11.1","11.2","11.3","11.4","11.5","11.6","11.7"};
            String[] yLabels = {"0","20","40","60","80","100"};

            for (int i = 0; i < initValues.length; ++i) {
                values = new ArrayList<SubcolumnValue>();
                for (int j = 0; j < numSubcolumns; ++j) {
                    values.add(new SubcolumnValue(initValues[i][j], colors[j]));
                }
                Column column = new Column(values);
                column.setHasLabels(hasLabels);
                column.setHasLabelsOnlyForSelected(hasLabelForSelected);
                columns.add(column);
                xAxisValues.add(new AxisValue(i,xLabels[i].toCharArray()));
            }

            for (int k=0;k<yLabels.length;k++){
                yAxisValues.add(new AxisValue(Float.valueOf(yLabels[k]),yLabels[k].toCharArray()));
            }

            data = new ColumnChartData(columns);

            // Set stacked flag.
            data.setStacked(true);

            if (hasAxes) {
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true).setHasSeparationLine(false);
                axisX.setValues(xAxisValues);
                axisY.setValues(yAxisValues);
                if (hasAxesNames) {
                    axisX.setName("Axis X");
                    axisY.setName("Axis Y");
                }
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
            } else {
                data.setAxisXBottom(null);
                data.setAxisYLeft(null);
            }

            chart.setColumnChartData(data);
        }

        private void generateData() {
            switch (dataType) {
                case DEFAULT_DATA:
                    generateDefaultData();
                    break;
                case SUBCOLUMNS_DATA:
                    generateSubcolumnsData();
                    break;
                case STACKED_DATA:
                    generateStackedData();
                    break;
                default:
                    generateDefaultData();
                    break;
            }
        }

    }
}
