package org.example.TaskTwo;

import org.example.TaskTwo.API.Curr;
import org.example.TaskTwo.API.CurrencyNbuAPI;
import org.example.TaskTwo.DBs.Client;
import org.example.TaskTwo.DBs.Currency;
import org.example.TaskTwo.DBs.Transaction;
import org.example.TaskTwo.Dao.ClientDao;
import org.example.TaskTwo.Dao.CurrencyDao;
import org.example.TaskTwo.Dao.TransactionDao;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        ClientDao clientDao = new ClientDao();
        CurrencyDao currencyDao = new CurrencyDao();
        TransactionDao transactionDao = new TransactionDao();


        for(int i = 0; i<= 10; i++){
            Client cl1 = ClientDao.createClient("TestUser" + (i+1) , 15 + i*5);
            clientDao.saveClients(cl1);

        }



        //Add currencies rate from NBU website
        Curr[] currs = CurrencyNbuAPI.getAllCurrenciesToday();
        currencyDao.saveArrayCurr(currs);



        //get summary balance
        Client cl = ClientDao.createClient("Vladyslav", 35);
        cl.getAccount().getUsdAcc().setBalance(120.50);
        cl.getAccount().getEurAcc().setBalance(60.90);
        cl.getAccount().getUahAcc().setBalance(9934.30);
        clientDao.saveClients(cl);


        // Total balance test
        Client cl2 = clientDao.getClientById(6);
        cl2.setEurBalance(999.00);
        cl2.setUahBalance(12493.00);

        try {
            clientDao.updateClientBalances(cl2);
        }catch (FailedTransactionException e){
            System.out.println(e);
        }

        System.out.println(clientDao.getClientById(6).getFull_name() + " Total balance : " + clientDao.getSummaryBalance(clientDao.getClientById(6), currencyDao));
        System.out.println(cl.getFull_name() + " Total balance : " + clientDao.getSummaryBalance(cl, currencyDao));



        //cl.sendMoneyFromEUR(cl2, 60.00);
        //cl.sendMoneyFromUAH(cl2, 934.00);
        //cl.sendMoneyFromUSD(cl2, 50.00);
        //працює тільки якщо змінити в дао   з homework9 на homework9_validate













    }
}
