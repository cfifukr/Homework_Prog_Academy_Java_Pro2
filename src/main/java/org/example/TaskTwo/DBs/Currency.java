package org.example.TaskTwo.DBs;

import org.example.TaskTwo.DBs.Acc.Acc;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Currency {


    @Id
    private String code;

    private double rate;

    public Currency() {
    }

    public Currency(String code, double rate) {
        this.code = code;
        this.rate = rate;
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

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", rate=" + rate +
                '}';
    }
}
