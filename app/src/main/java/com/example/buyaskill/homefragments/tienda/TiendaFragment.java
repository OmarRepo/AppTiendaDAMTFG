package com.example.buyaskill.homefragments.tienda;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyaskill.R;
import com.example.buyaskill.databinding.TiendaFragmentBinding;
import com.example.buyaskill.models.DataRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class TiendaFragment extends Fragment {
    private TiendaViewModel vm;
    private TiendaFragmentBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = TiendaFragmentBinding.inflate(inflater,container,false);
        binding.setLifecycleOwner(this);
        vm = new ViewModelProvider(getActivity()).get(TiendaViewModel.class);
        binding.setViewModel(vm);
        binding.executePendingBindings();
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv=getView().findViewById(R.id.listaPaquetes);
        rv.setAdapter(new PaqueteAdapter());
        PaqueteAdapter adapter=(PaqueteAdapter) rv.getAdapter();
        adapter.setClickListener((v, position) ->{
            DataRepository.setPaqueteElegido(adapter.getItem(position));
            NavController navController = Navigation.findNavController((Activity)rv.getContext(), R.id.nav_host_fragment);
            navController.navigate(R.id.nav_details);
        });
        SearchView buscador =getView().findViewById(R.id.busca_paquetes);
        buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RecyclerView rv=getView().findViewById(R.id.listaPaquetes);
                ((PaqueteAdapter)rv.getAdapter()).getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                RecyclerView rv=getView().findViewById(R.id.listaPaquetes);
                ((PaqueteAdapter)rv.getAdapter()).getFilter().filter(newText);
                return true;
            }
        });
        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.FAB_carrito);
        fab.setOnClickListener(view1 ->{
            NavController navController = Navigation.findNavController((Activity)rv.getContext(), R.id.nav_host_fragment);
            navController.navigate(R.id.nav_carrito);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        vm.actualizarPaquetes();
        RecyclerView rv=getView().findViewById(R.id.listaPaquetes);
        ((PaqueteAdapter)rv.getAdapter()).getFilter().filter("");
        SearchView buscador =getView().findViewById(R.id.busca_paquetes);
        if (buscador != null) {
            buscador.setQuery("", false);
            buscador.onActionViewCollapsed();
            buscador.onActionViewExpanded();
            buscador.clearFocus();
        }
    }
}