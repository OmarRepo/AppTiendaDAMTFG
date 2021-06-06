package com.example.apptienda.homefragments.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apptienda.models.Usuario;

public class PerfilViewModel extends ViewModel {

    private MutableLiveData<Usuario> usuario;

    public PerfilViewModel() {

    }

    public MutableLiveData<Usuario> getUsuario() {
        return usuario;
    }
}