package com.example.apptienda.models;


import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface VolleyCallback {
    void onSuccessResponse(String result);
    void onSuccessResponse(JSONObject result);
    void onErrorResponse(VolleyError error);
}
