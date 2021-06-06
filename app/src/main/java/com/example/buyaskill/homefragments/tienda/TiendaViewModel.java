package com.example.buyaskill.homefragments.tienda;

import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.buyaskill.helpers.App;
import com.example.buyaskill.helpers.Callbacks.VolleyJSONArrayCallback;
import com.example.buyaskill.models.Paquete;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

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
