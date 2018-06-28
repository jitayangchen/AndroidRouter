package cn.com.findfine.androidrouter;

import android.app.Application;

import cn.com.findfine.library.RouterEngine;

public class ARApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RouterEngine.init();
    }
}
