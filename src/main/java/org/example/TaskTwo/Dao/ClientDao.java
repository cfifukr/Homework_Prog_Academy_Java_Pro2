package org.example.TaskTwo.Dao;

import org.example.TaskTwo.DBs.Acc.EUR;
import org.example.TaskTwo.DBs.Acc.UAH;
import org.example.TaskTwo.DBs.Acc.USD;
import org.example.TaskTwo.DBs.Account;
import org.example.TaskTwo.DBs.Client;
import org.example.TaskTwo.FailedTransactionException;

import javax.persistence.*;
import java.util.List;

public class ClientDao {
    private final EntityManagerFactory emf;


    public ClientDao(){
        this.emf = Persistence.createEntityManagerFactory("homework9");
    }

    public void updateClientBalances(Client updatedClient) throws FailedTransactionException {
        EntityManager em = emf.createEntityManager();;
        Client existingClient = em.find(Client.class, updatedClient.getUserId());

        try {
            if (existingClient != null) {
                em.getTransaction().begin();
                existingClient.setEurBalance(updatedClient.getEurBalance());
                existingClient.setUsdBalance(updatedClient.getUsdBalance());
                existingClient.setUahBalance(updatedClient.getUahBalance());
                em.getTransaction().commit();
            }else{
                saveClients(updatedClient);
            }
        }catch (Exception e){
            em.getTransaction().rollback();
            throw new FailedTransactionException();
        }finally{
            em.close();
        }

    }
    public Client getClientById(long id ){
        EntityManager em = emf.createEntityManager();
        try{
            Client result = em.find(Client.class, id);
            return  result;
        }finally {
            em.close();
        }
    }
    public void saveClients(Client... client){
        EntityManager em = emf.createEntityManager();
        try {
            for(Client cl : client){
                em.getTransaction().begin();
                em.persist(cl);
                em.getTransaction().commit();
            }

        }catch (Exception e){

            em.getTransaction().rollback();
        }finally {
            em.close();
        }

    }


    public List<Client> getClientsAll(){

        EntityManager em = emf.createEntityManager();
        try {

            TypedQuery<Client> resultQuery= em.createQuery("SELECT x FROM Client x", Client.class);

            return resultQuery.getResultList();
        }finally {
            em.close();
        }

    }

    public static Client createClient(String name, int age){
        Client client = new Client(name, age);

        Account account = new Account(new UAH(), new USD(), new EUR());

        client.setAccount(account);


        return client;
    }

    public double getSummaryBalance(Client client, CurrencyDao currencyDao) {


        double res = client.getAccount().getUahAcc().getBalance();
        res += client.getAccount().getUsdAcc().getBalance() * currencyDao.getCurrencyRateByCode("EUR");
        res += client.getAccount().getEurAcc().getBalance() * currencyDao.getCurrencyRateByCode("USD");

        return res;
    }
}
