package com.angrychimps.appname.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.angrychimps.appname.R;

public class AvailabilityManager extends View {

    private int openTime;
    private int closeTime;
    private int serviceDuration;

    //How precise is the scheduling?
    enum Increment{ONE(1), FIVE(5), FIFTEEN(15), THIRTY(30), HOUR(60);

    private final int minutes;

        Increment(int minutes){
            this.minutes = minutes;
        }

        public int getMinutes() {
            return minutes;
        }
    }

    private Increment timeIncrement = Increment.THIRTY;
    private int colorSelected = R.color.accent;
    private int colorUnavailable = R.color.button_material_light;
    private int textColorSelected = R.color.white;
    private int textColorAccent = R.color.accent;
    private int textColorUnvailable = R.color.text_material_secondary_light;
    private int textColorTime = R.color.text_material_secondary_light;
    private final float dp = getResources().getDisplayMetrics().density;
    private int rowSpacing = (int) (48*dp);
    private int calendarPadding = (int) (16*dp);
    private int calendarElevation = (int) (4*dp);

    public AvailabilityManager(Context context) {
        super(context);
        init();
    }

    public AvailabilityManager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AvailabilityManager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
