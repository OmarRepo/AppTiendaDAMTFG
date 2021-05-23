package com.example.apptienda.models;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptienda.App;
import com.example.apptienda.MyRecyclerViewAdapter;
import com.example.apptienda.R;
import com.example.apptienda.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    private static MutableLiveData<List<Paquete>> Paquetes;
    public static void Refrescar(){
        Thread hilo= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Paquetes.postValue(Paquete.obtenerPaquetes());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        hilo.start();

    }
    public static  List<Paquete> getPaquetesRefrescados(){
        return  Paquetes.getValue();
    }
}
