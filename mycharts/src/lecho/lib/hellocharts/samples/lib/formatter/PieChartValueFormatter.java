package lecho.lib.hellocharts.samples.lib.formatter;

import lecho.lib.hellocharts.samples.lib.model.SliceValue;

public interface PieChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SliceValue value);
}
