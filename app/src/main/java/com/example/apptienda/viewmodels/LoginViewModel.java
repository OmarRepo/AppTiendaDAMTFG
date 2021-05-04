package com.example.apptienda.viewmodels;

import android.util.Patterns;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apptienda.models.Usuario;
import com.example.apptienda.models.VolleyCallback;
import com.google.gson.Gson;

import org.json.JSONObject;

public class LoginViewModel extends ViewModel {
    public final ObservableField<String> email;
    public final ObservableField<String> password;
    private MutableLiveData<Usuario> usuario;
    public LoginViewModel() {
        super();
        email= new ObservableField<>("");
        password=new ObservableField<>("");
        usuario=new MutableLiveData<>();
    }
    public Usuario login() {
        if(validateFields()) {
            Usuario.LogIn(email.get(), password.get(), new VolleyCallback() {
                @Override
                public void onSuccessResponse(String result) {
                    usuario.setValue(null);
                }
                @Override
                public void onSuccessResponse(JSONObject result) {
                    Gson gson=new Gson();
                    usuario.setValue(gson.fromJson(gson.toJson(result),Usuario.class));
                }
            });
        }
        return usuario.getValue();
    }
    private boolean validateFields() {
        return Usuario.validatePassword(password.get()) && Usuario.validateEmail(email.get());
    }
}
