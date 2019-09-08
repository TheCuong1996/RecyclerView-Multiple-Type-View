package com.example.recyclerview_multiple_type_view.other;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private final int padding;

    public GridSpacingItemDecoration() {
        this.padding = 5;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        assert gridLayoutManager != null;
        int spanCount = gridLayoutManager.getSpanCount();

        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();

        int spanIndex = params.getSpanIndex();
        int spanSize = params.getSpanSize();

        if (spanIndex == 0) {
            outRect.left = padding;
        } else {
            outRect.left = padding;
        }

        if (spanIndex + spanSize == spanCount) {
            outRect.right = padding;
        } else {
            outRect.right = padding;
        }

        outRect.top = padding;
        outRect.bottom = padding;
        if(isLayoutRTL(parent)) {
            int tmp = outRect.left;
            outRect.left = outRect.right;
            outRect.right = tmp;
        }
    }

    @SuppressLint({"NewApi", "WrongConstant"})
    private static boolean isLayoutRTL(RecyclerView parent) {
        return parent.getLayoutDirection() == ViewCompat.LAYOUT_DIRECTION_RTL;
    }
}
