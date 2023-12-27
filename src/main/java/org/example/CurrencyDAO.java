package org.example;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class CurrencyDAO {
    private final EntityManagerFactory factory;
    private final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public CurrencyDAO() {
        this.factory = Persistence.createEntityManagerFactory("homework7");


    }

    public void saveCurrencyArray(Currency[] currencies) {
        for(Currency currency : currencies){
            saveCurrency(currency);
        }
    }
    public void saveCurrency(Currency currency) {
        EntityManager entityManager = factory.createEntityManager();
       try{
            entityManager.getTransaction().begin();

              CurrencyEntity currencyEntity = new CurrencyEntity(Utils.parseDateString(currency.getDate()),
                      currency.getCurrencyName(),
                      currency.getValue());
            entityManager.persist(currencyEntity);

            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }
    }

    public List<CurrencyEntity> getAllValuesCurrency(String currencyName){
        EntityManager entityManager = factory.createEntityManager();
        try{
            String jpql = "SELECT x FROM CurrencyEntity x WHERE x.currencyName = '" +   currencyName.toUpperCase() + "'";
            TypedQuery<CurrencyEntity> query = entityManager.createQuery(jpql, CurrencyEntity.class);
            return query.getResultList();
        }finally {
            entityManager.close();
        }

    }

    public double getAverageValueCurrency(String currencyName){
        EntityManager entityManager = factory.createEntityManager();
        try{
            String jpql = "SELECT AVG(x.value) FROM CurrencyEntity x WHERE x.currencyName = '" + currencyName.toUpperCase() + "'";

            TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);

            double averageRate = query.getSingleResult();


            return averageRate;
        }finally {
            entityManager.close();
        }

    }

    public double getCurrencyByDay(String currencyName, Date date){
        EntityManager entityManager = factory.createEntityManager();
        try{

            String jpql ="SELECT x.value FROM CurrencyEntity x WHERE x.currencyName = :curParam AND x.date = '" + formater.format(date) + "'";
            TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
            query.setParameter("curParam",  currencyName.toUpperCase());

            return query.getSingleResult();
        }finally {
            entityManager.close();
        }
    }

    public List<CurrencyEntity> maxValuePeriod(String currency, Date startDate, Date finishDate){
        EntityManager em = factory.createEntityManager();
        try{
            String jpql = "SELECT x FROM CurrencyEntity x " +
                    "WHERE x.value = " +
                    "(SELECT MAX(y.value) FROM CurrencyEntity y " +
                    "WHERE y.currencyName = :currency " +
                    "AND y.date BETWEEN '" + formater.format(startDate) + "' AND '" + formater.format(finishDate) + "') " +
                    "AND x.currencyName = :currency " +
                    "AND x.date BETWEEN '" + formater.format(startDate) + "' AND '" + formater.format(finishDate) + "'";

            TypedQuery<CurrencyEntity>  query = em.createQuery(jpql, CurrencyEntity.class);
            query.setParameter("currency", currency.toUpperCase());
            return query.getResultList();
        }finally {
            em.close();
        }
    }

    public List<CurrencyEntity> minValuePeriod(String currency, Date startDate, Date finishDate){
        EntityManager em = factory.createEntityManager();
        try{
            String jpql = "SELECT x FROM CurrencyEntity x " +
                    "WHERE x.value = " +
                    "(SELECT MIN(y.value) FROM CurrencyEntity y " +
                    "WHERE y.currencyName = :currency " +
                    "AND y.date BETWEEN '" + formater.format(startDate) + "' AND '" + formater.format(finishDate) + "') " +
                    "AND x.currencyName = :currency " +
                    "AND x.date BETWEEN '" + formater.format(startDate) + "' AND '" + formater.format(finishDate) + "'";

            TypedQuery<CurrencyEntity>  query = em.createQuery(jpql, CurrencyEntity.class);
            query.setParameter("currency", currency.toUpperCase());
            return query.getResultList();
        }finally {
            em.close();
        }
    }

    public void addCurrencyToDB(Date date1, Date date2, String currency){
        String request = CurrencyNbuAPI.buildRequestPeriod(date1, date2, currency);
        String jsonString = CurrencyNbuAPI.getJsonFromAPI(request);
        Currency[] arrayEuro = CurrencyNbuAPI.getCurrenciesFromJSON(jsonString);
        this.saveCurrencyArray(arrayEuro);
    }







}
