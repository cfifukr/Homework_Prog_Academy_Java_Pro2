package org.example.TaskTwo.DBs;

import org.example.TaskTwo.DBs.Acc.EUR;
import org.example.TaskTwo.DBs.Acc.UAH;
import org.example.TaskTwo.DBs.Acc.USD;

import javax.persistence.*;

@Entity
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long account_id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "uah_id")
    private UAH uahAcc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "usd_id")
    private USD usdAcc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "eur_id")
    private EUR eurAcc;

    @OneToOne(mappedBy = "account")
    private Client client;


    public Account() {
    }

    public Account(UAH uahAcc, USD usdAcc, EUR eurAcc) {
        this.uahAcc = uahAcc;
        this.usdAcc = usdAcc;
        this.eurAcc = eurAcc;
    }

    public USD getUsdAcc() {
        return usdAcc;
    }

    public void setUsdAcc(USD usdAcc) {
        this.usdAcc = usdAcc;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public UAH getUahAcc() {
        return uahAcc;
    }

    public void setUahAcc(UAH uahAcc) {
        this.uahAcc = uahAcc;
    }

    public EUR getEurAcc() {
        return eurAcc;
    }

    public void setEurAcc(EUR eurAcc) {
        this.eurAcc = eurAcc;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "BALANCES :  "  +
                "UAH=" + uahAcc +
                ", USD=" + usdAcc +
                ", USD=" + eurAcc;
    }

}
