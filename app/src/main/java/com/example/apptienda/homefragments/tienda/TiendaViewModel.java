package com.example.apptienda.homefragments.tienda;

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
import com.example.apptienda.models.Paquete;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class TiendaViewModel extends ViewModel {

    static {
        listaPaquetes = new MutableLiveData<>();
    }
    private static MutableLiveData<ArrayList<Paquete>> listaPaquetes;


    public TiendaViewModel() {

    }

    public LiveData<ArrayList<Paquete>> getPaquetes() {
        return listaPaquetes;
    }


    @BindingAdapter("listData")
    public static void actualizarPaquetes(RecyclerView recyclerView,ArrayList<Paquete> paquetes) {
        try {
            Paquete.obtenerPaquetes(new VolleyJSONArrayCallback() {
                @Override
                public void onSuccessResponse(JSONArray result) {
                    Gson gson = new Gson();
                    listaPaquetes.setValue(gson.fromJson(result.toString(),new TypeToken<ArrayList<Paquete>>(){}.getType()));
                    recyclerView.setAdapter(new PaqueteAdapter(gson.fromJson(result.toString(),new TypeToken<ArrayList<Paquete>>(){}.getType())));
                    PaqueteAdapter adapter=(PaqueteAdapter) recyclerView.getAdapter();
                    adapter.setClickListener((view, position) ->{
                        Toast.makeText(App.getContext(), "Clickeado pos "+position, Toast.LENGTH_SHORT).show();
                    });
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