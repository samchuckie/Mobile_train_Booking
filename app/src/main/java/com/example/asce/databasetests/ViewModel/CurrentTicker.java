package com.example.asce.databasetests.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.view.View;

public class CurrentTicker extends ViewModel {
    private int id, phonenumber, price;
    private String name,trainentry_id;String ticketid ;

    public String getTrainentry_id() {
        return trainentry_id;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getTicketid() {
        return ticketid;
    }

    public void setTrainentry_id(String trainentry_id) {
        this.trainentry_id = trainentry_id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

}
