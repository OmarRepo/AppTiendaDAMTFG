package com.example.buyaskill.homefragments.pedidos;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyaskill.R;
import com.example.buyaskill.databinding.CarritoFragmentBinding;
import com.example.buyaskill.databinding.PedidosFragmentBinding;
import com.example.buyaskill.models.DataRepository;

import org.jetbrains.annotations.NotNull;

public class PedidosFragment extends Fragment {

    private PedidosViewModel vm;
    private PedidosFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = PedidosFragmentBinding.inflate(inflater,container,false);
        binding.setLifecycleOwner(this);
        vm = new ViewModelProvider(this).get(PedidosViewModel.class);
        binding.setViewModel(vm);
        binding.executePendingBindings();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv=getView().findViewById(R.id.listaPedidos);
        rv.setAdapter(new PedidoAdapter());
        PedidoAdapter adapter=(PedidoAdapter) rv.getAdapter();
        adapter.setClickListener((v, position) ->{
            DataRepository.setPedidoElegido(adapter.getItem(position));
            NavController navController = Navigation.findNavController((Activity)rv.getContext(), R.id.nav_host_fragment);
            navController.navigate(R.id.nav_pedido_details);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        vm.actualizarPedidos();
    }
}