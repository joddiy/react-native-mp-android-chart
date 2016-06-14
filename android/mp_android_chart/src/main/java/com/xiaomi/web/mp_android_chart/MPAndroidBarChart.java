package com.xiaomi.web.mp_android_chart;

/**
 * author:       joddiy <joddiy@qq.com>
 * time:         2016/6/6 18:10
 */

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MPAndroidBarChart extends SimpleViewManager<BarChart> {

    /**
     * @return the name of this view manager. This will be the name used to reference this view
     * manager from JavaScript in createReactNativeComponentClass.
     */
    @Override
    public String getName() {
        return "MPAndroidBarChart";
    }


    /**
     * If set to true, a grey area is drawn behind each bar that indicates the maximum value. Enabling his will reduce
     * performance by about 50%.
     *
     * @param mChart
     * @param enabled
     */
    @ReactProp(name = "draw_bar_shadow")
    public void setDrawBarShadow(BarChart mChart, Boolean enabled) {
        mChart.setDrawBarShadow(enabled);
    }

    /**
     * If set to true, all values are drawn above their bars, instead of below their top.
     *
     * @param mChart
     * @param enabled
     */
    @ReactProp(name = "draw_value_above_bar")
    public void setDrawValueAboveBar(BarChart mChart, Boolean enabled) {
        mChart.setDrawValueAboveBar(enabled);
    }

    /**
     * set a description text that appears in the bottom right corner of the
     * chart, size = Y-legend text size
     *
     * @param mChart
     * @param desc
     */
    @ReactProp(name = "description")
    public void setDescription(BarChart mChart, @Nullable String desc) {
        mChart.setDescription(desc);
    }

    /**
     * sets the number of maximum visible drawn values on the chart only active
     * when setDrawValues() is enabled
     *
     * @param mChart
     * @param count
     */
    @ReactProp(name = "max_visible_value_count")
    public void setMaxVisibleValueCount(BarChart mChart, int count) {
        mChart.setMaxVisibleValueCount(count);
    }

    /**
     * If set to true, both x and y axis can be scaled simultaneously with 2 fingers, if false,
     * x and y axis can be scaled separately. default: false
     *
     * @param mChart
     * @param enabled
     */
    @ReactProp(name = "pinch_zoom")
    public void setPinchZoom(BarChart mChart, Boolean enabled) {
        mChart.setPinchZoom(enabled);
    }

    /**
     * set this to true to draw the grid background, false if not
     *
     * @param mChart
     * @param enabled
     */
    @ReactProp(name = "draw_grid_background")
    public void setDrawGridBackground(BarChart mChart, Boolean enabled) {
        mChart.setDrawGridBackground(enabled);
    }

    /**
     * sets the position of the x-labels
     *
     * @param mChart
     * @param pos
     */
    @ReactProp(name = "x_axis_position")
    public void setXAxisPosition(BarChart mChart, String pos) {
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.valueOf(pos));
    }

    /**
     * Set this to true to enable drawing the grid lines for this axis.
     *
     * @param mChart
     * @param enabled
     */
    @ReactProp(name = "x_axis_draw_grid_lines")
    public void setXAxisDrawGridLines(BarChart mChart, Boolean enabled) {
        mChart.getXAxis().setDrawGridLines(enabled);
    }

    /**
     * Sets the space (in characters) that should be left out between the x-axis
     * labels, default 4. This only applies if the number of labels that will be
     * skipped in between drawn axis labels is not custom set.
     *
     * @param mChart
     * @param spaceCharacters
     */
    @ReactProp(name = "x_axis_space_between_labels")
    public void setXAxisSpaceBetweenLabels(BarChart mChart, int spaceCharacters) {
        mChart.getXAxis().setSpaceBetweenLabels(spaceCharacters);
    }

    /**
     * sets the position of the y-labels
     *
     * @param mChart
     * @param pos
     */
    @ReactProp(name = "y_axis_left_position")
    public void setYAxisLeftPosition(BarChart mChart, String pos) {
        mChart.getAxisLeft().setPosition(YAxis.YAxisLabelPosition.valueOf(pos));
    }

    /**
     * Sets the top axis space in percent of the full range. Default 10f
     *
     * @param mChart
     * @param count
     */
    @ReactProp(name = "y_axis_left_space_top")
    public void setYAxisLeftSpaceTop(BarChart mChart, float count) {
        mChart.getAxisLeft().setSpaceTop(count);
    }

    /**
     * Set a custom minimum value for this axis. If set, this value will not be calculated
     * automatically depending on
     * the provided data. Use resetAxisMinValue() to undo this. Do not forget to call
     * setStartAtZero(false) if you use
     * this method. Otherwise, the axis-minimum value will still be forced to 0.
     *
     * @param mChart
     * @param count
     */
    @ReactProp(name = "y_axis_left_min_value")
    public void setYAxisLeftMinValue(BarChart mChart, float count) {
        mChart.getAxisLeft().setAxisMinValue(count);
    }


    /**
     * sets the position of the y-labels
     *
     * @param mChart
     * @param pos
     */
    @ReactProp(name = "y_axis_right_position")
    public void setYAxisRightPosition(BarChart mChart, String pos) {
        mChart.getAxisRight().setPosition(YAxis.YAxisLabelPosition.valueOf(pos));
    }

    /**
     * Sets the top axis space in percent of the full range. Default 10f
     *
     * @param mChart
     * @param count
     */
    @ReactProp(name = "y_axis_right_space_top")
    public void setYAxisRightSpaceTop(BarChart mChart, float count) {
        mChart.getAxisRight().setSpaceTop(count);
    }

    /**
     * Set a custom minimum value for this axis. If set, this value will not be calculated
     * automatically depending on
     * the provided data. Use resetAxisMinValue() to undo this. Do not forget to call
     * setStartAtZero(false) if you use
     * this method. Otherwise, the axis-minimum value will still be forced to 0.
     *
     * @param mChart
     * @param count
     */
    @ReactProp(name = "y_axis_right_min_value")
    public void setYAxisRightMinValue(BarChart mChart, float count) {
        mChart.getAxisRight().setAxisMinValue(count);
    }

    /**
     * sets the position of the legend relative to the whole chart
     *
     * @param mChart
     * @param pos
     */
    @ReactProp(name = "legend_position")
    public void setLegendPosition(BarChart mChart, String pos) {
        mChart.getLegend().setPosition(Legend.LegendPosition.valueOf(pos));
    }

    /**
     * sets the form/shape of the legend forms
     *
     * @param mChart
     * @param shape
     */
    @ReactProp(name = "legend_form")
    public void setLegendForm(BarChart mChart, String shape) {
        mChart.getLegend().setForm(Legend.LegendForm.valueOf(shape));
    }

    /**
     * sets the size of the label text in pixels min = 6f, max = 24f, default
     * 10f
     *
     * @param mChart
     * @param size
     */
    @ReactProp(name = "legend_text_size")
    public void setLegendTextSize(BarChart mChart, float size) {
        mChart.getLegend().setTextSize(size);
    }

    /**
     * sets the space between the legend entries on a horizontal axis in pixels,
     * converts to dp internally
     *
     * @param mChart
     * @param space
     */
    @ReactProp(name = "legend_x_entry_sapce")
    public void setLegendXEntrySpace(BarChart mChart, float space) {
        mChart.getLegend().setXEntrySpace(space);
    }


    private enum barTypes {
        COMMON, STACKED
    }
    /**
     * set datasource of chart
     *
     * @param mChart
     * @param data_source
     */
    @ReactProp(name = "data_source")
    public void setDataSource(BarChart mChart, String data_source) {
        try {
            JSONObject JSONObj = new JSONObject(data_source);

            int mEntryCount = JSONObj.getInt("entryCount");

            int mSetCount = JSONObj.getInt("setCount");

            JSONArray xValsArray = JSONObj.getJSONArray("xVals");

            ArrayList<String> xVals = new ArrayList<>();

            for (int i = 0; i < mEntryCount; i++) {
                xVals.add(xValsArray.getString(i));
            }

            JSONArray mDataSets = JSONObj.getJSONArray("dataSets");

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();

//            ArrayList<BarDataSet> dataSets = new ArrayList<>();


            String barType = JSONObj.optString("barType", "COMMON");

            switch (barTypes.valueOf(barType)) {
                case COMMON:
                    for (int i = 0; i < mSetCount; i++) {
                        JSONObject mDataSet = mDataSets.getJSONObject(i);
                        JSONArray mYVals = mDataSet.getJSONArray("yVals");
                        ArrayList<BarEntry> yValsTemp = new ArrayList<>();
                        for (int j = 0; j < mEntryCount; j++) {
                            yValsTemp.add(new BarEntry((float) mYVals.getDouble(j), j));
                        }
                        BarDataSet setTemp = new BarDataSet(yValsTemp, mDataSet.getString("label"));
                        setTemp.setColor(Color.parseColor(mDataSet.getString("colors")));
                        setTemp.setBarSpacePercent((float) JSONObj.optDouble("barSpace", 15));
                        setTemp.setValueTextColor(Color.parseColor(mDataSet.optString("valueTextColor", "#000000")));
                        setTemp.setValueTextSize((float) JSONObj.optDouble("valueTextSize", 10));
                        dataSets.add(setTemp);
                    }
                    break;
                case STACKED:
                    JSONObject dataSet = mDataSets.getJSONObject(0);
                    JSONArray mYVals = dataSet.getJSONArray("yVals");
                    ArrayList<BarEntry> yValsTemp = new ArrayList<>();
                    for (int i = 0; i < mEntryCount; i++) {
                        JSONArray vals = mYVals.getJSONArray(i);
                        float[] yValsStr = new float[mSetCount];
                        for (int j = 0; j < mSetCount; j++) {
                            yValsStr[j] = (float) vals.getDouble(j);
                        }
                        yValsTemp.add(new BarEntry(yValsStr, i));
                    }
                    BarDataSet set = new BarDataSet(yValsTemp, "");
                    JSONArray label = dataSet.getJSONArray("labels");
                    JSONArray color = dataSet.getJSONArray("colors");
                    String[] labelStr = new String[mSetCount];
                    int[] colorStr = new int[mSetCount];
                    for (int i = 0; i < mSetCount; i++) {
                        labelStr[i] = label.getString(i);
                        colorStr[i] = Color.parseColor(color.optString(i));
                    }
                    set.setColors(colorStr);
                    set.setStackLabels(labelStr);
                    dataSets.add(set);
                    break;
            }

            BarData data = new BarData(xVals, dataSets);

            data.setGroupSpace((float) JSONObj.optDouble("entryCount", 80));

            mChart.setData(data);
//            mChart.animateY(2000);
            mChart.setPinchZoom(false);
            mChart.setDoubleTapToZoomEnabled(false);
            mChart.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
            mChart.clear();
            mChart.setNoDataText("数据还在路上...");
        }
    }

    /**
     * Subclasses should return a new View instance of the proper type.
     *
     * @param reactContext
     */
    @Override
    protected BarChart createViewInstance(ThemedReactContext reactContext) {
        BarChart mChart = new BarChart(reactContext);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setLabelCount(8, false);

//        setData(12, 50, mChart);

        return mChart;
    }

}
