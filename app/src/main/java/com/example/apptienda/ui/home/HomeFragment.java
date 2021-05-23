package com.example.apptienda.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.apptienda.App;
import com.example.apptienda.MyRecyclerViewAdapter;
import com.example.apptienda.R;
import com.example.apptienda.helpers.Callbacks.VolleyJSONArrayCallback;
import com.example.apptienda.models.Paquete;
import com.example.apptienda.models.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeFragment extends Fragment {
    public  MyRecyclerViewAdapter adapter;
    private HomeViewModel homeViewModel;
    public List<Paquete> ListaPaquetes;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            obtenerDatos();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    public void obtenerDatos() throws JSONException {
        Paquete.obtenerPaquetes(new VolleyJSONArrayCallback() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                Gson gson = new Gson();
                ListaPaquetes = gson.fromJson(result.toString(),new TypeToken<ArrayList<Paquete>>(){}.getType());
                RecyclerView rv = getView().findViewById(R.id.listaPaquetes);
                ArrayList<String> paquetes = new ArrayList<>();
                for(Paquete pack:ListaPaquetes){
                    paquetes.add(pack.toString());
                }
                rv.setLayoutManager(new LinearLayoutManager(App.getContext()));
                adapter = new MyRecyclerViewAdapter(App.getContext(), paquetes);
                adapter.setClickListener(new MyRecyclerViewAdapter.ItemClickListener(){
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(App.getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
                    }
                });
                rv.setAdapter(adapter);
            }
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

}