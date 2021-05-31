package com.example.apptienda.models;


import androidx.lifecycle.MutableLiveData;


public class DataRepository {
    private static MutableLiveData<Usuario> usuarioLogeado;
    private static MutableLiveData<Paquete> paqueteElegido;
    static {
        usuarioLogeado=new MutableLiveData<>();
        paqueteElegido=new MutableLiveData<>();
    }
    private DataRepository() {
        throw new IllegalStateException("Utility class");
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

    public static MutableLiveData<Paquete> getPaqueteElegido() {
        return paqueteElegido;
    }
    public static void setPaqueteElegido(Paquete paqueteElegido) {
        DataRepository.paqueteElegido.setValue(paqueteElegido);
    }
    public static void postPaqueteElegido(Paquete paqueteElegido) {
        DataRepository.paqueteElegido.postValue(paqueteElegido);
    }
}
