package com.example.asce.databasetests;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class entry {
    String name;
    String id;
    String price;

    public  entry()
    {

    }

    public entry(String name ,String id,String price)
    {
        this.name =name;
        this.id =id ;
        this.price = price;
    }
    @Exclude
    public Map<String ,Object> mapper ()
    {
        HashMap <String ,Object> tomaps = new HashMap<>();
        tomaps.put("Name" , name);
        tomaps.put("id" , id);
        tomaps.put("price", price);
        return tomaps;
    }

}
