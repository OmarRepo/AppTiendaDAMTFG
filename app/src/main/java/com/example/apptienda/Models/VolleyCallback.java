package com.example.apptienda.Models;


import org.json.JSONObject;

public interface VolleyCallback {
    void onSuccessResponse(String result);
    void onSuccessResponse(JSONObject result);
}
