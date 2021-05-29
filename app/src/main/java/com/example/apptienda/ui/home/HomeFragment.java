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
import com.example.apptienda.databinding.FragmentHomeBinding;
import com.example.apptienda.helpers.Callbacks.VolleyJSONArrayCallback;
import com.example.apptienda.models.Paquete;
import com.example.apptienda.models.Usuario;
import com.example.apptienda.viewmodels.RegisterViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeFragment extends Fragment {
    public  PaqueteAdapter adapter;
    private HomeViewModel vm;
    public ArrayList<Paquete> ListaPaquetes;
    private FragmentHomeBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        vm = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        binding.setViewModel(vm);
        binding.executePendingBindings();
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}