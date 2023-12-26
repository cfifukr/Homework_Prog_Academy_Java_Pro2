package org.example;


import java.time.ZonedDateTime;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        CurrencyDAO currencyDAO = new CurrencyDAO();


        Date today = new Date();
        Date past = Date.from(ZonedDateTime.now().minusMonths(6).toInstant());


        //euro
        String euroRequest = CurrencyNbuAPI.buildRequestPeriod(past, today, "EUR");
        String euroJson = CurrencyNbuAPI.getJsonFromAPI(euroRequest);
        Currency[] arrayEuro = CurrencyNbuAPI.getCurrenciesFromJSON(euroJson);
        currencyDAO.saveCurrencyArray(arrayEuro);




        Date past2 = Date.from(ZonedDateTime.now().minusMonths(8).minusDays(54).toInstant());

        //zlotiy
        String zlotiyRequest = CurrencyNbuAPI.buildRequestPeriod(past2, today, "PLN");
        String zlotiyJson = CurrencyNbuAPI.getJsonFromAPI(zlotiyRequest);
        Currency[] arrayZlotiy = CurrencyNbuAPI.getCurrenciesFromJSON(zlotiyJson);
        currencyDAO.saveCurrencyArray(arrayZlotiy);

        System.out.println("Average value PLN = " + currencyDAO.getAverageValueCurrency("PLN"));
        System.out.println("Average value EUR = " + currencyDAO.getAverageValueCurrency("EUR"));


    }
}
