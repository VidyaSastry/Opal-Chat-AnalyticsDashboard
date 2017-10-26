package com.sreevidya.opal.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

public class MyHorizontalBarChart extends HorizontalBarChart {
    public MyHorizontalBarChart(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MyHorizontalBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MyHorizontalBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        setDrawBarShadow(false);
        setDrawValueAboveBar(true);
        getDescription().setEnabled(false);
        setMaxVisibleValueCount(60);
        setDrawGridBackground(false);

        XAxis xl = getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(10f);
        xl.setTextColor(Color.GRAY);

        YAxis yl = getAxisLeft();
        yl.setDrawAxisLine(false);
        yl.setDrawGridLines(false);
        yl.setDrawLabels(false);
        yl.setAxisMinimum(0f);
        yl.setTextColor(Color.GRAY);

        YAxis yr = getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);
        yl.setTextColor(Color.GRAY);

        setFitBars(true);
        animateY(2500);

        Legend l = getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);

    }
}
