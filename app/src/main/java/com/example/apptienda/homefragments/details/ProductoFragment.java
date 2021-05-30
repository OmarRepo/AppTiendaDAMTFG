package com.example.apptienda.homefragments.details;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apptienda.R;
import com.example.apptienda.databinding.ProductoFragmentBinding;
import com.example.apptienda.databinding.TiendaFragmentBinding;
import com.example.apptienda.homefragments.tienda.TiendaViewModel;
import com.example.apptienda.models.DataRepository;
import com.example.apptienda.viewmodels.HomeViewModel;

public class ProductoFragment extends Fragment {

    private ProductoViewModel vm;
    private ProductoFragmentBinding binding;

    public static ProductoFragment newInstance() {
        return new ProductoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = ProductoFragmentBinding.inflate(inflater,container,false);
        vm = new ViewModelProvider(getActivity()).get(ProductoViewModel.class);
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
        vm.getPaquete().setValue(DataRepository.getPaqueteElegido());
    }
}