package com.example.buyaskill.homefragments.pedidos;

import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.buyaskill.helpers.App;
import com.example.buyaskill.helpers.Callbacks.VolleyJSONArrayCallback;
import com.example.buyaskill.models.Pedido;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PedidosViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Pedido>> listaPedidos;
    private MutableLiveData<Integer> totalPedidos;
    public PedidosViewModel() {
        listaPedidos = new MutableLiveData<>();
        totalPedidos = new MutableLiveData<>();
    }

    public MutableLiveData<Integer> getTotalPedidos() {
        return totalPedidos;
    }
    public LiveData<ArrayList<Pedido>> getPedidos() {
        return listaPedidos;
    }

    public void actualizarPedidos() {
        try {
            Pedido.obtenerPedidos(new VolleyJSONArrayCallback() {
                @Override
                public void onSuccessResponse(JSONArray result) {
                    Gson gson = new Gson();
                    listaPedidos.setValue(gson.fromJson(result.toString(),new TypeToken<ArrayList<Pedido>>(){}.getType()));
                    totalPedidos.setValue(listaPedidos.getValue().size());
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
    @BindingAdapter("listPedidos")
    public static void setListaPedidos(RecyclerView recyclerView, ArrayList<Pedido> pedidos) {
        PedidoAdapter adapter = (PedidoAdapter) recyclerView.getAdapter();
        if(adapter!=null && pedidos!=null) {
            adapter.setData(pedidos);
            adapter.notifyDataSetChanged();
        }
    }


}