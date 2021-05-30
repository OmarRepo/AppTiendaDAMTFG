package com.example.apptienda.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apptienda.models.DataRepository;
import com.example.apptienda.models.Usuario;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuario;
    public HomeViewModel() {
        super();
        usuario=new MutableLiveData<>( DataRepository.getUsuarioLogeado());
    }
    public LiveData<Usuario> getUsuario() {
        return usuario;
    }
}
