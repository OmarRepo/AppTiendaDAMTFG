package com.example.apptienda.viewmodels;

import android.content.Intent;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.apptienda.App;
import com.example.apptienda.HomeActivity;
import com.example.apptienda.RegisterFormActivity;
import com.example.apptienda.models.DataRepository;
import com.example.apptienda.models.Usuario;
import com.example.apptienda.models.VolleyCallback;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

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
            try {
                Usuario usu = Usuario.LogIn(email.get(), password.get());
                DataRepository.setUsuarioLogeado(usu);
                navigateToHome();
            } catch (ExecutionException e) {
                Toast.makeText(App.getContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            } catch (InterruptedException e) {
                Toast.makeText(App.getContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean validateFields() {
        return Usuario.validatePassword(password.get()) && Usuario.validateEmail(email.get());
    }
    private void navigateToHome() {
        Intent it=new Intent(App.getContext(), HomeActivity.class);
        App.getApplication().startActivity(it);
    }
}
