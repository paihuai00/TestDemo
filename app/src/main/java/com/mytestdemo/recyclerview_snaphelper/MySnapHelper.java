package com.mytestdemo.recyclerview_snaphelper;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by TJXF-JY-WEIDINGQIANG on 2017/4/5.
 */

public class MySnapHelper extends LinearSnapHelper {
    private int[] out = new int[2];

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) targetView.getLayoutParams();
        int width = layoutManager.getDecoratedMeasuredWidth(targetView) + params.leftMargin + params.rightMargin;
        int childLeft = layoutManager.getDecoratedLeft(targetView) - params.leftMargin;
        int parentPaddingLeft = layoutManager.getPaddingLeft();
        if (childLeft >= parentPaddingLeft) {
            out[0] = childLeft - parentPaddingLeft;
        } else if (parentPaddingLeft - childLeft > width / 2) {
            out[0] = childLeft + width-parentPaddingLeft;
        } else {
            out[0] = childLeft - parentPaddingLeft;
        }
        return out;
    }

    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int firstVizPosition = linearLayoutManager.findFirstVisibleItemPosition();
            if (firstVizPosition >= 0) {
                return layoutManager.findViewByPosition(firstVizPosition);
            }
        }
        return null;
    }
}
