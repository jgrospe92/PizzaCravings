package com.example.pizzacravings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    private boolean register(){
        // Views
        EditText newUsername = (EditText) findViewById(R.id.editTextSetUserName);
        EditText newPassword = (EditText) findViewById(R.id.editTextSetPassword);
        EditText confirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        String username = newUsername.getText().toString();
        String password = newPassword.getText().toString();
        String confirmPass = confirmPassword.getText().toString();

        if(username.equals("") || password.equals("") || confirmPass.equals("")){
            Toast.makeText(this, "Please complete the form", Toast.LENGTH_SHORT).show();
            return  false;
        }

        if (password.equals(confirmPass)){
            LogInActivity.members.put(username, password);
            return true;
        } else {
            Toast.makeText(this, "Password mismatched", Toast.LENGTH_SHORT).show();
        }
        return  false;
    }

    public void regComplete(View view){
        if(register()){
            finish();
        }

    }
    public void cancelReg(View view){
        finish();
    }
}