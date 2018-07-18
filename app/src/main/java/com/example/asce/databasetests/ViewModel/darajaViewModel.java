package com.example.asce.databasetests.ViewModel;

import android.arch.lifecycle.ViewModel;

public class darajaViewModel extends ViewModel {
    private String consumerkey ="IUgqx9M5VSyo21ZX6oMUbIjJcZANdLxu";
    private String consumesecret="F2KdwWh7HUh8K37c";
    private String businnesscode = "174379";
    private String passkey = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
    private String amount  ;
    private String partyA = "254703318241";
    private String partyB = "174379";
    private String phoneno = "254703318241";
    private String callbackurl = "http://mycallbackurl.com/checkout.php";
    private String accountreference = "Samuel Account";
    private String transactiondec = "Train Booking";


    public String getBusinnesscode() {
        return businnesscode;
    }

    public String getPasskey() {
        return passkey;
    }

    public String getPartyA() {
        return partyA;
    }

    public String getPartyB() {
        return partyB;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public String getCallbackurl() {
        return callbackurl;
    }

    public String getAccountreference() {
        return accountreference;
    }

    public String getTransactiondec() {
        return transactiondec;
    }

    public String getConsumerkey() {
        return consumerkey;
    }

    public String getConsumesecret() {
        return consumesecret;
    }

    public String getAmount(int price) {
        amount = String.valueOf(price);
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
