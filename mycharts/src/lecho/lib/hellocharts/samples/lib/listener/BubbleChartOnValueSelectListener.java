package lecho.lib.hellocharts.samples.lib.listener;


import lecho.lib.hellocharts.samples.lib.model.BubbleValue;

public interface BubbleChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int bubbleIndex, BubbleValue value);

}
