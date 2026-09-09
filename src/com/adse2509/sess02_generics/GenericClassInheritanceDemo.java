package com.adse2509.sess02_generics;

import java.time.LocalDate;

import com.adse2509.classes.Fruit;

public class GenericClassInheritanceDemo
{

    public static void main(String[] args)
    {
        // Create a fruit object
        Fruit apple = new Fruit("Apple", LocalDate.now().plusDays(14), "sweet", 145.0f);

        // Create an apple fruit container
        FoodContainer<Fruit> appleContainer = new FoodContainer<>(apple);

        // Call/invoke the methods from the 'Container' and 'FoodContainer' classes.
        appleContainer.displayItemInfo(); // Display the fruit's details
        appleContainer.displayExpirationDate(); // Display the fruit's expiration date

        // TODO
        // 1. Create a Drink object (your favourite juice/soda)
        // 2. Create a Juice Container
        // 3. Invoke methods from the 'Container' and 'DrinkContainer' classes.
    }

}
