package com.example.buyaskill;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buyaskill.databinding.RegisterFormFragment2FragmentBinding;
import com.example.buyaskill.viewmodels.RegisterViewModel;

public class RegisterFormFragment_2 extends Fragment {

    private RegisterViewModel vm;

    public static RegisterFormFragment_2 newInstance() {
        return new RegisterFormFragment_2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RegisterFormFragment2FragmentBinding binding = RegisterFormFragment2FragmentBinding.inflate(inflater,container,false);
        vm = new ViewModelProvider(getActivity()).get(RegisterViewModel.class);
        binding.setViewModel(vm);
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();

        return binding.getRoot();
    }
    //Metodo que llama a la validacion de los distintos campos del formulario
    public String validateFields() {
        return vm.validateFields2();
    }
}