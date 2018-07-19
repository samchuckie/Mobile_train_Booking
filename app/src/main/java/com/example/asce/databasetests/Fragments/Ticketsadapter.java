package com.example.asce.databasetests.Fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asce.databasetests.R;

class Ticketsadapter  extends RecyclerView.Adapter<Ticketsadapter.Viewholder>{
    @NonNull
    @Override
    public Ticketsadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tt = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_tv ,parent,false);
        Viewholder vv = new Viewholder(tt);

        return vv;
    }

    @Override
    public void onBindViewHolder(@NonNull Ticketsadapter.Viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
        //
        //
        // return size
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView t_n,t_p,t_f,t_t;
        public Viewholder(View itemView) {
            super(itemView);
            t_n=itemView.findViewById(R.id.tk_name);
            t_p=itemView.findViewById(R.id.tk_telephone);
            t_f=itemView.findViewById(R.id.tk_from);
            t_t=itemView.findViewById(R.id.tk_to);
        }
    }
}
