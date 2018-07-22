package com.example.asce.databasetests.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asce.databasetests.R;

import java.util.List;

public class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.ViewHolder> {
    List<String> places ;
    public MapsAdapter (List p){
        places=p;
    }
    @NonNull
    @Override
    public MapsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mt = LayoutInflater.from(parent.getContext()).inflate(R.layout.maps_tv ,parent,false);
        ViewHolder vv = new ViewHolder(mt);

        return vv;
    }

    @Override
    public void onBindViewHolder(@NonNull MapsAdapter.ViewHolder holder, int position) {
        String placename= places.get(position);
        holder.map_tv.setText(placename);

    }

    @Override
    public int getItemCount() {
        return places.size();
    }


    public class
    ViewHolder extends RecyclerView.ViewHolder {
        TextView map_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            map_tv = itemView.findViewById(R.id.map_tv);
        }
    }
}
