package com.example.apptienda;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android.volley.VolleyError;
import com.example.apptienda.models.Usuario;
import com.example.apptienda.models.VolleyCallback;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
        CompletableFuture<String> future = new CompletableFuture<>();

        Usuario.LogIn("ivan@gmail.xd","prueba123", new VolleyCallback(){
            @Override
            public void onSuccessResponse(String result) {
                future.complete("fail");
            }
            @Override
            public void onSuccessResponse(JSONObject result) {
                future.complete("ok");
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                future.complete("fail");
            }
        });
        try {
            assertEquals("ok", future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void login_error() {
        CompletableFuture<String> future = new CompletableFuture<>();

        Usuario.LogIn("ivan@gmail.xd","prueba120", new VolleyCallback(){
            @Override
            public void onSuccessResponse(String result) {
                future.complete("fail");
            }

            @Override
            public void onSuccessResponse(JSONObject result) {
                future.complete("fail");
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                future.complete("ok");
            }
        });
        try {
            assertEquals("ok", future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}