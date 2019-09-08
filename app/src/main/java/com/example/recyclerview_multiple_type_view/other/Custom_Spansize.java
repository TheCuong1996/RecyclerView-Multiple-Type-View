package com.example.recyclerview_multiple_type_view.other;

import androidx.recyclerview.widget.GridLayoutManager;

public class Custom_Spansize extends GridLayoutManager.SpanSizeLookup {

    private int spanCnt1, spanCnt2;

    public Custom_Spansize(int spanCnt1, int spanCnt2) {
        super();
        this.spanCnt1 = spanCnt1;
        this.spanCnt2 = spanCnt2;
    }

    @Override
    public int getSpanSize(int position) {
        int result = 0;
        if(position >= 0 && position <= 1){
            result = spanCnt2;
        }
        else if(position >= 1 && position <= 2){
            result = spanCnt1;
        }
        return result;
    }
}
