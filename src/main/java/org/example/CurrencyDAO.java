package org.example;

import javax.persistence.*;
import java.util.List;

public class CurrencyDAO {
    private final EntityManagerFactory factory;

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

            entityManager.persist(currency);

            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }
    }

    public List<Currency> getAllValuesCurrency(String currencyName){
        EntityManager entityManager = factory.createEntityManager();
        try{
            String jpql = "SELECT x FROM Currency x WHERE x.currencyName = '" +   currencyName.toUpperCase() + "'";
            TypedQuery<Currency> query = entityManager.createQuery(jpql, Currency.class);
            return query.getResultList();
        }finally {
            entityManager.close();
        }

    }

    public double getAverageValueCurrency(String currencyName){
        EntityManager entityManager = factory.createEntityManager();
        try{
            String jpql = "SELECT AVG(x.value) FROM Currency x WHERE x.currencyName = '" + currencyName.toUpperCase() + "'";

            TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);

            double averageRate = query.getSingleResult();


            return averageRate;
        }finally {
            entityManager.close();
        }

    }






}
