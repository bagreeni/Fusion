package com.callisto.fusion;

import android.app.Application;
import android.content.Context;

/**
 * Created by voxaelfox on 3/5/18.
 */

public class FusionApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        FusionApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return FusionApplication.context;
    }

}
