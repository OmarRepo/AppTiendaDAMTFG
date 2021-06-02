package com.example.apptienda.homefragments.tienda;

import android.app.Activity;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
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
import com.example.apptienda.models.DataRepository;
import com.example.apptienda.models.Paquete;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Observable;

public class TiendaViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Paquete>> listaPaquetes;

    public TiendaViewModel() {
        listaPaquetes = new MutableLiveData<>();
    }
    public LiveData<ArrayList<Paquete>> getPaquetes() {
        return listaPaquetes;
    }


    public void actualizarPaquetes() {
        try {
            Paquete.obtenerPaquetes(new VolleyJSONArrayCallback() {
                @Override
                public void onSuccessResponse(JSONArray result) {
                    Gson gson = new Gson();
                    listaPaquetes.setValue(gson.fromJson(result.toString(),new TypeToken<ArrayList<Paquete>>(){}.getType()));
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
    @BindingAdapter("listData")
    public static void setListaPaquetes(RecyclerView recyclerView,ArrayList<Paquete> paquetes) {
        PaqueteAdapter adapter = (PaqueteAdapter) recyclerView.getAdapter();
        if(adapter!=null && paquetes!=null) {
            adapter.setData(paquetes);
            adapter.notifyDataSetChanged();
        }
    }
}
