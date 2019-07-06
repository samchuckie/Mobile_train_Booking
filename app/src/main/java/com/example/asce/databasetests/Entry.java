package com.example.asce.databasetests;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Entry {
    private  String from;
    private String name;
    private int id;
    private int price;
    private int phone;

    public Entry()
    {

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public Entry(String name , int id, int price , int phone)
    {
        this.name =name;
        this.id =id ;
        this.price = price;
        this.phone =phone;
    }

    @Exclude
    public Map<String ,Object> mapper ()
    {
        HashMap <String ,Object> tomaps = new HashMap<>();
        tomaps.put("name" , name);
        tomaps.put("id" , id);
        tomaps.put("price", price);
        tomaps.put("phonenumber", phone);
        return tomaps;
    }
}
