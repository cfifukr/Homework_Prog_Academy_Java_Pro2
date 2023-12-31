package org.example.TaskTwo.DBs.Acc;

import org.example.TaskTwo.DBs.Account;
import org.example.TaskTwo.DBs.Client;

import javax.persistence.*;

@Entity
public class EUR implements Acc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eur_id;

    @OneToOne(mappedBy = "eurAcc")
    private Account account;

    private Double balance;

    public long getId() {
        return eur_id;
    }

    public EUR(Double balance) {
        this.balance = balance;
    }

    public EUR() {
        this.balance = 0.00;
    }

    public void setId(long id) {
        this.eur_id = id;
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
        return balance + "eur";
    }
}
