package com.example.asce.databasetests.Fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asce.databasetests.R;
import com.example.asce.databasetests.ViewModel.TicketBooked;

import java.util.ArrayList;
import java.util.List;

class Ticketsadapter  extends RecyclerView.Adapter<Ticketsadapter.Viewholder>{
    List<TicketBooked> tb = new ArrayList<>();
    @NonNull
    @Override
    public Ticketsadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tt = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_tv ,parent,false);
        Viewholder vv = new Viewholder(tt);

        return vv;
    }

    @Override
    public void onBindViewHolder(@NonNull Ticketsadapter.Viewholder holder, int position) {
        TicketBooked t =tb.get(position);
        String id=String.valueOf(t.getId());
        String telephone=String.valueOf(t.getPhonenumber());
        String price=String.valueOf(t.getPrice());


        holder.t_n.setText(t.getName());
        holder.t_phone.setText(telephone);
        holder.t_i.setText(id);
        holder.t_pr.setText(price);

    }
    @Override
    public int getItemCount() {
//        if(tb!=null) {
//            Log.e("sam", "" + tb.size());
//            return tb.size();
//        }
        Log.e("sam", "" + tb.size());

        return tb.size();
    }

    public void addticket(TicketBooked booked) {
        tb.add(booked);
        notifyDataSetChanged();

    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView t_n,t_phone,t_i,t_pr;
        public Viewholder(View itemView) {
            super(itemView);
            t_n=itemView.findViewById(R.id.tk_name);
            t_phone=itemView.findViewById(R.id.tk_phoneno);
            t_i=itemView.findViewById(R.id.tk_id);
            t_pr=itemView.findViewById(R.id.tk_price);
        }
    }
}
