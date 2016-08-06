package com.example.tmnt.bottomwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by tmnt on 2016/8/6.
 */
public class ScrollviewListView extends ListView {
    public ScrollviewListView(Context context) {
        super(context);
    }

    public ScrollviewListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);


    }
}
