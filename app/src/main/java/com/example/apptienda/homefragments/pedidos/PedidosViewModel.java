package com.example.apptienda.homefragments.pedidos;

import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.apptienda.helpers.App;
import com.example.apptienda.helpers.Callbacks.VolleyJSONArrayCallback;
import com.example.apptienda.homefragments.tienda.PaqueteAdapter;
import com.example.apptienda.models.Paquete;
import com.example.apptienda.models.Pedido;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PedidosViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Pedido>> listaPedidos;

    public PedidosViewModel() {
        listaPedidos = new MutableLiveData<>();
    }
    public LiveData<ArrayList<Pedido>> getPedidos() {
        return listaPedidos;
    }

    public void actualizarPedido() {
        try {
            Pedido.obtenerPedidos(new VolleyJSONArrayCallback() {
                @Override
                public void onSuccessResponse(JSONArray result) {
                    Gson gson = new Gson();
                    listaPedidos.setValue(gson.fromJson(result.toString(),new TypeToken<ArrayList<Paquete>>(){}.getType()));
                }
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(App.getContext(), "Error al cargar la lista", Toast.LENGTH_SHORT).show();
                }
            });
        } catch ( ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @BindingAdapter("listPedido")
    public static void setListaPedidos(RecyclerView recyclerView, ArrayList<Pedido> pedidos) {
        PedidoAdapter adapter = (PedidoAdapter) recyclerView.getAdapter();
        if(adapter!=null && pedidos!=null) {
            adapter.setData(pedidos);
            adapter.notifyDataSetChanged();
        }
    }


}