package lecho.lib.hellocharts.samples.lib.listener;


import lecho.lib.hellocharts.samples.lib.model.SliceValue;

public interface PieChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int arcIndex, SliceValue value);

}
