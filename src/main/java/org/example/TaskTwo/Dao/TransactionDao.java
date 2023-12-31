package org.example.TaskTwo.Dao;

import org.example.TaskTwo.DBs.Transaction;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;

public class TransactionDao {
    private final EntityManagerFactory emf;

    public TransactionDao(){
        this.emf = Persistence.createEntityManagerFactory("homework9");
    }


    public void save(Transaction transaction){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(transaction);
            em.getTransaction().commit();

        }finally {
            em.close();
        }
    }

    public Transaction findById(long id){
        EntityManager em = emf.createEntityManager();
        try {
            Transaction result = em.find(Transaction.class, id);
            return result;
        }finally {
            em.close();
        }
    }



}
