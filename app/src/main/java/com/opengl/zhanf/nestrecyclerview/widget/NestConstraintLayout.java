package com.opengl.zhanf.nestrecyclerview.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 静止 parentView 处理事件
 * Created by zhanf on 2018/3/30.
 */

public class NestConstraintLayout extends ConstraintLayout {
    public static final String TAG = "NestConstraintLayout";

    public NestConstraintLayout(@NonNull Context context) {
        super(context);
    }

    public NestConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int xDispatchLast;
    private int yDispatchLast;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "xLastDown:" + xDispatchLast);
                Log.d(TAG, "yLastDown:" + yDispatchLast);

                xDispatchLast = (int) ev.getX();
                yDispatchLast = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final int curX = (int) ev.getX();
                final int curY = (int) ev.getY();
                Log.d(TAG, "curXMove:" + curX);
                Log.d(TAG, "curYMove:" + curY);
                int xDiff = curX - xDispatchLast;
                int yDiff = curY - yDispatchLast;
                int xAbsDiff = Math.abs(xDiff);
                int yAbsDiff = Math.abs(yDiff);
                Log.d(TAG, "xDiffMove:" + xDiff);
                Log.d(TAG, "yDiffMove:" + yDiff);
                if (yAbsDiff < xAbsDiff || (yAbsDiff > xAbsDiff && yDiff > 0)) {//水平滑动时拦截父控件的事件
                    Log.d(TAG, "requestDisallowInterceptTouchEvent");
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                xDispatchLast = (int) ev.getX();
                yDispatchLast = (int) ev.getY();
                Log.d(TAG, "xLastUp:" + xDispatchLast);
                Log.d(TAG, "yLastUp:" + yDispatchLast);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }


}
