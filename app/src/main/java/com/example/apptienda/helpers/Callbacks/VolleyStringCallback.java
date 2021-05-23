package com.example.apptienda.helpers.Callbacks;

import com.android.volley.VolleyError;


public interface VolleyStringCallback {
    void onSuccessResponse(String result);
    void onErrorResponse(VolleyError error);
}
