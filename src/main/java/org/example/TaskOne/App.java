package org.example.TaskOne;

import java.util.List;

public class App {
    public static  void main(String[] args){

        DishDAO dishDAO = new DishDAO();

        Dish omlet = new Dish("Omlet", 250, 200.70, null);
        Dish caesar = new Dish("Caesar", 300, 343.00, 15.00);
        Dish beer = new Dish("Beer", 500, 175.00, 10.00);

        Dish testDish1 = new Dish("Test1", 999, 1999.99, 25.00);
        Dish testDish2 = new Dish("Test2", 650, 899.99, 15.00);
        Dish testDish3 = new Dish("Test3", 100, 340.50, null);

        dishDAO.saveDishesToDB(omlet, caesar, beer, testDish1, testDish2, testDish3);


        System.out.println("\nWEIGHT QUERY TEST");
        List<Dish> resultQuery1 = dishDAO.getDishesByWeight(250,  700);
        for(Dish i : resultQuery1){
            System.out.println(i);
        }

        System.out.println("\nPRICE QUERY TEST");
        List<Dish> resultQuery2 = dishDAO.getDishesByPrice(345.00, 999.99);
        for(Dish i : resultQuery2){
            System.out.println(i);
        }

        System.out.println("\nDISCOUNT QUERY TEST");
        List<Dish> resultQuery3 = dishDAO.getDishesWithDiscount();
        for(Dish i : resultQuery3){
            System.out.println(i);
        }


        System.out.println("\nRANDOM SET TEST(DISHES CAN REPEAT)");
        List<Dish> resultQuery4 = dishDAO.createRandomSet(1000.00);
        for(Dish i : resultQuery4){
            System.out.println(i);
        }




    }
}
