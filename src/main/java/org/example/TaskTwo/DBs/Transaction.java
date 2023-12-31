package org.example.TaskTwo.DBs;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    private double value;
    @ManyToOne
    @JoinColumn(name = "code")
    private Currency exchangeRate;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "userId")
    private Client client1;

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "userId")
    private Client client2;

    private Date date;

    public Transaction() {
    }

    public Transaction(double value, Currency exchangeRate, Client client1, Client client2, Date date) {
        this.value = value;
        this.exchangeRate = exchangeRate;
        this.client1 = client1;
        this.client2 = client2;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public Currency getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Currency exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Client getClient1() {
        return client1;
    }

    public void setClient1(Client client1) {
        this.client1 = client1;
    }

    public Client getClient2() {
        return client2;
    }

    public void setClient2(Client client2) {
        this.client2 = client2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", value=" + value +
                ", exchangeRate=" + exchangeRate +
                ", client1=" + client1 +
                ", client2=" + client2 +
                ", date=" + date +
                '}';
    }

}
