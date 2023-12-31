package org.example.TaskTwo.Tests;

import org.example.TaskTwo.API.Curr;
import org.example.TaskTwo.API.CurrencyNbuAPI;

import org.example.TaskTwo.Dao.ClientDao;
import org.example.TaskTwo.Dao.CurrencyDao;

public class TestCurrency {

    public static void main(String[] args) {
        CurrencyDao currencyDao = new CurrencyDao();



        Curr[] currs = CurrencyNbuAPI.getAllCurrenciesToday();
        currencyDao.saveArrayCurr(currs);

        for(Curr i : currs){
            System.out.println(i);
        }

        System.out.println(currencyDao.getCurrencyByCode("EUR"));


    }
}
