package com.example.pizzacravings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {

    TextView tvTotal;
    String currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        currentUser = getIntent().getStringExtra("currentUserNameData");
        TextView textViewCurrentUser = findViewById(R.id.textViewCurrentUser);
        textViewCurrentUser.setText(currentUser);

        displayTotalMsg();
    }

    public void logout(View view){
        Intent switchActivity = new Intent(this,LogInActivity.class);
        startActivity(switchActivity);
    }

    private void displayTotalMsg(){
        tvTotal = findViewById(R.id.tvTotal);
        String total = getIntent().getStringExtra("total");
        String strMsg = "We've received your order" +
                "\n" + "Please pay " + total + "$";
        tvTotal.setText(strMsg);

    }

    public void btnMenu(View view){
        Intent switchActivity = new Intent(this,Menu.class);
        switchActivity.putExtra(LogInActivity.SEND_DATA,currentUser );
        startActivity(switchActivity);
    }
    public void cancel(View viw){
        finish();
    }

    public void confirm(View view){
        Toast.makeText(getApplicationContext(), "Thank you for ordering!", Toast.LENGTH_LONG).show();
    }
}
