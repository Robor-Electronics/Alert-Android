package org.robor.ui.Cells;

import android.content.Context;
import android.view.View;

import org.robor.messenger.AndroidUtilities;

public class FixedHeightEmptyCell extends View {

    int heightInDp;

    public FixedHeightEmptyCell(Context context, int heightInDp) {
        super(context);
        this.heightInDp = heightInDp;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(heightInDp), MeasureSpec.EXACTLY));
    }
}