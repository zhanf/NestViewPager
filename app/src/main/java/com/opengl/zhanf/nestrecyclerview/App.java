package com.opengl.zhanf.nestrecyclerview;

import android.app.Application;

/**
 * Created by zhanf on 2018/1/30.
 */

public class App extends Application {

    private static App instance;

    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
