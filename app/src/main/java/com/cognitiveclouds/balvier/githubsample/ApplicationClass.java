package com.cognitiveclouds.balvier.githubsample;

import android.app.Application;
import android.content.Context;

/**
 * Created by Balvier on 9/29/2017.
 */

public class ApplicationClass extends Application {
    private static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        this.CONTEXT = this;
    }

    public static Context getApplicationConotext() {
        return CONTEXT;
    }
}
