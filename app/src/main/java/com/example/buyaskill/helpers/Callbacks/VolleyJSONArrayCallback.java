package com.example.buyaskill.helpers.Callbacks;

import com.android.volley.VolleyError;

import org.json.JSONArray;

public interface VolleyJSONArrayCallback {
    void onSuccessResponse(JSONArray result);
    void onErrorResponse(VolleyError error);
}
