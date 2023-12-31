package org.example.TaskOne;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DishDAO {
    private final EntityManagerFactory factory;


    public DishDAO() {
        this.factory = Persistence.createEntityManagerFactory("homework9");
    }

    public void saveDishesToDB(Dish... dish){
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            for (Dish i : dish) {
                em.persist(i);
            }
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
    }
    public List<Dish> getDishesByWeight(Integer minWeight, Integer maxWeight){
        if(minWeight == null){
            minWeight = 0;
        }
        if(maxWeight == null){
            maxWeight = 99999;
        }
        if (minWeight>maxWeight) {
            Integer tmp = minWeight;
            minWeight = maxWeight;
            maxWeight = tmp;
        }
        String jpql = "SELECT x FROM Dish x WHERE x.weight BETWEEN :minWeight AND : maxWeight";

        EntityManager em = factory.createEntityManager();
        try{
            TypedQuery<Dish> resultQuery = em.createQuery(jpql, Dish.class);
            resultQuery.setParameter("minWeight",  minWeight);
            resultQuery.setParameter("maxWeight",  maxWeight);
            return resultQuery.getResultList();
        }finally {
            em.close();
        }
    }

    public List<Dish> getDishesByPrice(Double minPrice, Double maxPrice){
        if(minPrice == null){
            minPrice = -1.00;
        }
        if(maxPrice == null){
            maxPrice = 99999.00;
        }
        if (minPrice>maxPrice) {
            Double tmp = minPrice;
            minPrice = maxPrice;
            maxPrice = tmp;
        }
        String jpql = "SELECT x FROM Dish x WHERE  x.price BETWEEN :minPrice AND :maxPrice";

        EntityManager em = factory.createEntityManager();
        try{
            TypedQuery<Dish> resultQuery = em.createQuery(jpql, Dish.class);
            resultQuery.setParameter("minPrice",  minPrice);
            resultQuery.setParameter("maxPrice",  maxPrice);
            return resultQuery.getResultList();
        }finally {
            em.close();
        }
    }

    public List<Dish> getDishesWithDiscount(){

        String jpql = "SELECT x FROM Dish x WHERE  x.discount != null";

        EntityManager em = factory.createEntityManager();
        try{
            TypedQuery<Dish> resultQuery = em.createQuery(jpql, Dish.class);
            return resultQuery.getResultList();
        }finally {
            em.close();
        }
    }

    public Dish pickRandomDish(Double maxPrice){

        String jpql = "SELECT x FROM Dish x WHERE x.price <= :maxPrice ORDER BY RAND()";

        EntityManager em = factory.createEntityManager();
        try{
            TypedQuery<Dish> resultQuery = em.createQuery(jpql, Dish.class);
            resultQuery.setParameter("maxPrice", maxPrice);
            resultQuery.setMaxResults(1);
            return resultQuery.getSingleResult();
        }finally {
            em.close();
        }
    }


    public double getCheapestPrice(){

        String jpql = "SELECT x FROM Dish x ORDER BY x.price ASC";


        EntityManager em = factory.createEntityManager();
        try{
            TypedQuery<Dish> resultQuery = em.createQuery(jpql, Dish.class);
            resultQuery.setMaxResults(1);
            return resultQuery.getSingleResult().getPrice();
        }finally {
            em.close();
        }
    }


    public List<Dish> createRandomSet(Double maxPrice){
        Double check = maxPrice;
        Double minPrice = getCheapestPrice();
        List<Dish> result = new ArrayList<>();
        while(check > minPrice){
            Dish dish = pickRandomDish(check);
            result.add(dish);
            check = check - dish.getPrice();

        }
        System.out.println("Money left " + String.format("%.2f", check));
        return result;

    }
}
