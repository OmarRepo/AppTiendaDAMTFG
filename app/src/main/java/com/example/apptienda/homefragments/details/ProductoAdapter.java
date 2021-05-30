package com.example.apptienda.homefragments.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptienda.databinding.PaqueteCardLayoutBinding;
import com.example.apptienda.databinding.ProductoCardLayoutBinding;
import com.example.apptienda.models.Paquete;
import com.example.apptienda.models.Producto;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoHolder> {


    private ArrayList<Producto> producto;

    private ItemClickListener itemClickListener;

    public ProductoAdapter() {
        this.producto = new ArrayList<>();
    }
    public ProductoAdapter(ArrayList<Producto> producto) {
        this.producto = producto;
    }
    public void setData(ArrayList<Paquete> paquetes) {
        this.producto=producto;
    }
    @Override
    public ProductoAdapter.ProductoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductoCardLayoutBinding binding = ProductoCardLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ProductoHolder(parent,binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ProductoHolder holder, int position) {
        holder.bind(producto.get(position));
    }

    @Override
    public int getItemCount() {
            return producto.size();
        }

    public class ProductoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ProductoCardLayoutBinding rowBinding;

        public ProductoHolder(View v,ProductoCardLayoutBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.rowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            rowBinding.setViewModel((Producto) obj);
            rowBinding.executePendingBindings();
            rowBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
    public Producto getItem(int id) {
        return producto.get(id);
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

