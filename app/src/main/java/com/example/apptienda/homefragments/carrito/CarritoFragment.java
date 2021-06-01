package com.example.apptienda.homefragments.carrito;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apptienda.R;
import com.example.apptienda.databinding.CarritoFragmentBinding;
import com.example.apptienda.databinding.TiendaFragmentBinding;
import com.example.apptienda.homefragments.tienda.PaqueteAdapter;
import com.example.apptienda.homefragments.tienda.TiendaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

public class CarritoFragment extends Fragment {
    private CarritoViewModel vm;
    private CarritoFragmentBinding binding;
    public static CarritoFragment newInstance() {
        return new CarritoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CarritoFragmentBinding.inflate(inflater,container,false);
        vm = new ViewModelProvider(getActivity()).get(CarritoViewModel.class);
        binding.setViewModel(vm);
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv=getView().findViewById(R.id.carritoPaquetes);
        rv.setAdapter(new CarritoAdapter(vm));
        CarritoAdapter adapter=(CarritoAdapter) rv.getAdapter();
        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.floatingCrearPedido);
        fab.setOnClickListener(view1 ->{

        });
    }

}