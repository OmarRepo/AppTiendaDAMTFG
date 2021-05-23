package com.example.apptienda.models;


import androidx.lifecycle.MutableLiveData;


public class DataRepository {
    static {
        usuarioLogeado=new MutableLiveData<>();
    }
    private DataRepository() {
        throw new IllegalStateException("Utility class");
    }
    private static MutableLiveData<Usuario> usuarioLogeado;

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
