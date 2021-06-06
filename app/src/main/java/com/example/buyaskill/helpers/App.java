package com.example.buyaskill.helpers;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Application app;

    public static Application getApplication() {
        return app;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
