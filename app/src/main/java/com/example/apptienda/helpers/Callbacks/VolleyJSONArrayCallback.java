package com.example.apptienda.helpers.Callbacks;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public interface VolleyJSONArrayCallback {
    void onSuccessResponse(JSONArray result);
    void onErrorResponse(VolleyError error);
}
