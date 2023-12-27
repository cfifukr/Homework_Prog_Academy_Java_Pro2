package org.example;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.text.SimpleDateFormat;


public class Currency {

    @SerializedName("exchangedate")
    private String date;

    @SerializedName("cc")
    private String currencyName;

    @SerializedName("rate")
    private double value;

    public Currency() {
    }

    public Currency(String date, String currencyName, double value) {
        this.date = date;
        this.currencyName = currencyName;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        SimpleDateFormat smf = new SimpleDateFormat("dd-MM-yyyy");
        return "CurrencyName ='" + currencyName + '\'' + smf.format(date) + " = " + value;
    }
}
