package com.example.buyaskill.homefragments.perfil;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.buyaskill.models.Usuario;

public class PerfilViewModel extends ViewModel {

    private MutableLiveData<Usuario> usuario;

    public PerfilViewModel() {

    }

    public MutableLiveData<Usuario> getUsuario() {
        return usuario;
    }
}