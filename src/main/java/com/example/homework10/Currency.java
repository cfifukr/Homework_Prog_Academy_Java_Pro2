package com.example.homework10;

import com.google.gson.annotations.SerializedName;

public class Currency {
    @SerializedName("cc")
    private String code;

    @SerializedName("rate")
    private double rate;

    @SerializedName("txt")
    private String codeUkr;

    public Currency(String code, double rate, String codeUkr) {
        this.code = code;
        this.rate = rate;
        this.codeUkr = codeUkr;
    }

    public Currency() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCodeUkr() {
        return codeUkr;
    }

    public void setCodeUkr(String codeUkr) {
        this.codeUkr = codeUkr;
    }

    @Override
    public String toString() {
        return code + "(" + codeUkr + ") = " + rate;

    }
}
