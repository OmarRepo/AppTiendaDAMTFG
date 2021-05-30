package com.example.apptienda.homefragments.details;

import android.app.Activity;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.apptienda.R;
import com.example.apptienda.helpers.App;
import com.example.apptienda.helpers.Callbacks.VolleyJSONArrayCallback;
import com.example.apptienda.homefragments.tienda.PaqueteAdapter;
import com.example.apptienda.models.DataRepository;
import com.example.apptienda.models.Paquete;
import com.example.apptienda.models.Producto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ProductoViewModel extends ViewModel {
    static {
        listaProductos = new MutableLiveData<>();
    }
    private static MutableLiveData<ArrayList<Producto>> listaProductos;
    private  MutableLiveData<Paquete> paqueteElegido;


    public ProductoViewModel() {
    paqueteElegido=new MutableLiveData<>(DataRepository.getPaqueteElegido());
    }

    public LiveData<ArrayList<Producto>> getProductos() {
        return listaProductos;
    }
    public LiveData<Paquete> getPaquete() {
        return paqueteElegido;
    }


    @BindingAdapter("listProducto")
    public static void actualizarProductos(RecyclerView recyclerView, ArrayList<Producto> productos) {
        try {
            DataRepository.getPaqueteElegido().obtenerProductos(new VolleyJSONArrayCallback(){
                @Override
                public void onSuccessResponse(JSONArray result) {
                    Gson gson = new Gson();
                    listaProductos.setValue(gson.fromJson(result.toString(),new TypeToken<ArrayList<Producto>>(){}.getType()));
                    recyclerView.setAdapter(new PaqueteAdapter(gson.fromJson(result.toString(),new TypeToken<ArrayList<Producto>>(){}.getType())));
                }
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(App.getContext(), "Error al cargar la lista", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
