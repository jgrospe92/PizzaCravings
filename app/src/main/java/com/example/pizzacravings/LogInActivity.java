package com.example.pizzacravings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class LogInActivity extends AppCompatActivity {

    public static HashMap<String,String> members = new HashMap<>();

    public static final String SEND_DATA = "com.example.pizzacravings.DATA";

    EditText editTextUserName;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        editTextUserName = (EditText)findViewById(R.id.editTextUserName);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        // Instantiate default member
        instantiateDefaultMembers();

    }

    private void instantiateDefaultMembers(){
        members.put("jeffrey", "1234");
    }

    private void clearEditText(){
        editTextUserName.getText().clear();
        editTextPassword.getText().clear();
    }

    private boolean authenticateUser(){

        // Views
        String username = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        Context context = getApplicationContext();

        if (username.equals("") && (password.equals(""))){
            Toast.makeText(context, "Please enter your credentials", Toast.LENGTH_SHORT).show();
            return  false;
        }

        if(members.get(username) != null){
            if(members.get(username).equals(password)){
                return  true;
            }else {
                Toast.makeText(context, "Invalid password", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        Toast.makeText(context, "user does not exist", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void switchActivity(Context currentContext, Class activityClass, String data){
        Intent switchActivity = new Intent(currentContext,activityClass);
        switchActivity.putExtra(SEND_DATA, data);
        startActivity(switchActivity);

    }

    public void btnActions(View view){

        switch (view.getId()){
            case R.id.btnLogin:
                if(authenticateUser()){
                    // clear text field
                    String currentUser = editTextUserName.getText().toString();
                    clearEditText();
                    switchActivity(LogInActivity.this , Menu.class, currentUser);
                }
                break;

            case R.id.btnRegister:

                switchActivity(LogInActivity.this, RegistrationActivity.class, "");
                break;
        }


    }

}