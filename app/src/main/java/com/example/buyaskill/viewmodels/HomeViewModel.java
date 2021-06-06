package com.example.buyaskill.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.buyaskill.models.DataRepository;
import com.example.buyaskill.models.Usuario;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuario;
    public HomeViewModel() {
        super();
        usuario=DataRepository.getMutableUsuario();
    }
    public LiveData<Usuario> getUsuario() {
        return usuario;
    }
}
