package com.example.apptienda.homefragments.tienda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptienda.databinding.PaqueteCardLayoutBinding;
import com.example.apptienda.helpers.App;
import com.example.apptienda.models.Paquete;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
public class PaqueteAdapter extends RecyclerView.Adapter<PaqueteAdapter.PaqueteHolder> {


    private ArrayList<Paquete> paquetes;

    private ItemClickListener itemClickListener;

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
        return new PaqueteHolder(parent,binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PaqueteAdapter.PaqueteHolder holder, int position) {
        holder.bind(paquetes.get(position));
    }

    @Override
    public int getItemCount() {
            return paquetes.size();
        }

    public class PaqueteHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public PaqueteCardLayoutBinding rowBinding;

        public PaqueteHolder(View v,PaqueteCardLayoutBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.rowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            rowBinding.setViewModel((Paquete) obj);
            rowBinding.executePendingBindings();
            rowBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
    public Paquete getItem(int id) {
        return paquetes.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

