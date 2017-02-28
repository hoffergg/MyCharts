package lecho.lib.hellocharts.samples.lib.formatter;


import lecho.lib.hellocharts.samples.lib.model.PointValue;

public interface LineChartValueFormatter {

    public int formatChartValue(char[] formattedValue, PointValue value);
}
