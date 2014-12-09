package com.angrychimps.appname;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;


//Modified listView for use with Quick Return.
public class QuickReturnListView extends ListView {

    private int itemCount, height;
    private int itemOffsetY[];
    private boolean scrollIsComputed = false;

    public QuickReturnListView(Context context) {super(context);}
    public QuickReturnListView(Context context, AttributeSet attrs) {super(context, attrs);}
    public int getListHeight() {return height;}

    public void computeScrollY() {
        height = 0;
        itemCount = getAdapter().getCount();
        if (itemOffsetY == null) {
            itemOffsetY = new int[itemCount];
        }
        for (int i = 0; i < itemCount; ++i) {
            View view = getAdapter().getView(i, null, this);
            view.measure(
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            itemOffsetY[i] = height;
            height += view.getMeasuredHeight();
            System.out.println(height);
        }
        scrollIsComputed = true;
    }

    public boolean scrollYIsComputed() {
        return scrollIsComputed;
    }

    public int getComputedScrollY() {
        int pos, nScrollY, nItemY;
        View view = null;
        pos = getFirstVisiblePosition();
        view = getChildAt(0);
        nItemY = view.getTop();
        nScrollY = itemOffsetY[pos] - nItemY;
        return nScrollY;
    }
}
