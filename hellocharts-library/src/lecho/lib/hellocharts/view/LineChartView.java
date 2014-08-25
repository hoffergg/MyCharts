package lecho.lib.hellocharts.view;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.BuildConfig;
import lecho.lib.hellocharts.LineChartDataProvider;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.ChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SelectedValue;
import lecho.lib.hellocharts.renderer.LineChartRenderer;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;

public class LineChartView extends AbstractChartView implements LineChartDataProvider {
	private static final String TAG = "LineChartView";
	protected LineChartData data;
	protected LineChartOnValueTouchListener onValueTouchListener = new DummyOnValueTouchListener();

	public LineChartView(Context context) {
		this(context, null, 0);
	}

	public LineChartView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LineChartView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		chartRenderer = new LineChartRenderer(context, this, this);
		setLineChartData(generateDummyData());
	}

	@Override
	public void setLineChartData(LineChartData data) {
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "Setting data for LineChartView");
		}

		if (null == data) {
			this.data = generateDummyData();
		} else {
			this.data = data;
		}
		axesRenderer.initAxesMeasurements();
		chartRenderer.initMaxViewport();
		chartRenderer.initCurrentViewport();
		chartRenderer.initDataMeasuremetns();

		ViewCompat.postInvalidateOnAnimation(LineChartView.this);
	}

	@Override
	public LineChartData getLineChartData() {
		return data;
	}

	@Override
	public ChartData getChartData() {
		return data;
	}

	@Override
	public void callChartTouchListener(SelectedValue selectedValue) {
		PointValue point = data.getLines().get(selectedValue.firstIndex).getValues().get(selectedValue.secondIndex);
		onValueTouchListener.onValueTouched(selectedValue.firstIndex, selectedValue.secondIndex, point);
	}

	public LineChartOnValueTouchListener getOnValueTouchListener() {
		return onValueTouchListener;
	}

	public void setOnValueTouchListener(LineChartOnValueTouchListener touchListener) {
		if (null == touchListener) {
			this.onValueTouchListener = new DummyOnValueTouchListener();
		} else {
			this.onValueTouchListener = touchListener;
		}
	}

	@Override
	public void animationDataUpdate(float scale) {
		for (Line line : data.getLines()) {
			for (PointValue point : line.getValues()) {
				point.update(scale);
			}
		}
		chartRenderer.initMaxViewport();
		chartRenderer.initCurrentViewport();
		ViewCompat.postInvalidateOnAnimation(this);
	}

	@Override
	public void animationDataFinished(boolean isFinishedSuccess) {
		for (Line line : data.getLines()) {
			for (PointValue point : line.getValues()) {
				point.finish(isFinishedSuccess);
			}
		}
		chartRenderer.initMaxViewport();
		chartRenderer.initCurrentViewport();
		ViewCompat.postInvalidateOnAnimation(this);
	}

	protected LineChartData generateDummyData() {
		final int numValues = 4;
		LineChartData data = new LineChartData();
		List<PointValue> values = new ArrayList<PointValue>(numValues);
		values.add(new PointValue(1, 8));
		values.add(new PointValue(2, 6));
		values.add(new PointValue(3, 10));
		values.add(new PointValue(4, 4));
		Line line = new Line(values);
		List<Line> lines = new ArrayList<Line>(1);
		lines.add(line);
		data.setLines(lines);
		data.setAxisX(null);
		data.setAxisY(null);
		return data;
	}

	public interface LineChartOnValueTouchListener {
		public void onValueTouched(int selectedLine, int selectedValue, PointValue value);
	}

	private static class DummyOnValueTouchListener implements LineChartOnValueTouchListener {

		@Override
		public void onValueTouched(int selectedLine, int selectedValue, PointValue value) {
			// do nothing
		}
	}
}
