package com.example.buyaskill.helpers.Callbacks;

import com.android.volley.VolleyError;


public interface VolleyStringCallback {
    void onSuccessResponse(String result);
    void onErrorResponse(VolleyError error);
}
