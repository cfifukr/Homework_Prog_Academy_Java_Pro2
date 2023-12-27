package org.example;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;


    private String currencyName;


    private double value;

    public CurrencyEntity() {
    }

    public CurrencyEntity(Date date, String currencyName, double value) {

        this.date = date;
        this.currencyName = currencyName;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
        return currencyName + " " + smf.format(date) + " = " + value;
    }
}
