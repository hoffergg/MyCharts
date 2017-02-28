package lecho.lib.hellocharts.samples.lib.formatter;

import lecho.lib.hellocharts.samples.lib.model.BubbleValue;

public interface BubbleChartValueFormatter {

    public int formatChartValue(char[] formattedValue, BubbleValue value);
}
