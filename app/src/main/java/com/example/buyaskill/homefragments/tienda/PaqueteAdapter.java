package com.example.buyaskill.homefragments.tienda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyaskill.databinding.PaqueteCardLayoutBinding;
import com.example.buyaskill.models.Paquete;


import java.util.ArrayList;
import java.util.List;

public class PaqueteAdapter extends RecyclerView.Adapter<PaqueteAdapter.PaqueteHolder> implements Filterable {


    private ArrayList<Paquete> paquetes;
    private ArrayList<Paquete> paquetesFull;


    private ItemClickListener itemClickListener;

    public PaqueteAdapter() {
        this.paquetes = new ArrayList<>();
    }
    public PaqueteAdapter(ArrayList<Paquete> paquetes) {
        this.paquetes = paquetes;
        this.paquetesFull=new ArrayList<>(paquetes);
    }
    public void setData(ArrayList<Paquete> paquetes) {
        this.paquetes=paquetes;
        this.paquetesFull=new ArrayList<>(paquetes);
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

    @Override
    public Filter getFilter() {
        return filtroPaquetes;
    }
    private Filter filtroPaquetes = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Paquete> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length()==0) {
                filteredList.addAll(paquetesFull);
            }
            else{
                String patron = constraint.toString().toLowerCase().trim();
                for (Paquete paquete: paquetesFull) {
                    if(paquete.getNombre().toLowerCase().contains(patron)) {
                        filteredList.add(paquete);
                    }
                }

            }
            FilterResults results= new FilterResults();
            results.values=filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            paquetes.clear();
            if(results.values!=null) {
                paquetes.addAll((List) results.values);
            }
            notifyDataSetChanged();
        }
    };

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

