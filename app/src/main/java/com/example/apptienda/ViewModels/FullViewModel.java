package com.example.apptienda.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.apptienda.Models.Usuario;

public class FullViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuario;
    public MutableLiveData<Usuario> getCurrentUser() {
        return usuario;
    }
}
