package com.example.asce.databasetests.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asce.databasetests.R;

public class StationsAdapter extends RecyclerView.Adapter<StationsAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView station_iv;
        TextView station_location,station_direction;
        public ViewHolder(View itemView) {
            super(itemView);
            station_iv = itemView.findViewById(R.id.station_iv);
            station_location = itemView.findViewById(R.id.station_location);
            station_direction = itemView.findViewById(R.id.station_direction);
        }
    }
}
