package com.example.apptienda;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.apptienda.models.Usuario;

import org.junit.Test;
import org.junit.runner.RunWith;

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
        try {
            Usuario usu = Usuario.LogIn("ivan@gmail.xd","prueba123");
            assertNotNull(usu);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void login_error() {
        try {
            Usuario usu = Usuario.LogIn("ivan@gmail.xd","prueba120");
            assertNull(usu);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}