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
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.annotation.Nullable;


public class MPAndroidPieChartManager extends BaseViewManager<PieChart, ReactTextShadowNode> {

    @VisibleForTesting
    public static final String REACT_CLASS = "MPAndroidPieChart";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    private enum AnimateType {
        X, Y
    }

    @ReactProp(name = "chartStyles")
    public void setChartStyles(PieChart mChart, @Nullable String JSONStr) {
        try {
            JSONObject JSONObj = JSONStr == null ? new JSONObject() : new JSONObject(JSONStr);
            //Animate
            String mAnimateType = JSONObj.optString("animateType", "Y");
            switch (AnimateType.valueOf(mAnimateType)) {
                case X:
                    mChart.animateX(JSONObj.optInt("animateX", 2000), Easing.EasingOption.EaseInOutQuad);
                    break;
                case Y:
                    mChart.animateY(JSONObj.optInt("animateY", 2000), Easing.EasingOption.EaseInOutQuad);
                    break;
            }
            //Other
            mChart.setDescription(JSONObj.optString("description", ""));
            mChart.setUsePercentValues(JSONObj.optBoolean("usePercentValues", true));
            mChart.setDragDecelerationFrictionCoef((float) JSONObj.optDouble("dragDecelerationFrictionCoef", 0.95f));
            mChart.setDrawHoleEnabled(JSONObj.optBoolean("drawHoleEnabled", true));
            mChart.setHoleColorTransparent(JSONObj.optBoolean("holeColorTransparent", true));
            mChart.setTransparentCircleColor(Color.parseColor(JSONObj.optString("transparentCircleColor", "#FFFFFF")));
            mChart.setTransparentCircleAlpha(JSONObj.optInt("TransparentCircleAlpha", 110));
            mChart.setHoleRadius((float) JSONObj.optDouble("holeRadius", 58f));
            mChart.setTransparentCircleRadius((float) JSONObj.optDouble("transparentCircleRadius", 61f));
            mChart.setDrawCenterText(JSONObj.optBoolean("drawCenterText", true));
            mChart.setRotationAngle((float) JSONObj.optDouble("rotationAngle", 0));
            mChart.setRotationEnabled(JSONObj.optBoolean("rotationEnabled", true));
            mChart.setCenterText(JSONObj.optString("centerText", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactProp(name = "legend")
    public void setLegend(PieChart mChart, @Nullable String JSONStr) {
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

    @ReactProp(name = "dataSource")
    public void setDataSource(PieChart mChart, @Nullable String JSONStr) {
        try {
            JSONObject JSONObj =  new JSONObject(JSONStr);

            int mEntryCount = JSONObj.getInt("entryCount");

            JSONArray xValsArray = JSONObj.getJSONArray("xVals");

            ArrayList<String> xVals = new ArrayList<>();

            for (int i = 0; i < mEntryCount; i++) {
                xVals.add(xValsArray.getString(i));
            }

            JSONArray mDataSets = JSONObj.getJSONArray("dataSets");

            JSONObject mDataSet = mDataSets.getJSONObject(0);
            JSONArray mYVals = mDataSet.getJSONArray("yVals");
            JSONArray colors = mDataSet.getJSONArray("colors");
            ArrayList<Entry> yValsTemp = new ArrayList<>();
            ArrayList<Integer> colorStr = new ArrayList<>();
            for (int i = 0; i < mEntryCount; i++) {
                yValsTemp.add(new Entry((float) mYVals.getDouble(i), i));
                colorStr.add(Color.parseColor(colors.getString(i)));
            }
            PieDataSet setTemp = new PieDataSet(yValsTemp, "");
            setTemp.setColors(colorStr);
            setTemp.setSelectionShift(0f);

            PieData data = new PieData(xVals, setTemp);
            if (mDataSet.optBoolean("valueFormatter", true))
                data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize((float) mDataSet.optDouble("valueTextSize", 11f));
            data.setValueTextColor(Color.parseColor(mDataSet.optString("valueTextColor", "#FFFFFF")));
            mChart.setData(data);

            // undo all highlights
            mChart.highlightValues(null);
//            mChart.animateY(2000);
            mChart.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
            mChart.clear();
            mChart.setNoDataText("数据还在路上...");
        }


    }

    @Override
    public PieChart createViewInstance(ThemedReactContext context) {
        return new PieChart(context);
    }


    @Override
    public void updateExtraData(PieChart view, Object extraData) {

    }

    @Override
    public ReactTextShadowNode createShadowNodeInstance() {
        return new ReactTextShadowNode(false);
    }

    @Override
    public Class<ReactTextShadowNode> getShadowNodeClass() {
        return ReactTextShadowNode.class;
    }

}
