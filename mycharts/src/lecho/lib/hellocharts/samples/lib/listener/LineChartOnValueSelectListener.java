package lecho.lib.hellocharts.samples.lib.listener;


import lecho.lib.hellocharts.samples.lib.model.PointValue;

public interface LineChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int lineIndex, int pointIndex, PointValue value);

}
