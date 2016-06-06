package com.xiaomi.web.mp_android_chart;

/**
 * author:       joddiy <joddiy@qq.com>
 * time:         2016/6/6 18:10
 */

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.views.text.ReactTextShadowNode;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class MPAndroidBarChartManager extends BaseViewManager<BarChart, ReactTextShadowNode> {

    /**
     * @return the name of this view manager. This will be the name used to reference this view
     * manager from JavaScript in createReactNativeComponentClass.
     */
    @Override
    public String getName() {
        return "MPAndroidBarChart";
    }


    @ReactProp(name = "xAxis")
    public void setXAxis(BarChart mChart, @Nullable String JSONStr) {
        try {
            JSONObject JSONObj = JSONStr == null ? new JSONObject() : new JSONObject(JSONStr);
            //Position
            String Position = JSONObj.optString("position", "BOTTOM");
            XAxis xl = mChart.getXAxis();
            xl.setPosition(XAxis.XAxisPosition.valueOf(Position));
            xl.setTextColor(Color.parseColor(JSONObj.optString("textColor", "#000000")));
            xl.setTextSize((float) JSONObj.optDouble("textSize", 10));

            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private enum YAxisPosition {
        LEFT, RIGHT, BOTH
    }

    @ReactProp(name = "yAxis")
    public void setYAxis(BarChart mChart, @Nullable String JSONStr) {
        try {
            JSONObject JSONObj = JSONStr == null ? new JSONObject() : new JSONObject(JSONStr);
            YAxis mYAxis;
            //Position
            String Position = JSONObj.optString("position", "LEFT");
            switch (YAxisPosition.valueOf(Position)) {
                case LEFT:
                    mYAxis = mChart.getAxisLeft();
                    mYAxis.setValueFormatter(new LargeValueFormatter());
                    mChart.getAxisRight().setEnabled(false);
                    mYAxis.setTextColor(Color.parseColor(JSONObj.optString("textColor", "#000000")));
                    mYAxis.setTextSize((float) JSONObj.optDouble("textSize", 10));
                    break;
                case RIGHT:
                    mYAxis = mChart.getAxisRight();
                    mYAxis.setValueFormatter(new LargeValueFormatter());
                    mChart.getAxisLeft().setEnabled(false);
                    mYAxis.setTextColor(Color.parseColor(JSONObj.optString("textColor", "#000000")));
                    mYAxis.setTextSize((float) JSONObj.optDouble("textSize", 10));
                    break;
                case BOTH:
                    break;
            }
//            mYAxis.setDrawGridLines(false);
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactProp(name = "legend")
    public void setLegend(BarChart mChart, @Nullable String JSONStr) {
        try {
            JSONObject JSONObj = JSONStr == null ? new JSONObject() : new JSONObject(JSONStr);
            Legend l = mChart.getLegend();
            //Position
            String Position = JSONObj.optString("position", "BELOW_CHART_LEFT");
            l.setPosition(Legend.LegendPosition.valueOf(Position));
            //TextSize
            double TextSize = JSONObj.optDouble("textSize", 8f);
            l.setTextSize((float) TextSize);
            l.setTextColor(Color.parseColor(JSONObj.optString("textColor", "#000000")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private enum AnimateType {
        X, Y, XY
    }

    @ReactProp(name = "chartStyles")
    public void setChartStyles(BarChart mChart, @Nullable String JSONStr) {
        try {
            JSONObject JSONObj = JSONStr == null ? new JSONObject() : new JSONObject(JSONStr);
            //Animate
            String mAnimateType = JSONObj.optString("animateType", "Y");
            switch (AnimateType.valueOf(mAnimateType)) {
                case X:
                    mChart.animateX(JSONObj.optInt("animateX", 2000));
                    break;
                case Y:
                    mChart.animateY(JSONObj.optInt("animateY", 2000));
                    break;
                case XY:
                    mChart.animateXY(JSONObj.optInt("animateX", 2000), JSONObj.optInt("animateY", 2000));
                    break;
            }
            //Other
            mChart.setDescription(JSONObj.optString("description", ""));
            mChart.setBackgroundColor(Color.parseColor(JSONObj.optString("backgroundColor", "#FFFFFF")));
            mChart.setGridBackgroundColor(Color.parseColor(JSONObj.optString("gridBackgroundColor", "#FFFFFF")));

//            mChart.setDrawGridBackground(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private enum barTypes {
        COMMON, STACKED
    }

    @ReactProp(name = "dataSource")
    public void setDataSource(BarChart mChart, @Nullable String JSONStr) {
        try {
            JSONObject JSONObj = new JSONObject(JSONStr);

            int mEntryCount = JSONObj.getInt("entryCount");

            int mSetCount = JSONObj.getInt("setCount");

            JSONArray xValsArray = JSONObj.getJSONArray("xVals");

            ArrayList<String> xVals = new ArrayList<>();

            for (int i = 0; i < mEntryCount; i++) {
                xVals.add(xValsArray.getString(i));
            }

            JSONArray mDataSets = JSONObj.getJSONArray("dataSets");

            ArrayList<BarDataSet> dataSets = new ArrayList<>();

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
            data.setValueFormatter(new LargeValueFormatter());

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
     * This method should return a subclass of {@link ReactShadowNode} which will be then used for
     * measuring position and size of the view. In mose of the cases this should just return an
     * instance of {@link ReactShadowNode}
     */
    @Override
    public ReactTextShadowNode createShadowNodeInstance() {
        return new ReactTextShadowNode(false);
    }

    /**
     * This method should return {@link Class} instance that represent type of shadow node that this
     * manager will return from {@link #createShadowNodeInstance}.
     * <p/>
     * This method will be used in the bridge initialization phase to collect properties exposed using
     * {@link ReactProp} (or {@link ReactPropGroup}) annotation from the {@link ReactShadowNode}
     * subclass specific for native view this manager provides.
     *
     * @return {@link Class} object that represents type of shadow node used by this view manager.
     */
    @Override
    public Class<? extends ReactTextShadowNode> getShadowNodeClass() {
        return ReactTextShadowNode.class;
    }

    /**
     * Subclasses should return a new View instance of the proper type.
     *
     * @param reactContext
     */
    @Override
    protected BarChart createViewInstance(ThemedReactContext reactContext) {
        return  new BarChart(reactContext);
    }

    /**
     * Subclasses can implement this method to receive an optional extra data enqueued from the
     * corresponding instance of {@link ReactShadowNode} in
     * {@link ReactShadowNode#onCollectExtraUpdates}.
     * <p/>
     * Since css layout step and ui updates can be executed in separate thread apart of setting
     * x/y/width/height this is the recommended and thread-safe way of passing extra data from css
     * node to the native view counterpart.
     * <p/>
     * TODO(7247021): Replace updateExtraData with generic update props mechanism after D2086999
     *
     * @param root
     * @param extraData
     */
    @Override
    public void updateExtraData(BarChart root, Object extraData) {

    }
}
