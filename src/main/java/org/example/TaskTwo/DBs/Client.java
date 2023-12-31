package org.example.TaskTwo.DBs;

import org.example.TaskTwo.Dao.ClientDao;
import org.example.TaskTwo.Dao.CurrencyDao;
import org.example.TaskTwo.Dao.TransactionDao;
import org.example.TaskTwo.FailedTransactionException;
import org.hibernate.sql.Update;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String full_name;
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "acc_id", referencedColumnName = "account_id")
    private Account account;

    @OneToMany(mappedBy = "client1", cascade = CascadeType.ALL)
    private List<Transaction> outcomeTransactions = new ArrayList<>();
    @OneToMany(mappedBy = "client2", cascade = CascadeType.ALL)
    private List<Transaction> incomeTransactions = new ArrayList<>();

    public Client() {
    }

    public Client(String full_name, Integer age) {
        this.full_name = full_name;
        this.age = age;

    }


    private void addIncomeTrans(Transaction transaction){
        this.incomeTransactions.add(transaction);

    }

    private void addOutcomeTrans(Transaction transaction){
        this.outcomeTransactions.add(transaction);
    }

    public List<Transaction> getOutcomeTransactions() {
        return outcomeTransactions;
    }

    public void setOutcomeTransactions(List<Transaction> outcomeTransacions) {
        this.outcomeTransactions = outcomeTransacions;
    }

    public List<Transaction> getIncomeTransactions() {
        return incomeTransactions;
    }

    public void setIncomeTransactions(List<Transaction> incomeTransactions) {
        this.incomeTransactions = incomeTransactions;
    }

    public double getUahBalance(){
        return getAccount().getUahAcc().getBalance();
    }
    public double getUsdBalance(){
        return getAccount().getUsdAcc().getBalance();
    }
    public double getEurBalance(){
        return getAccount().getEurAcc().getBalance();
    }

    public void setUahBalance(Double uahBalance){
        getAccount().getUahAcc().setBalance(uahBalance);
    }

    public void setUsdBalance(Double usdBalance){
        getAccount().getUsdAcc().setBalance(usdBalance);
    }
    public void setEurBalance(Double eurBalance){
        getAccount().getEurAcc().setBalance(eurBalance);
    }
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean sendMoneyFromUSD(Client recipient, double value){
        ClientDao clientDao = new ClientDao();
        CurrencyDao currencyDao = new CurrencyDao();
        TransactionDao transactionDao = new TransactionDao();
        if(this.getUsdBalance() < value){
            System.out.println("YOU DONT HAVE ENOUGH MONEY FOR TRANSACTION");
            return false;
        }
        try {
            this.setUsdBalance(this.getUsdBalance() - value);
            recipient.setUsdBalance(recipient.getUsdBalance() + value);
            clientDao.updateClientBalances(this);
            clientDao.updateClientBalances(recipient);

        }catch (FailedTransactionException e){
            System.out.println("TRANSACTION FAILED");
            return false;
        }

        Transaction tr = new Transaction(value, currencyDao.getCurrencyByCode("USD"), this, recipient, new Date());
        transactionDao.save(tr);

        return true;

    }
    public boolean sendMoneyFromEUR(Client recipient, double value){
        ClientDao clientDao = new ClientDao();
        CurrencyDao currencyDao = new CurrencyDao();
        TransactionDao transactionDao = new TransactionDao();
        if(this.getEurBalance() < value){
            System.out.println("YOU DONT HAVE ENOUGH MONEY FOR TRANSACTION");
            return false;
        }
        try {
            this.setEurBalance(this.getEurBalance() - value);
            recipient.setEurBalance(recipient.getEurBalance() + value);
            clientDao.updateClientBalances(this);
            clientDao.updateClientBalances(recipient);
        }catch (FailedTransactionException e){
            System.out.println("TRANSACTION FAILED");
            return false;
        }
        Transaction tr = new Transaction(value, currencyDao.getCurrencyByCode("EUR"), this, recipient,new Date());
        transactionDao.save(tr);


        return true;


    }
    public boolean sendMoneyFromUAH(Client recipient, double value){
        ClientDao clientDao = new ClientDao();
        CurrencyDao currencyDao = new CurrencyDao();
        TransactionDao transactionDao = new TransactionDao();
        if(this.getUahBalance() < value){
            System.out.println("YOU DONT HAVE ENOUGH MONEY FOR TRANSACTION");
            return false;
        }
        try {
            this.setUahBalance(this.getUsdBalance() - value);
            recipient.setUahBalance(recipient.getUahBalance() + value);
            clientDao.updateClientBalances(this);
            clientDao.updateClientBalances(recipient);
        }catch (FailedTransactionException e){
            System.out.println("TRANSACTION FAILED");
            return false;
        }
        Transaction tr = new Transaction(value, currencyDao.getCurrencyByCode("UAH"), this, recipient,new Date());
        transactionDao.save(tr);


        return true;

    }


    @Override
    public String toString() {
        return  full_name  +"( id = " + userId + ") " + age  + "y.o. " + account;
    }
}
