package com.example.asce.databasetests;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class entry {
    String name;
    String id;
    int price;
    String phone;

    public  entry()
    {

    }

    public entry(String name ,String id,int price , String phone)
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
        tomaps.put("Name" , name);
        tomaps.put("ID" , id);
        tomaps.put("Price", price);
        tomaps.put("Phone Number", phone);
        return tomaps;
    }

}
