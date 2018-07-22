package com.example.asce.databasetests.ViewModel;

public class TicketBooked {

    public TicketBooked(){

    }
    int id, phonenumber, price;
    String name;

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPhonenumber() {
        return phonenumber;
    }


    public TicketBooked(int id, String name, int phonenumber, int price){
    this.id=id;
    this.phonenumber=phonenumber;
    this.price=price;
    this.name=name;
    }


}
