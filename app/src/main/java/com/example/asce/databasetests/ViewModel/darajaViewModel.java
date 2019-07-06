package com.example.asce.databasetests.ViewModel;

import android.arch.lifecycle.ViewModel;

import static com.example.asce.databasetests.BuildConfig.Consumer_key;
import static com.example.asce.databasetests.BuildConfig.Consumer_secret;

public class darajaViewModel extends ViewModel {
    private String amount  ;


    public String getBusinnesscode() {
        return "174379";
    }

    public String getPasskey() {
        return "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
    }
    public String getPartyA() {
        return "254703318241";
    }

    public String getPartyB() {
        return "174379";
    }

    public String getPhoneno() {
        return "254703318241";
    }

//    public String getCallbackurl() {
//        return callbackurl;
//    }
    public String getAccountreference() {
        return "Samuel Account";
    }

    public String getTransactiondec() {
        return "Train Booking";
    }

    public String getConsumerkey() {
        // add your own consumer key
        return Consumer_key;
    }

    public String getConsumesecret() {
        // add your own consumer secret
        return Consumer_secret;
    }

    public String getAmount(int price) {
        amount = String.valueOf(price);
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
