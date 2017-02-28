package lecho.lib.hellocharts.samples.lib.listener;


import lecho.lib.hellocharts.samples.lib.model.PointValue;
import lecho.lib.hellocharts.samples.lib.model.SubcolumnValue;

public interface ComboLineColumnChartOnValueSelectListener extends OnValueDeselectListener {

    public void onColumnValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value);

    public void onPointValueSelected(int lineIndex, int pointIndex, PointValue value);

}
