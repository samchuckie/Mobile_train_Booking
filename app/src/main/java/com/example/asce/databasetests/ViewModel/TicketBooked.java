package com.example.asce.databasetests.ViewModel;

public class TicketBooked {

    public TicketBooked(){

    }
    private int id, phonenumber, price;
   private String name,trainentry_id;

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

    public void setTrainentry_id(String trainentry_id) {
        this.trainentry_id = trainentry_id;
    }

    public String getTrainentry_id() {
        return trainentry_id;
    }

    public TicketBooked(int id, String name, int phonenumber, int price){
    this.id=id;
    this.phonenumber=phonenumber;
    this.price=price;
    this.name=name;
    }


}
