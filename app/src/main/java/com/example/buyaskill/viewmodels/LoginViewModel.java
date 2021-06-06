package com.example.buyaskill.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.buyaskill.helpers.App;
import com.example.buyaskill.HomeActivity;
import com.example.buyaskill.RegisterFormActivity;
import com.example.buyaskill.helpers.Callbacks.VolleyJSONCallback;
import com.example.buyaskill.models.DataRepository;
import com.example.buyaskill.models.Usuario;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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
                        SharedPreferences sharedPref =App.getContext().getSharedPreferences("session",Context.MODE_PRIVATE);
                        sharedPref.edit().putString("email",email.get()).putString("password",password.get()).apply();
                        navigateToHome();
                    }
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(App.getContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void directLogin() {
        SharedPreferences sharedPref =App.getContext().getSharedPreferences("session",Context.MODE_PRIVATE);
        String emailSaved=sharedPref.getString("email","vacio");
        String passwordSaved=sharedPref.getString("password","vacio");
        if(!emailSaved.equals("vacio")) {
            try {
                Usuario.LogIn(emailSaved, passwordSaved, new VolleyJSONCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) {
                        Gson gson=new Gson();
                        Usuario usu = gson.fromJson(result.toString(),Usuario.class);
                        DataRepository.postUsuarioLogeado(usu);
                        navigateToHome();
                    }
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(App.getContext(), "Contraseña cambiada, vuelva a iniciar sesion", Toast.LENGTH_SHORT).show();
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
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
