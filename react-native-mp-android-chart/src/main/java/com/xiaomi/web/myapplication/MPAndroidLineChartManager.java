/**
 *
 * Created by joddiy@qq.com on 15-11-23 10:45.
 */

package com.xiaomi.web.myapplication;

import android.graphics.Color;

import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.text.ReactTextShadowNode;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.annotation.Nullable;


public class MPAndroidLineChartManager extends BaseViewManager<LineChart, ReactTextShadowNode> {

    @VisibleForTesting
    public static final String REACT_CLASS = "MPAndroidLineChart";

    @Override
    public String getName() {
        return REACT_CLASS;
    }


    @ReactProp(name = "xAxis")
    public void setXAxis(LineChart mChart, @Nullable String JSONStr) {
        try {
            JSONObject JSONObj = JSONStr == null ? new JSONObject() : new JSONObject(JSONStr);
            //Position
            String Position = JSONObj.optString("position", "BOTTOM");
            XAxis xl = mChart.getXAxis();
            xl.setPosition(XAxis.XAxisPosition.valueOf(Position));
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private enum YAxisPosition {
        LEFT, RIGHT, BOTH
    }

    @ReactProp(name = "yAxis")
    public void setYAxis(LineChart mChart, @Nullable String JSONStr) {
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
                    break;
                case RIGHT:
                    mYAxis = mChart.getAxisRight();
                    mYAxis.setValueFormatter(new LargeValueFormatter());
                    mChart.getAxisLeft().setEnabled(false);
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
    public void setLegend(LineChart mChart, @Nullable String JSONStr) {
        try {
            JSONObject JSONObj = JSONStr == null ? new JSONObject() : new JSONObject(JSONStr);
            Legend l = mChart.getLegend();
            //Position
            String Position = JSONObj.optString("position", "BELOW_CHART_LEFT");
            l.setPosition(Legend.LegendPosition.valueOf(Position));
            //TextSize
            double TextSize = JSONObj.optDouble("textSize", 8f);
            l.setTextSize((float) TextSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private enum AnimateType {
        X, Y, XY
    }

    @ReactProp(name = "chartStyles")
    public void setChartStyles(LineChart mChart, @Nullable String JSONStr) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactProp(name = "dataSource")
    public void setDataSource(LineChart mChart, @Nullable String JSONStr) {
        try {
            JSONObject JSONObj =  new JSONObject(JSONStr);

            int mEntryCount = JSONObj.getInt("entryCount");

            int mSetCount = JSONObj.getInt("setCount");

            JSONArray xValsArray = JSONObj.getJSONArray("xVals");

            ArrayList<String> xVals = new ArrayList<>();

            for (int i = 0; i < mEntryCount; i++) {
                xVals.add(xValsArray.getString(i));
            }

            JSONArray mDataSets = JSONObj.getJSONArray("dataSets");

            ArrayList<LineDataSet> dataSets = new ArrayList<>();

            for (int i = 0; i < mSetCount; i++) {
                JSONObject mDataSet = mDataSets.getJSONObject(i);
                JSONArray mYVals = mDataSet.getJSONArray("yVals");
                ArrayList<Entry> yValsTemp = new ArrayList<>();
                for (int j = 0; j < mEntryCount; j++) {
                    yValsTemp.add(new Entry((float) mYVals.getDouble(j), j));
                }
                LineDataSet setTemp = new LineDataSet(yValsTemp, mDataSet.getString("label"));
                setTemp.setColor(Color.parseColor(mDataSet.getString("colors")));
                setTemp.setLineWidth((float)mDataSet.optDouble("lineWidth",1.5f));
                setTemp.setCircleSize((float)mDataSet.optDouble("circleSize",4f));
                dataSets.add(setTemp);
            }

            LineData data = new LineData(xVals, dataSets);
            data.setValueFormatter(new LargeValueFormatter());

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


    @Override
    public LineChart createViewInstance(ThemedReactContext context) {
        return new LineChart(context);
    }


    @Override
    public void updateExtraData(LineChart view, Object extraData) {

    }

    @Override
    public ReactTextShadowNode createShadowNodeInstance() {
        return new ReactTextShadowNode(false);
    }

    @Override
    public Class<ReactTextShadowNode> getShadowNodeClass() {
        return ReactTextShadowNode.class;
    }

    protected String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };

}
