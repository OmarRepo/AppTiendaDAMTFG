package com.example.apptienda.homefragments.carrito;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.apptienda.models.DataRepository;
import com.example.apptienda.models.Paquete;
import com.example.apptienda.models.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;

public class CarritoViewModel extends ViewModel {
    static {
        listaPaquetes = new MutableLiveData<>();
    }
    private static MutableLiveData<ArrayList<Paquete>> listaPaquetes;

    public CarritoViewModel() {

    }
    public LiveData<ArrayList<Paquete>> getPaquetes() {
        return listaPaquetes;
    }

    @BindingAdapter("cartData")
    public static void actualizarPaquetes(RecyclerView recyclerView, ArrayList<Paquete> paquetes) {
        try {
            Paquete.obtenerPaquetes(new VolleyJSONArrayCallback() {
                @Override
                public void onSuccessResponse(JSONArray result) {
                    Gson gson = new Gson();
                    ArrayList<Paquete> ArrayPaquete=(gson.fromJson(result.toString(),new TypeToken<ArrayList<Paquete>>(){}.getType()));
                    Usuario usuario = DataRepository.getUsuarioLogeado();
                    SharedPreferences cart= App.getContext().getSharedPreferences(usuario.getId()+usuario.getNombre(), Context.MODE_PRIVATE);
                    HashSet<String> cartSet=new HashSet<>(cart.getStringSet("cart",new HashSet<String>()));
                    /*for (int x=0;x<= ArrayPaquete.size();x++){
                        if(!cartSet.contains(ArrayPaquete.get(x).getId())){
                            ArrayPaquete.remove(x);
                        }
                    }*/
                    listaPaquetes.setValue(ArrayPaquete);
                    PaqueteAdapter adapter=(PaqueteAdapter) recyclerView.getAdapter();
                    adapter.setData(ArrayPaquete);
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(App.getContext(), "Error al cargar la lista", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }}