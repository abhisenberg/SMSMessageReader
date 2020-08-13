package com.abheisenberg.messagereader.sms;

import com.google.gson.annotations.SerializedName;

public class SMS {

    @SerializedName("senderName")
    private String senderName;

    @SerializedName("body")
    String body;

    @SerializedName("date")
    String date;

    @SerializedName("amount")
    String amount;

    public SMS(){
        senderName = "";
        body = "";
        date = "";
        amount = "";
    }

    public SMS(String senderName, String body, String date, String amount) {
        this.senderName = senderName;
        this.body = body;
        this.date = date;
        this.amount = amount;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
