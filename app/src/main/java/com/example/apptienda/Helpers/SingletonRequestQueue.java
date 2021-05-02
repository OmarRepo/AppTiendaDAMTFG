package com.example.apptienda.Helpers;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonRequestQueue {
    private static SingletonRequestQueue instance;
    private RequestQueue queue;
    private String url;

    private SingletonRequestQueue(Context context) {
        queue = Volley.newRequestQueue(context.getApplicationContext());
    }
    public static synchronized SingletonRequestQueue getInstance(Context context) {
        if(instance==null) {
            instance = new SingletonRequestQueue(context);
        }
        return instance;
    }

    public RequestQueue getQueue() {
        return queue;
    }
}
