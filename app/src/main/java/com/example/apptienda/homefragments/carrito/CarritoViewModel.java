package com.example.apptienda.homefragments.carrito;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.apptienda.helpers.App;
import com.example.apptienda.helpers.Callbacks.VolleyJSONArrayCallback;
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
                    ArrayList<Paquete> arrayPaquete=(gson.fromJson(result.toString(),new TypeToken<ArrayList<Paquete>>(){}.getType()));
                    ArrayList<Paquete> arrayFiltrado=new ArrayList<>();
                    Usuario usuario = DataRepository.getUsuarioLogeado();
                    SharedPreferences cart= App.getContext().getSharedPreferences(usuario.getId()+usuario.getNombre(), Context.MODE_PRIVATE);
                    HashSet<String> cartSet=new HashSet<>(cart.getStringSet("cart",new HashSet<String>()));
                    for (int x=0;x< arrayPaquete.size();x++){
                        if(cartSet.contains(arrayPaquete.get(x).getId())){
                           arrayFiltrado.add(arrayPaquete.get(x));
                        }
                    }
                    listaPaquetes.setValue(arrayFiltrado);
                    CarritoAdapter adapter=(CarritoAdapter) recyclerView.getAdapter();
                    adapter.setData(arrayFiltrado);
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
    }
    public void removeFromCart(Paquete paquete) {
        Usuario usuario = DataRepository.getUsuarioLogeado();
        SharedPreferences cart= App.getContext().getSharedPreferences(usuario.getId()+usuario.getNombre(), Context.MODE_PRIVATE);
        HashSet<String> cartSet=new HashSet<>(cart.getStringSet("cart",new HashSet<String>()));
        cartSet.remove(paquete.getId());
        if(cart.edit().putStringSet("cart",cartSet).commit()){
            Toast.makeText(App.getContext(), "Esto se ejecuta y el id del paquete es "+paquete.getId(), Toast.LENGTH_SHORT).show();
        }

    }
}
