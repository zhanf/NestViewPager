package com.opengl.zhanf.nestrecyclerview.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

import com.opengl.zhanf.nestrecyclerview.App;


/**
 * Created by zhanf on 2018/3/21.
 */

public class ConvertUtil {

    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) App.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Point p = new Point();
        wm.getDefaultDisplay().getSize(p);
        return p.x;
    }

    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) App.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Point p = new Point();
        wm.getDefaultDisplay().getSize(p);
        return p.y;
    }

    public static int dp2px(float dpValue) {
        final float scale = App.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(float pxValue) {
        final float scale = App.getInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
