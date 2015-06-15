package com.raizlabs.android.dbflow;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(this);
    }
}
