package com.example.apptienda.homefragments.carrito;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
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

    private MutableLiveData<ArrayList<Paquete>> listaPaquetes;
    private MutableLiveData<Double> total;
    public CarritoViewModel() {
        listaPaquetes = new MutableLiveData<>();
        total = new MutableLiveData<>();
    }
    public LiveData<ArrayList<Paquete>> getPaquetes() {
        return listaPaquetes;
    }
    public LiveData<Double> getTotal() {
        return total;
    }
    @BindingAdapter("cartData")
    public static void setCartData(RecyclerView recyclerView, ArrayList<Paquete> paquetes) {
        CarritoAdapter adapter=(CarritoAdapter) recyclerView.getAdapter();
        if(paquetes!=null&&adapter!=null) {
            adapter.setData(paquetes);
            adapter.notifyDataSetChanged();
        }
    }
    public void actualizarPaquetes() {
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
                    Double precioTotal=0.0;
                    for (int x=0;x< arrayPaquete.size();x++){
                        if(cartSet.contains(arrayPaquete.get(x).getId())){
                            arrayFiltrado.add(arrayPaquete.get(x));
                            String precioString=arrayPaquete.get(x).getPrecio();
                            precioTotal+=Double.parseDouble(precioString!=null?precioString:"0.0");
                        }
                    }
                    total.setValue(precioTotal);
                    listaPaquetes.setValue(arrayFiltrado);
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
        cart.edit().putStringSet("cart",cartSet).commit();
        actualizarPaquetes();
    }
}
