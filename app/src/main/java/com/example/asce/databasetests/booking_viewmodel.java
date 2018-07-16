package com.example.asce.databasetests;

import android.arch.lifecycle.ViewModel;


public class booking_viewmodel extends ViewModel {
    //String dateFormat = "yyyy/MM/dd";
    // SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.GERMAN);

//    String dateString = sdf.format(currentdate);
//        Log.e("sam", "" );


    booking_viewmodel(){
        setdefault();
    }

    private int mYear , mMonth , mDay,price;
    String destination_station ,departure_station , economical_status;

    public String getEconomical_status() {
        return economical_status;
    }

    public String getDeparture_station() {
        return departure_station;
    }

    public String getDestination_station() {
        return destination_station;
    }

    public void setEconomical_status(String economical_status) {
        this.economical_status = economical_status;
    }

    public void setDeparture_station(String departure_station) {
        this.departure_station = departure_station;
    }

    public void setDestination_station(String destination_station) {
        this.destination_station = destination_station;
    }

    long dategotten;

    public long getDategotten() {
        return dategotten;
    }
    public void getdatechanged(int mYear ,int mMonth ,int mDay,int price)
    {
    this.mYear =mYear;
    this.mMonth=mMonth;
    this.mDay = mDay;
    this.price=price;
    }

    public int getmDay() {
        return mDay;
    }

    public int getmMonth() {
        return mMonth;
    }

    public int getmYear() {
        return mYear;
    }

    public int getPrice() {
        return price;
    }

    public void setDategotten(long dategotten) {
        this.dategotten = dategotten;
    }
    public void setdefault()
    {
        dategotten = System.currentTimeMillis();
    }
}
