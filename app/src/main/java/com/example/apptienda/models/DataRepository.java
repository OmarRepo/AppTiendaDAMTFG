package com.example.apptienda.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DataRepository {
    private static MutableLiveData<Usuario> usuarioLogeado;
    static {
        usuarioLogeado=new MutableLiveData<>();
    }
    public static Usuario getUsuarioLogeado() {
        return usuarioLogeado.getValue();
    }
    public static void setUsuarioLogeado(Usuario usuarioLogeado) {
        DataRepository.usuarioLogeado.setValue(usuarioLogeado);
    }
    public static void postUsuarioLogeado(Usuario usuarioLogeado) {
        DataRepository.usuarioLogeado.postValue(usuarioLogeado);
    }
}
