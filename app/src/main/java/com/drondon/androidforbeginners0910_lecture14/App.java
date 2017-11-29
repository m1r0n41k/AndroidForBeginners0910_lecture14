package com.drondon.androidforbeginners0910_lecture14;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by andriimiroshnychenko on 11/29/17.
 */

public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Fabric.with(this, new Crashlytics());
    }

    public static App get() {
        return app;
    }
}
