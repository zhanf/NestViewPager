package com.opengl.zhanf.nestrecyclerview.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.opengl.zhanf.nestrecyclerview.utils.ConvertUtil;

import java.lang.reflect.Field;

public class VerticalViewPager extends ViewPager {

    public static final String TAG = "VerticalViewPager";

    public VerticalViewPager(Context context) {
        this(context, null);
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        fixTouchSlop();
    }

    private void init() {
        // The majority of the magic happens here
        setPageTransformer(true, new VerticalPageTransformer());
        // The easiest way to get rid of the overscroll drawing that happens on the left and right
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    /**
     * 这个方法是通过反射，修改viewpager的触发切换的最小滑动速度（还是距离？姑
     * 且是速度吧！滑了10dp就给它切换）
     **/
    private void fixTouchSlop() {
        Field field = null;
        try {
            field = ViewPager.class.getDeclaredField("mMinimumVelocity");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            if (null != field) {
                field.setAccessible(true);
                field.setInt(this, ConvertUtil.px2dp(10));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View view, float position) {

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                view.setAlpha(1);

                // Counteract the default slide transition
                view.setTranslationX(view.getWidth() * -position);

                //set Y position to swipe in from top
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }


    private int xInterceptLast;
    private int yInterceptLast;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "xLastDown:" + xInterceptLast);
                Log.d(TAG, "yLastDown:" + yInterceptLast);

                xInterceptLast = (int) ev.getX();
                yInterceptLast = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final int curX = (int) ev.getX();
                final int curY = (int) ev.getY();
                Log.d(TAG, "curXMove:" + curX);
                Log.d(TAG, "curYMove:" + curY);
                int xDiff = curX - xInterceptLast;
                int yDiff = curY - yInterceptLast;
                int xAbsDiff = Math.abs(xDiff);
                int yAbsDiff = Math.abs(yDiff);
                Log.d(TAG, "xDiffMove:" + xDiff);
                Log.d(TAG, "yDiffMove:" + yDiff);
                if (yAbsDiff > xAbsDiff && yDiff <= 0) {//竖直方向手指从下往上滑动，即 ViewPager 向下一页滑动时，事件由 ViewPager 自身处理
                    Log.d(TAG, "onInterceptTouchEvent");
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                xInterceptLast = (int) ev.getX();
                yInterceptLast = (int) ev.getY();
                Log.d(TAG, "xLastUp:" + xInterceptLast);
                Log.d(TAG, "yLastUp:" + yInterceptLast);
                break;
        }
        boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
        swapXY(ev); // return touch coordinates to original reference frame for any child views
        return intercepted;
    }


    private int xDispatchLast;
    private int yDispatchLast;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "xLastDown:" + xInterceptLast);
                Log.d(TAG, "yLastDown:" + yInterceptLast);

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
                if (yAbsDiff > xAbsDiff && yDiff > 0) {//竖直方向手指从上往下滑动，即 ViewPager 向前一页滑动时，不进行事件处理即不可向下滑动
                    Log.d(TAG, "dispatchTouchEvent");
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                xDispatchLast = (int) ev.getX();
                yDispatchLast = (int) ev.getY();
                Log.d(TAG, "xLastUp:" + xDispatchLast);
                Log.d(TAG, "yLastUp:" + yDispatchLast);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapXY(ev));
    }
}