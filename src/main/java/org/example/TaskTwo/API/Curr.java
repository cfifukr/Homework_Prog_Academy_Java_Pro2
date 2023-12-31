package org.example.TaskTwo.API;

import com.google.gson.annotations.SerializedName;
public class Curr {
    @SerializedName("exchangedate")
    private String date;

    @SerializedName("cc")
    private String currencyName;

    @SerializedName("rate")
    private double value;

    public Curr() {
    }

    public Curr(String date, String currencyName, double value) {
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
        return "Currency{" +
                "date='" + date + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", value=" + value +
                '}';
    }
}

