package com.example.apptienda;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.android.volley.VolleyError;
import com.example.apptienda.models.Paquete;
import com.example.apptienda.models.Usuario;
import com.example.apptienda.models.VolleyCallback;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

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
            ArrayList<Paquete> paquetes = (ArrayList<Paquete>) Paquete.obtenerPaquetes();
            assertNotNull(paquetes);
        } catch (ExecutionException e) { Log.e("error: ",e.getCause().toString());
           fail();
        } catch (InterruptedException e) {
           fail();
        }
    }
}
