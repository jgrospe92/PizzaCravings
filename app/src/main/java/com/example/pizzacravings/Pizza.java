package com.example.pizzacravings;


import java.io.Serializable;
import java.util.HashMap;

public class Pizza implements Serializable {
    // Pizza attributes
    String name;
    String pizzaInfo;
    HashMap<String, Integer> PricesPerSize = new HashMap<>();
    HashMap<String, Integer> PricesPerToppings = new HashMap<>();

    public Pizza(String name, String pizzaInfo, HashMap<String, Integer> pricesPerSize, HashMap<String, Integer> pricesPerToppings) {
        this.name = name;
        this.pizzaInfo = pizzaInfo;
        PricesPerSize = pricesPerSize;
        PricesPerToppings = pricesPerToppings;
    }

}
