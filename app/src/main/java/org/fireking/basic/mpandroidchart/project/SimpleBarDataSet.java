package org.fireking.basic.mpandroidchart.project;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

public class SimpleBarDataSet extends BarDataSet {

    private List<BarEntry> yVals;

    public SimpleBarDataSet(List<BarEntry> yVals, String label) {
        super(yVals, label);
        this.yVals = yVals;
    }

    @Override
    public int getColor(int index) {
        //此处根据自己的需求填写相应的代码
        float value = yVals.get(index).getY();
        if (value <= 0) {
            return mColors.get(1);
        } else {
            return mColors.get(0);
        }
    }
}
