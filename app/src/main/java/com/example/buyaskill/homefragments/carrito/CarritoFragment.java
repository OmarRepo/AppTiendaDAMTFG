package com.example.buyaskill.homefragments.carrito;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buyaskill.R;
import com.example.buyaskill.databinding.CarritoFragmentBinding;
import com.example.buyaskill.databinding.TiendaFragmentBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;

import cn.pedant.SweetAlert.SweetAlertDialog;

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
        binding.setLifecycleOwner(this);
        vm = new ViewModelProvider(this).get(CarritoViewModel.class);
        binding.setViewModel(vm);
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv=getView().findViewById(R.id.carritoPaquetes);
        rv.setAdapter(new CarritoAdapter(vm));
        FloatingActionButton fab = getView().findViewById(R.id.floatingCrearPedido);
        fab.setOnClickListener(view1 -> {
            if (vm.getPaquetes().getValue().size() > 0) {
                SweetAlertDialog dialog=new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
                        dialog.setTitleText("Confirmar pedido")
                        .setContentText("Estas seguro de que quieres comprar?")
                        .setConfirmText("Dale!")
                        .setConfirmClickListener(sweetAlertDialog -> {
                            try {
                                vm.confirmarPedido();
                                vm.emptyCart();
                                dialog.dismissWithAnimation();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        })
                        .setCancelButton("No ", sDialog -> sDialog.dismissWithAnimation())
                        .show();
            } else {
                SweetAlertDialog dialog=new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Carrito vacio")
                        .setContentText("El carrito esta vacio D.D ?")
                        .setConfirmText("Cierto")
                        .setConfirmClickListener(SweetAlertDialog::dismissWithAnimation)
                        .show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        vm.actualizarPaquetes();
    }
}