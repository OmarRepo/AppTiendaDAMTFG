package com.example.apptienda.homefragments.pedidos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.VolleyError;
import com.example.apptienda.R;
import com.example.apptienda.databinding.CarritoFragmentBinding;
import com.example.apptienda.databinding.PedidosFragmentBinding;
import com.example.apptienda.helpers.App;
import com.example.apptienda.helpers.Callbacks.VolleyJSONArrayCallback;
import com.example.apptienda.homefragments.carrito.CarritoViewModel;
import com.example.apptienda.models.Paquete;
import com.example.apptienda.models.Pedido;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class PedidosFragment extends Fragment {

    private PedidosViewModel vm;
    private PedidosFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = PedidosFragmentBinding.inflate(inflater,container,false);
        binding.setLifecycleOwner(this);
        vm = new ViewModelProvider(this).get(PedidosViewModel.class);
        binding.setLista(vm);
        binding.executePendingBindings();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}