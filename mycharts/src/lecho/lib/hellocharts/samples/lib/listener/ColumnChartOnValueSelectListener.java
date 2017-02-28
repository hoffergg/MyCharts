package lecho.lib.hellocharts.samples.lib.listener;


import lecho.lib.hellocharts.samples.lib.model.SubcolumnValue;

public interface ColumnChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value);

}
