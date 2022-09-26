package com.example.pizzacravings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class Menu extends AppCompatActivity {

    // Global variables
    HashMap<String, Integer>pricesPerSize = new HashMap<>();
    HashMap<String, Integer>pricesPerToppings = new HashMap<>();


    String currentUser; // reference to the current user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView textViewCurrentUser = findViewById(R.id.textViewCurrentUser);
        Intent intent = getIntent();
        currentUser = intent.getStringExtra(LogInActivity.SEND_DATA);

        textViewCurrentUser.setText(currentUser);

    }

    public void logout(View view){
        finish();
    }

    public void switchActivity(Pizza pizza){
        Intent switchActivity = new Intent(getApplicationContext(),PizzaDescription.class);
        switchActivity.putExtra(LogInActivity.SEND_DATA, pizza);
        switchActivity.putExtra("currentUserName",currentUser);
        startActivity(switchActivity);

    }

    public void onClickPizzas(View view){
        String name = "";
        String pizzaDesc = "";
        Pizza pizza = null;

        switch (view.getId()){
            case R.id.imageViewGardenPizza:
                // sizes
                pricesPerSize.put("small", 10);
                pricesPerSize.put("medium", 20);
                pricesPerSize.put("large", 30);
                // toppings
                pricesPerToppings.put("small", 2);
                pricesPerToppings.put("medium", 3);
                pricesPerToppings.put("large", 5);
                TextView textViewGarden = findViewById(R.id.textViewGarden); // Pizza Name
                name = textViewGarden.getText().toString();

                pizzaDesc = "Made with love and" + "\n" +  "fresh from the garden"; // Pizza Description

                pizza = new Pizza(name, pizzaDesc, pricesPerSize, pricesPerToppings);
                switchActivity(pizza);
                break;
            case R.id.imageViewHawaiian:
                // sizes
                pricesPerSize.put("small", 12);
                pricesPerSize.put("medium", 22);
                pricesPerSize.put("large", 28);
                // toppings
                pricesPerToppings.put("small", 3);
                pricesPerToppings.put("medium", 4);
                pricesPerToppings.put("large", 6);

                TextView textViewHawaiian = findViewById(R.id.textViewHawaiian); // Pizza Name
                name = textViewHawaiian.getText().toString();

                pizzaDesc = "Hate or love it" + "\n" +  "It's still a great pizza"; // Pizza Description
                pizza = new Pizza(name, pizzaDesc, pricesPerSize, pricesPerToppings);
                switchActivity(pizza);
                break;
            case R.id.imageViewTheWorks:
                pricesPerSize.put("small", 6);
                pricesPerSize.put("medium", 10);
                pricesPerSize.put("large", 15);
                // toppings
                pricesPerToppings.put("small", 1);
                pricesPerToppings.put("medium", 2);
                pricesPerToppings.put("large", 3);

                TextView textViewTheWorks = findViewById(R.id.textViewTheWorks); // Pizza Name
                name = textViewTheWorks.getText().toString();

                pizzaDesc = "A heaping helping of pepperoni, julienne-cut Canadian bacon," + "\n" +  "spicy Italian sausage, fresh-cut onions, crisp green " +
                        "peppers, mushrooms, ripe black olives, and real cheese made from mozzarella"; // Pizza Description
                pizza = new Pizza(name, pizzaDesc, pricesPerSize, pricesPerToppings);
                switchActivity(pizza);
                break;
            case R.id.imageViewZestyItalian:
                pricesPerSize.put("small", 14);
                pricesPerSize.put("medium", 24);
                pricesPerSize.put("large", 36);
                // toppings
                pricesPerToppings.put("small", 3);
                pricesPerToppings.put("medium", 4);
                pricesPerToppings.put("large", 6);

                TextView textViewZesty = findViewById(R.id.textViewZesty); // Pizza Name
                name = textViewZesty.getText().toString();

                pizzaDesc = "Our Best zeller pizza," + "\n" +  "It ze made with ze pure love " +
                        "Imported directly from Italy"; // Pizza Description
                pizza = new Pizza(name, pizzaDesc, pricesPerSize, pricesPerToppings);
                switchActivity(pizza);
        }
    }
}