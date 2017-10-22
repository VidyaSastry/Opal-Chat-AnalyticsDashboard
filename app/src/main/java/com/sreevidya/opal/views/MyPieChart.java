package com.sreevidya.opal.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;


public class MyPieChart extends PieChart {
    public MyPieChart(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MyPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MyPieChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        setUsePercentValues(true);
        getDescription().setEnabled(false);
        setExtraOffsets(5, 10, 5, 5);
        setDragDecelerationFrictionCoef(0.95f);

        setDrawHoleEnabled(true);
        setHoleColor(Color.WHITE);

        setTransparentCircleColor(Color.WHITE);
        setTransparentCircleAlpha(110);

        setHoleRadius(58f);
        setTransparentCircleRadius(61f);

        setDrawCenterText(true);

        setRotationAngle(0);
        // enable rotation of the chart by touch
        setRotationEnabled(true);
        setHighlightPerTapEnabled(true);


        Legend l = getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        setEntryLabelColor(Color.BLACK);
        setEntryLabelTextSize(12f);
    }
}
