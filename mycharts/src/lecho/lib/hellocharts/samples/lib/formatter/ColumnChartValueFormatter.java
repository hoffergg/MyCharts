package lecho.lib.hellocharts.samples.lib.formatter;

import lecho.lib.hellocharts.samples.lib.model.SubcolumnValue;

public interface ColumnChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SubcolumnValue value);

}
