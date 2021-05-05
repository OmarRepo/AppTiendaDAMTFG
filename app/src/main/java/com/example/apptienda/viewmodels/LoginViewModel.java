package com.example.apptienda.viewmodels;

import android.content.Intent;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.apptienda.App;
import com.example.apptienda.RegisterFormActivity;
import com.example.apptienda.models.DataRepository;
import com.example.apptienda.models.Usuario;
import com.example.apptienda.models.VolleyCallback;
import com.google.gson.Gson;

import org.json.JSONObject;

public class LoginViewModel extends ViewModel {
    public final ObservableField<String> email;
    public final ObservableField<String> password;
    public LoginViewModel() {
        super();
        email= new ObservableField<>("");
        password=new ObservableField<>("");
    }
    public void login() {
        if(validateFields()) {

            Usuario.LogIn(email.get(), password.get(), new VolleyCallback() {
                @Override
                public void onSuccessResponse(String result) {

                }
                @Override
                public void onSuccessResponse(JSONObject result) {
                    Gson gson=new Gson();
                    DataRepository.setUsuarioLogeado(gson.fromJson(result.toString(),Usuario.class));
                    navigateToHome();
                }
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(App.getContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private boolean validateFields() {
        return Usuario.validatePassword(password.get()) && Usuario.validateEmail(email.get());
    }
    private void navigateToHome() {
        Intent it=new Intent(App.getContext(), RegisterFormActivity.class);
        App.getApplication().startActivity(it);
    }
}
