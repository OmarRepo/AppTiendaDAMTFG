package com.example.buyaskill.homefragments.paquete_details;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.buyaskill.R;
import com.example.buyaskill.databinding.ProductoFragmentBinding;

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
        binding.setLifecycleOwner(this);
        vm = new ViewModelProvider(getActivity()).get(ProductoViewModel.class);
        binding.setViewModel(vm);
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button addPaquete =getView().findViewById(R.id.carrito);
        addPaquete.setOnClickListener(v ->{
            if(vm.addToCart()){
                Toast.makeText(getContext(), "Añadido con exito", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getContext(), "Paquete ya en el carrito", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        vm.actualizarProductos();
    }
}