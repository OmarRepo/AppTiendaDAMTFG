package com.example.buyaskill.homefragments.paquete_details;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.buyaskill.R;
import com.example.buyaskill.helpers.App;
import com.example.buyaskill.helpers.Callbacks.VolleyJSONArrayCallback;
import com.example.buyaskill.models.DataRepository;
import com.example.buyaskill.models.Paquete;
import com.example.buyaskill.models.Producto;
import com.example.buyaskill.models.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;

public class ProductoViewModel extends ViewModel {
    static {
        listaProductos = new MutableLiveData<>();
    }
    private static MutableLiveData<ArrayList<Producto>> listaProductos;
    private static MutableLiveData<Paquete> paqueteElegido;

    public ProductoViewModel() {
        paqueteElegido=DataRepository.getPaqueteElegido();
    }

    public static MutableLiveData<ArrayList<Producto>> getProductos() {
        return listaProductos;
    }
    public MutableLiveData<Paquete> getPaquete() {
        return paqueteElegido;
    }

    @BindingAdapter("listProducto")
    public static void setProductos(RecyclerView recyclerView, ArrayList<Producto> productos) {
        if(productos!=null) {
            recyclerView.setAdapter(new ProductoAdapter(productos));
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    public void actualizarProductos() {
        try {
            DataRepository.getPaqueteElegido().getValue().obtenerProductos(new VolleyJSONArrayCallback(){
                @Override
                public void onSuccessResponse(JSONArray result) {
                    Gson gson = new Gson();
                    listaProductos.setValue(gson.fromJson(result.toString(),new TypeToken<ArrayList<Producto>>(){}.getType()));
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

    @BindingAdapter({"imagenPaquete"})
    public static void loadImage(ImageView imagenView, String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_launcher_background).into(imagenView);
    }
    public boolean addToCart() {
        Paquete paquete = paqueteElegido.getValue();
        Usuario usuario =DataRepository.getUsuarioLogeado();
        if(paquete==null) {
            return false;
        }
        SharedPreferences cart= App.getContext().getSharedPreferences(usuario.getId()+usuario.getNombre(), Context.MODE_PRIVATE);
        HashSet<String> cartSet=new HashSet<>(cart.getStringSet("cart",new HashSet<String>()));
        if(cartSet.add(paquete.getId())){
            cart.edit().putStringSet("cart",cartSet).apply();
            return true;
        }
        return false;
    }
}
