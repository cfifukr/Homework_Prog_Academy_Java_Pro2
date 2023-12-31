package org.example.TaskOne;

import javax.persistence.*;

@Entity
@Table(name = "Menu")
public class Dish{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private Double price;

    private Double discount;


    public Dish() {
    }

    public Dish(String name, Integer weight, Double price, Double discount) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.discount = discount;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String discountString;
        if (discount == null || discount == 0.00){
            discountString = "  No discount";
        }else{
            discountString = "  Discount = " + discount;
        }
        return  name +  "(" + weight +
                " gr/ml)  price=" + price +
                discountString;
    }
}
