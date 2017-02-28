package lecho.lib.hellocharts.samples.lib.provider;

import lecho.lib.hellocharts.samples.lib.model.LineChartData;

public interface LineChartDataProvider {

    public LineChartData getLineChartData();

    public void setLineChartData(LineChartData data);

}
