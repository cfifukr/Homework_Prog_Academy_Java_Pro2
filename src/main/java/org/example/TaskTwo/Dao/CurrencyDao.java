package org.example.TaskTwo.Dao;

import org.example.TaskTwo.API.Curr;
import org.example.TaskTwo.DBs.Client;
import org.example.TaskTwo.DBs.Currency;

import javax.persistence.*;
import java.util.List;

public class CurrencyDao {
    private final EntityManagerFactory emf;

    public CurrencyDao(){
        emf = Persistence.createEntityManagerFactory("homework9");
    }

    public void addCurrencies(Currency... currencies){
        EntityManager em = emf.createEntityManager();
        try {
            for (Currency cur : currencies) {
                try {
                    em.getTransaction().begin();
                    em.persist(cur);
                    em.getTransaction().commit();
                } catch (Exception e) {
                    em.getTransaction().rollback();
                }
            }
        }finally {
            em.close();
        }
    }

    public List<Currency> getAllCurrencies(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Currency> resultQuery  = em.createQuery("SELECT x FROM Currency x", Currency.class);
            return resultQuery.getResultList();
        }finally {
            em.close();
        }
    }

    public double getCurrencyRateByCode(String code){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Currency> resultQuery  = em.createQuery("SELECT x FROM Currency x WHERE x.code = :nameCurrency", Currency.class);
            resultQuery.setParameter("nameCurrency",  code.toUpperCase());

            return resultQuery.getSingleResult().getRate();
        }finally {
            em.close();
        }
    }

    public void saveArrayCurr(Curr[] currs){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Currency uah = new Currency("uah", 1.00);
            em.persist(uah);
            em.getTransaction().commit();
            for(Curr i : currs){
                em.getTransaction().begin();
                Currency currency = new Currency(i.getCurrencyName().toLowerCase(), i.getValue());
                em.persist(currency);
                em.getTransaction().commit();
            }
        }catch (RollbackException e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
    }


    public Currency getCurrencyByCode(String code) {
        EntityManager em = emf.createEntityManager();
        try {
            Currency result = em.find(Currency.class, code);
            return result;
        } finally {
            em.close();
        }
    }







}
