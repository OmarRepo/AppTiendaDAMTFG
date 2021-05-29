package com.example.apptienda.homefragments.tienda;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptienda.databinding.PaqueteCardLayoutBinding;
import com.example.apptienda.models.Paquete;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
public class PaqueteAdapter extends RecyclerView.Adapter<PaqueteAdapter.PaqueteHolder> {


    private ArrayList<Paquete> paquetes;

    public PaqueteAdapter() {
        this.paquetes = new ArrayList<>();
    }
    public PaqueteAdapter(ArrayList<Paquete> paquetes) {
        this.paquetes = paquetes;
    }
    public void setData(ArrayList<Paquete> paquetes) {
        this.paquetes=paquetes;
    }
    @Override
    public PaqueteAdapter.PaqueteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PaqueteCardLayoutBinding binding = PaqueteCardLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PaqueteHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PaqueteAdapter.PaqueteHolder holder, int position) {
        holder.bind(paquetes.get(position));
    }

    @Override
    public int getItemCount() {
            return paquetes.size();
        }

    public class PaqueteHolder extends RecyclerView.ViewHolder {
        public PaqueteCardLayoutBinding rowBinding;

        public PaqueteHolder(PaqueteCardLayoutBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.rowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            rowBinding.setViewModel((Paquete) obj);
            rowBinding.executePendingBindings();
        }
    }
}

