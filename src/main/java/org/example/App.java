package org.example;


import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {
        CurrencyDAO currencyDAO = new CurrencyDAO();


        Date today = new Date();
        Date yearAgo = Date.from(ZonedDateTime.now().minusYears(1).toInstant());



        currencyDAO.addCurrencyToDB(yearAgo, today, "PLN");
        currencyDAO.addCurrencyToDB(yearAgo, today, "USD");
        currencyDAO.addCurrencyToDB(yearAgo, today, "EUR");


        // HOMEWORK 8

        //tasl 1
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = Date.from(ZonedDateTime.now().minusYears(1).plusDays(10).toInstant());
        Date date2 = Date.from(ZonedDateTime.now().minusMonths(6).plusDays(24).toInstant());

        double result1 = currencyDAO.getCurrencyByDay("PLN", date1 );
        double result2 = currencyDAO.getCurrencyByDay("EUR", date2 );

        System.out.println("PLN " + smf.format(date1)  + " = " + result1);
        System.out.println("EUR " + smf.format(date2)  + " = " + result2);

        //task 2

        List<CurrencyEntity> test1= currencyDAO.maxValuePeriod("PLN",yearAgo, today);
        System.out.println("\nMAX value of PLN in period between " + smf.format(yearAgo) + " and " + smf.format(today));
        for(CurrencyEntity i : test1 ){
            System.out.println(i);
        }

        Date test2Date = Utils.parseDateString("25.07.2023");

        List<CurrencyEntity> test2 = currencyDAO.maxValuePeriod("USD",test2Date, today);
        System.out.println("\nMAX value of USD in period between " + smf.format(test2Date) + " and " + smf.format(today));
        for(CurrencyEntity i : test2 ){
            System.out.println(i);
        }


        List<CurrencyEntity> test3 = currencyDAO.maxValuePeriod("EUR",yearAgo, test2Date);
        System.out.println("\nMIN value of EUR in period between " + smf.format(yearAgo) + " and " + smf.format(test2Date));
        for(CurrencyEntity i : test3 ){
            System.out.println(i);
        }


    }
}
