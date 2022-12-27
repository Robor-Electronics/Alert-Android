package org.robor.ui.Charts.view_data;

import android.graphics.Paint;

import org.robor.ui.Charts.BaseChartView;
import org.robor.ui.Charts.data.ChartData;

public class StackLinearViewData extends LineViewData {

    public StackLinearViewData(ChartData.Line line) {
        super(line);
        paint.setStyle(Paint.Style.FILL);
        if (BaseChartView.USE_LINES) {
            paint.setAntiAlias(false);
        }
    }
}
