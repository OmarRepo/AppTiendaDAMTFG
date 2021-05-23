package com.example.apptienda;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android.volley.VolleyError;
import com.example.apptienda.helpers.Callbacks.VolleyJSONCallback;
import com.example.apptienda.models.Usuario;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    //@Test
    //public void useAppContext() {
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //assertEquals("com.example.apptienda", appContext.getPackageName());
    //}
    @Test
    public void login() {
        try {
            Usuario.LogIn("ivan@gmail.xd","prueba123",new VolleyJSONCallback(){
                @Override
                public void onSuccessResponse(JSONObject result) {
                    assertNotNull(result);
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }catch (JSONException e) {

        }
    }
    @Test
    public void login_error() {
        try {
            Usuario.LogIn("ivan@gmail.xd","prueba120",new VolleyJSONCallback(){
                @Override
                public void onSuccessResponse(JSONObject result) {
                    assertNotNull(result);
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    assertNotNull(error);
                }
            });
        }catch (JSONException e) {

        }
    }
}