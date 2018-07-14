package com.example.asce.databasetests;

import android.arch.lifecycle.ViewModel;


public class booking_viewmodel extends ViewModel {
    int mYear , mMonth , mDay,price;

    booking_viewmodel()
    {
        setdefault();
    }
    long dategotten;
    //String dateFormat = "yyyy/MM/dd";
   // SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.GERMAN);

//    String dateString = sdf.format(currentdate);
//        Log.e("sam", "" );


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
