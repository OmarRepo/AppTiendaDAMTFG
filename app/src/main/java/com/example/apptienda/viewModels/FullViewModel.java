package com.example.apptienda.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.apptienda.models.Usuario;
import com.example.apptienda.models.VolleyCallback;

import org.json.JSONObject;

public class FullViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuario;
    private MutableLiveData<String> password;
    public MutableLiveData<Usuario> getCurrentUser() {
        return usuario;
    }
    public FullViewModel() {
        super();
        usuario=new MutableLiveData<Usuario>();
        usuario.setValue(new Usuario());
    }
    public void registerUser() {
        Usuario.RegistrarUsuario(usuario.getValue(), password.getValue(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {

            }

            @Override
            public void onSuccessResponse(JSONObject result) {

            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
