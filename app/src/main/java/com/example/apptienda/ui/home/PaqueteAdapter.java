package com.example.apptienda.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.apptienda.App;
import com.example.apptienda.models.Paquete;
import com.example.apptienda.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
public class PaqueteAdapter extends RecyclerView.Adapter<PaqueteAdapter.MyViewHolder> {


        private ArrayList<Paquete> dataSet;

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textViewName;
            TextView textViewVersion;
            ImageView imageViewIcon;

            public MyViewHolder(View itemView) {
                super(itemView);
                this.textViewName = (TextView) itemView.findViewById(R.id.id_nombrePaquete);
                this.textViewVersion = (TextView) itemView.findViewById(R.id.id_textoPaquete);
                this.imageViewIcon = (ImageView) itemView.findViewById(R.id.id_imagenPaquete);
            }
        }

        public PaqueteAdapter(ArrayList<Paquete> data) {
            this.dataSet = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.paquete_card_layout, parent, false);

           // view.setOnClickListener(MainActivity.myOnClickListener);

            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

            TextView textViewName = holder.textViewName;
            TextView textViewVersion = holder.textViewVersion;
            ImageView imageView = holder.imageViewIcon;

            textViewName.setText(dataSet.get(listPosition).getNombre());
            textViewVersion.setText(dataSet.get(listPosition).getId());
            Picasso.get().load(dataSet.get(listPosition).getImg()).resize(150,150).centerCrop().into(imageView);

        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }
    }

