package com.example.buyaskill.homefragments.pedido_details;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.buyaskill.helpers.App;
import com.example.buyaskill.helpers.Callbacks.VolleyJSONArrayCallback;
import com.example.buyaskill.models.DataRepository;
import com.example.buyaskill.models.Paquete;
import com.example.buyaskill.models.Pedido;
import com.example.buyaskill.models.Producto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PedidoDetailViewModel extends ViewModel {
    private static MutableLiveData<ArrayList<Paquete>> listaPaquete;
    private static MutableLiveData<Pedido> pedidoElegido;

    public PedidoDetailViewModel() {
        pedidoElegido=DataRepository.getPedidoElegido();
        listaPaquete=new MutableLiveData<>();
    }

    public static MutableLiveData<ArrayList<Paquete>> getPaquetes() {
        return listaPaquete;
    }
    public MutableLiveData<Pedido> getPedido() {
        return pedidoElegido;
    }

    public void actualizarPaquetesPedido() {
        pedidoElegido=DataRepository.getPedidoElegido();
        pedidoElegido.getValue().getPaquetesPedido(new VolleyJSONArrayCallback() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                Gson gson = new Gson();
                listaPaquete.setValue(gson.fromJson(result.toString(),new TypeToken<ArrayList<Paquete>>(){}.getType()));
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(App.getContext(), "Error al cargar la lista", Toast.LENGTH_SHORT).show();
            }
        });
    }
}