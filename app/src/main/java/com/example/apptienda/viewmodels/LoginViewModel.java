package com.example.apptienda.viewmodels;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.apptienda.App;
import com.example.apptienda.HomeActivity;
import com.example.apptienda.RegisterFormActivity;
import com.example.apptienda.helpers.Callbacks.VolleyJSONCallback;
import com.example.apptienda.models.DataRepository;
import com.example.apptienda.models.Usuario;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class LoginViewModel extends ViewModel {
    public final ObservableField<String> email;
    public final ObservableField<String> password;
    public final ObservableBoolean actionsEnabled;
    public LoginViewModel() {
        super();
        email= new ObservableField<>("");
        password=new ObservableField<>("");
        actionsEnabled=new ObservableBoolean(true);
    }
    public void login() {
        if(validateFields()) {
            try {
                Usuario.LogIn(email.get(), password.get(), new VolleyJSONCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) {
                        Gson gson=new Gson();
                        Usuario usu = gson.fromJson(result.toString(),Usuario.class);
                        DataRepository.postUsuarioLogeado(usu);
                        navigateToHome();
                    }
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean validateFields() {
        return Usuario.validatePassword(password.get()) && Usuario.validateEmail(email.get());
    }
    private void navigateToHome() {
        actionsEnabled.set(false);
        Intent it=new Intent(App.getContext(), HomeActivity.class);
        App.getContext().startActivity(it);
        actionsEnabled.set(true);
    }
    private void navigateToRegister() {
        actionsEnabled.set(false);
        Intent it=new Intent(App.getContext(), RegisterFormActivity.class);
        App.getContext().startActivity(it);
        actionsEnabled.set(true);
    }
}
