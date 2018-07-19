package com.example.asce.databasetests;

import java.util.HashMap;
import java.util.Map;

public class Fared {
    public Fared() {

    }

    private int nairobi_Mombasa , nairobi_Voi , voi_Mombasa;


    public Fared(int nairobi_Mombasa, int nairobi_Voi , int voi_Mombasa){
        this.nairobi_Voi =nairobi_Mombasa;
        this.nairobi_Mombasa = nairobi_Voi;
        this.voi_Mombasa = voi_Mombasa;
    };

    public int getNairobi_Mombasa() {
        return nairobi_Mombasa;
    }

    public int getNairobi_Voi() {
        return nairobi_Voi;
    }

    public int getVoi_Mombasa() {
        return voi_Mombasa;
    }

//   public  Map<String, Object> tomap (){
//       HashMap<String, Object> result = new HashMap<>();
//       result.put("Title", Title);
//       result.put("Content", Content);
//       result.put("Date", date);
//       return result;
//    }
}