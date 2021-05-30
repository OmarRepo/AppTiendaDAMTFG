package com.example.apptienda.homefragments.tienda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptienda.R;
import com.example.apptienda.databinding.TiendaFragmentBinding;
import com.example.apptienda.helpers.App;

public class TiendaFragment extends Fragment {
    private TiendaViewModel vm;
    private TiendaFragmentBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = TiendaFragmentBinding.inflate(inflater,container,false);
        vm = new ViewModelProvider(getActivity()).get(TiendaViewModel.class);
        binding.setViewModel(vm);
        binding.executePendingBindings();
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}