package com.sreevidya.opal.data;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Created by Vidya on 10/21/17.
 */

public class ChartData {

    public static ArrayList<BarEntry> getBatData(int count, float range) {
        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = (int) start; i < start + count + 1; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);

            yVals1.add(new BarEntry(i, val));

        }
        return yVals1;
    }
}
