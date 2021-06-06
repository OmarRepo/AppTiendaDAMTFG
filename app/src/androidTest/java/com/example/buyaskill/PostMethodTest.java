package com.example.buyaskill;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android.volley.VolleyError;
import com.example.buyaskill.helpers.Callbacks.VolleyJSONArrayCallback;
import com.example.buyaskill.models.Paquete;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class PostMethodTest {
    @Test
    public void obtenerPaquetes() {
        try {
            Paquete.obtenerPaquetes(new VolleyJSONArrayCallback() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   fail();
               }
               @Override
               public void onSuccessResponse(JSONArray response) {
                    assertNotNull(response);
               }
           });
        } catch (JSONException e) {
            fail();
            e.printStackTrace();
        }
    }
}
