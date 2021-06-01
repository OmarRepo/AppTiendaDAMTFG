package com.example.apptienda.homefragments.carrito;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apptienda.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CarritoFragment extends Fragment {

    private CarritoViewModel mViewModel;

    public static CarritoFragment newInstance() {
        return new CarritoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.carrito_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CarritoViewModel.class);

        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.floatingCrearPedido);
        fab.setOnClickListener(view1 ->{

        });
    }

}