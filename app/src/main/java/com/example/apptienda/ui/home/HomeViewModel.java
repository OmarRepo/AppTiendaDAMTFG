package com.example.apptienda.ui.home;

import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.apptienda.App;
import com.example.apptienda.R;
import com.example.apptienda.databinding.FragmentHomeBinding;
import com.example.apptienda.helpers.Callbacks.VolleyJSONArrayCallback;
import com.example.apptienda.models.Paquete;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    static {
        listaPaquetes = new MutableLiveData<>();
    }
    private static MutableLiveData<ArrayList<Paquete>> listaPaquetes;


    public HomeViewModel() {

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