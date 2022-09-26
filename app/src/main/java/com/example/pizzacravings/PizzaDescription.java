package com.example.pizzacravings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PizzaDescription extends AppCompatActivity {

    Pizza currentPizza;
    ImageView currentPizzaImg;

    int smallQTY = 0, mediumQTY = 0, largeQTY = 0;
    int extraToppingsSmall = 1, extraToppingsMed = 1, extraToppingsLarge = 1;
    int totalPrice = 0;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_description);
        currentPizza = (Pizza)getIntent().getSerializableExtra(LogInActivity.SEND_DATA);

        currentUser = getIntent().getStringExtra("currentUserName");
        TextView textViewCurrentUser = findViewById(R.id.textViewCurrentUser);
        textViewCurrentUser.setText(currentUser);

        TextView textViewCurrentPizza = findViewById(R.id.textViewCurrentPizza);
        textViewCurrentPizza.setText(currentPizza.name);

        currentPizzaImg = (ImageView) findViewById(R.id.imageViewCurrentPizza);
        setPizzaImg(currentPizza.name);

        setPizzaDesc();
        displayPizzaPrice();
        displayPricePerToppings();

    }

    private void updateAmount(TextView tv, int qty){
        String strQTY = Integer.toString(qty);
        tv.setText(strQTY);
    }

    private void displayCountToppings(TextView tv, int value){
        String num = Integer.toString(value);
        String toppingsString = num + (( value == 1) ? " topping" : " toppings");
        tv.setText(toppingsString);
    }

    private void calculateToppings(){
        // Pizza price per extra toppings
        int small = currentPizza.PricesPerToppings.get("small");
        int medium = currentPizza.PricesPerToppings.get("medium");
        int large = currentPizza.PricesPerToppings.get("large");
        int extraToppingsCost = 0;

        if (extraToppingsSmall > 1 && smallQTY > 0){
            extraToppingsCost += (extraToppingsSmall - 1) * small;
        }
        if (extraToppingsMed > 1 && mediumQTY > 0){
            extraToppingsCost += (extraToppingsMed - 1) * medium;
        }
        if (extraToppingsLarge > 1 && largeQTY > 0){
            extraToppingsCost += (extraToppingsLarge - 1 )* large;
        }

        totalPrice += extraToppingsCost;
    }

    private void calculateCostPerSize(){
        // Pizza price per size
        int small = currentPizza.PricesPerSize.get("small");
        int medium = currentPizza.PricesPerSize.get("medium");
        int large = currentPizza.PricesPerSize.get("large");
        int totalCost = (smallQTY * small ) + (mediumQTY * medium) + (largeQTY * large);
        totalPrice = totalCost;

    }

    private void displayTotal(){
        TextView tvTotalPrice = findViewById(R.id.tvTotalPrice);

        Double convertToDouble = (double)totalPrice;
        String totalCost = Double.toString(convertToDouble);
        String result = "TOTAL: " + totalCost + "$";

        tvTotalPrice.setText(result);

    }

    private void calculate(){
        calculateCostPerSize();
        calculateToppings();
        displayTotal();
    }

    public void orderNow(View view){

        if ( totalPrice > 0) {
            Intent switchActivity = new Intent(getApplicationContext(),CheckoutActivity.class);
            switchActivity.putExtra("currentUserNameData",currentUser);
            Double convertToDbl = (double)totalPrice;
            String convertToStr = Double.toString(convertToDbl);
            switchActivity.putExtra("total",convertToStr);
            startActivity(switchActivity);
        } else {
            Toast.makeText(getApplicationContext(), "Buy at least 1 pizza :)", Toast.LENGTH_SHORT).show();
        }

    }

    public void btnClickToppings(View view){

        TextView tvSmallTopping = findViewById(R.id.tvSmallTopping);
        TextView tvMediumTopping = findViewById(R.id.tvMediumTopping);
        TextView tvLargeTopping = findViewById(R.id.tvLargeTopping);

        switch (view.getId()){
            case R.id.imgBtnSmallAddToppings: ;
                if (extraToppingsSmall < 5){
                    extraToppingsSmall++;

                }
                calculate();
                displayCountToppings(tvSmallTopping, extraToppingsSmall);
                break;
            case R.id.imgBtnSmallMinusToppings:
                if (extraToppingsSmall > 1){
                    extraToppingsSmall--;
                }
                calculate();
                displayCountToppings(tvSmallTopping, extraToppingsSmall);
                break;
            case R.id.imgBtnMedAddToppings:
                if (extraToppingsMed < 5){
                    extraToppingsMed++;
                }
                calculate();
                displayCountToppings(tvMediumTopping, extraToppingsMed);
                break;
            case R.id.imgBtnMedMinusToppings:
                if (extraToppingsMed > 1){
                    extraToppingsMed--;
                }
                calculate();
                displayCountToppings(tvMediumTopping, extraToppingsMed);
                break;
            case  R.id.imgBtnLargeAddToppings:
                if (extraToppingsLarge < 5){
                    extraToppingsLarge++;
                }
                calculate();
                displayCountToppings(tvLargeTopping, extraToppingsLarge);
                break;
            case R.id.imgBtnLargeMinusToppings:
                if (extraToppingsLarge > 1) {
                    extraToppingsLarge--;
                }
                calculate();
                displayCountToppings(tvLargeTopping, extraToppingsLarge);
                break;
        }
    }


    public void btnClickedQTY(View view){

        TextView tvSmallQTY = findViewById(R.id.tvSmallQTY);
        TextView tvMediumQTY = findViewById(R.id.tvMediumQTY);
        TextView tvLargeQTY = findViewById(R.id.tvLargeQTY);

        switch (view.getId()){
            case R.id.imageSmallAddQTY:
                if(smallQTY < 10){
                    smallQTY++;
                }
                calculate();
                updateAmount(tvSmallQTY, smallQTY);
                break;
            case R.id.imageButtonSmallMinusQTY:
                if (smallQTY > 0){
                    smallQTY--;
                }
                calculate();
                updateAmount(tvSmallQTY, smallQTY);
                break;
            case R.id.imageBtnMedAddQTY:
                if(mediumQTY < 10){
                    mediumQTY++;
                }
                calculate();
                updateAmount(tvMediumQTY, mediumQTY);
                break;
            case R.id.imageBtnMedMinusQTY:
                if (mediumQTY > 0){
                    mediumQTY--;
                }
                calculate();
                updateAmount(tvMediumQTY, mediumQTY);
                break;
            case R.id.imgBtnLargeAddQTY:
                if(largeQTY < 10){
                    largeQTY++;
                }
                calculate();
                updateAmount(tvLargeQTY, largeQTY);
                break;
            case R.id.imgBtnLargeMinusQTY:
                if (largeQTY > 0){
                    largeQTY--;
                }
                calculate();
                updateAmount(tvLargeQTY, largeQTY);
                break;
        }

    }

    private void displayPizzaPrice(){
        TextView tvSmallPrice = findViewById(R.id.tvSmallPrice);
        TextView tvMediumPrice = findViewById(R.id.tvMediumPrice);
        TextView tvLargePrice = findViewById(R.id.tvLargePrice);
        String smallPrice = currentPizza.PricesPerSize.get("small") + "$";
        String mediumPrice = currentPizza.PricesPerSize.get("medium") + "$";
        String largePrice = currentPizza.PricesPerSize.get("large") + "$";
        tvSmallPrice.setText(smallPrice);
        tvMediumPrice.setText(mediumPrice);
        tvLargePrice.setText(largePrice);

    }

    private void displayPricePerToppings(){
        TextView tvSmallPricePerToppings = findViewById(R.id.tvSmallPricePerToppings);
        TextView tvMedPricePerToppings = findViewById(R.id.tvMedPricePerToppings);
        TextView tvLargePricePerToppings = findViewById(R.id.tvLargePricePerToppings);

        String smallPrice = currentPizza.PricesPerToppings.get("small") + "$" + "+";
        String mediumPrice = currentPizza.PricesPerToppings.get("medium") + "$" + "+";
        String largePrice = currentPizza.PricesPerToppings.get("large") + "$" + "+";

        tvSmallPricePerToppings.setText(smallPrice);
        tvMedPricePerToppings.setText(mediumPrice);
        tvLargePricePerToppings.setText(largePrice);
    }

    public void logout(View view){
        Intent switchActivity = new Intent(this,LogInActivity.class);
        startActivity(switchActivity);
    }

    private void setPizzaDesc(){
        TextView tvPizzaDesc = findViewById(R.id.tvPizzaDesc);
        String info = currentPizza.pizzaInfo;
        String msg = "\"" + info + "\"";
        tvPizzaDesc.setText(msg);
    }


    private void setPizzaImg(String name){

        switch (name){
            case  "Garden Delux":
                currentPizzaImg.setImageResource(R.drawable.gardenpizza);
                break;
            case "Hawaiian Bacon":
                currentPizzaImg.setImageResource(R.drawable.hawaiianbbqpizza);
                break;
            case "The Works":
                currentPizzaImg.setImageResource(R.drawable.theworks);
                break;
            case "Zesty Italian":
                currentPizzaImg.setImageResource(R.drawable.zestyitalianpizza);
                break;
        }
    }

}