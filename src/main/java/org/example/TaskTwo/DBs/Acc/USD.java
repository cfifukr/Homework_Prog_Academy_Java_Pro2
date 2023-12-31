package org.example.TaskTwo.DBs.Acc;

import org.example.TaskTwo.DBs.Account;
import org.example.TaskTwo.DBs.Client;


import javax.persistence.*;

@Entity
public class USD implements Acc{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long usd_id;

    @OneToOne(mappedBy = "usdAcc")
    private Account account;

    private Double balance;

    public long getId() {
        return usd_id;
    }

    public USD(Double balance) {
        this.balance = balance;
    }

    public USD() {
        this.balance = 0.00;
    }

    public void setId(long id) {
        this.usd_id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    @Override
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    @Override
    public Client getClient(){
        return  this.getAccount().getClient();
    };

    @Override
    public double convertToUAH(double value) {
        return 0;
    }

    @Override
    public double convertFromUAH(double value) {
        return 0;
    }

    @Override
    public String toString() {
        return balance + "usd";
    }
}
