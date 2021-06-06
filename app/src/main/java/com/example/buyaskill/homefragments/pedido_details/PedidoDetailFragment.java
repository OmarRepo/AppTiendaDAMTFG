package com.example.buyaskill.homefragments.pedido_details;

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

import com.example.buyaskill.R;
import com.example.buyaskill.databinding.PedidoDetailFragmentBinding;
import com.example.buyaskill.databinding.ProductoFragmentBinding;
import com.example.buyaskill.homefragments.paquete_details.ProductoViewModel;
import com.example.buyaskill.homefragments.tienda.PaqueteAdapter;
import com.example.buyaskill.models.DataRepository;

import org.jetbrains.annotations.NotNull;

public class PedidoDetailFragment extends Fragment {

    private PedidoDetailViewModel vm;
    private PedidoDetailFragmentBinding binding;

    public static PedidoDetailFragment newInstance() {
        return new PedidoDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PedidoDetailFragmentBinding.inflate(inflater,container,false);
        binding.setLifecycleOwner(this);
        vm = new ViewModelProvider(getActivity()).get(PedidoDetailViewModel.class);
        binding.setViewModel(vm);
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv=getView().findViewById(R.id.listaPaquetes);
        rv.setAdapter(new PaqueteAdapter());
        PaqueteAdapter adapter=(PaqueteAdapter) rv.getAdapter();
        adapter.setClickListener((v, position) ->{
            DataRepository.setPaqueteElegido(adapter.getItem(position));
            NavController navController = Navigation.findNavController((Activity)rv.getContext(), R.id.nav_host_fragment);
            navController.navigate(R.id.nav_details);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        vm.actualizarPaquetesPedido();
    }

}