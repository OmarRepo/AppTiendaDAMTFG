package com.example.apptienda;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.apptienda.models.Paquete;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
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
