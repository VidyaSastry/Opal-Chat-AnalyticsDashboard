package com.sreevidya.opal.data;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;


public class ChartData {

    public static ArrayList<BarEntry> getBarData(int count, float range) {
        float start = 1f;

        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();

        for (int i = (int) start; i < start + count + 1; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);

            yValues.add(new BarEntry(i, val));
        }
        return yValues;
    }

    public static ArrayList<Entry> getData(int count, float range) {
        ArrayList<Entry> yValues = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float mult = range / 2f;
            float val = (float) (Math.random() * mult) + 50;
            yValues.add(new Entry(i, val));
        }
        return yValues;
    }

    public static ArrayList<PieEntry> getPieData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        String[] mParties = new String[count];
        mParties[0] = "One";
        mParties[1] = "Two";
        mParties[2] = "Three";
        mParties[3] = "Four";

        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    mParties[i % mParties.length]));
        }

        return entries;
    }
}
