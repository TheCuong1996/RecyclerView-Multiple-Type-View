package com.example.recyclerview_multiple_type_view.other;

import androidx.recyclerview.widget.GridLayoutManager;

public class Custom_Spansize_RCV  extends GridLayoutManager.SpanSizeLookup{

    private int spanCnt1, spanCnt2;

    public Custom_Spansize_RCV(int spanCnt1, int spanCnt2) {
        super();
        this.spanCnt1 = spanCnt1;
        this.spanCnt2 = spanCnt2;
    }

    @Override
    public int getSpanSize(int i) {
        int result = 0;
        if(i >= 0 && i <= 3){
            result = spanCnt2;
        }
        else if(i >= 3){
            result = spanCnt1;
        }
        return result;
    }
}
