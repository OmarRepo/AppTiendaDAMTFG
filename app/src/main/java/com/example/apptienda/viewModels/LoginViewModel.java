package com.example.apptienda.viewModels;

import android.util.Patterns;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apptienda.App;
import com.example.apptienda.models.Usuario;
import com.example.apptienda.models.VolleyCallback;
import com.google.gson.Gson;

import org.json.JSONObject;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<String> email;
    private MutableLiveData<String> password;
    public final ObservableField<String> Email;
    public final ObservableField<String> Password;
    private MutableLiveData<Usuario> usuario;
    public LoginViewModel() {
        super();
        Email= new ObservableField<String>("");
        Password=new ObservableField<String>("");
        email=new MutableLiveData<String>();
        password=new MutableLiveData<String>();
        usuario=new MutableLiveData<Usuario>();
    }
    public Usuario login() {
        if(getFields()) {
            Usuario.LogIn(email.getValue(), password.getValue(), new VolleyCallback() {
                @Override
                public void onSuccessResponse(String result) {

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
    private boolean getFields() {
        if(validateFields()){
            email.setValue(Email.get());
            password.setValue(Password.get());
            return true;
        }
        return false;
    }
    public boolean validateFields() {
        return Patterns.EMAIL_ADDRESS.matcher(Email.get()).matches() && !Password.get().equals("");
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
