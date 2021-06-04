package com.example.apptienda.homefragments.pedidos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptienda.databinding.PedidoCardLayoutBinding;
import com.example.apptienda.databinding.ProductoCardLayoutBinding;
import com.example.apptienda.models.Paquete;
import com.example.apptienda.models.Pedido;
import com.example.apptienda.models.Producto;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoHolder> {


    private ArrayList<Pedido> pedido;

    private ItemClickListener itemClickListener;

    public PedidoAdapter() {
        this.pedido = new ArrayList<>();
    }
    public PedidoAdapter(ArrayList<Pedido> pedido) {
        this.pedido = pedido;
    }
    public void setData(ArrayList<Pedido> pedido) {
        this.pedido=pedido;
    }
    @Override
    public PedidoAdapter.PedidoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PedidoCardLayoutBinding binding = PedidoCardLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PedidoHolder(parent,binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PedidoAdapter.PedidoHolder holder, int position) {
        holder.bind(pedido.get(position));

    }


    @Override
    public int getItemCount() {
            return pedido.size();
    }

    public class PedidoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public PedidoCardLayoutBinding rowBinding;

        public PedidoHolder(View v,PedidoCardLayoutBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.rowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            rowBinding.setEntusiasmado((Pedido) obj);
            rowBinding.executePendingBindings();
            rowBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
    public Pedido getItem(int id) {
        return pedido.get(id);
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

