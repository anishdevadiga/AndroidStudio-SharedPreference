package com.example.week8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        usernameEditText=(EditText) findViewById(R.id.user);
        passwordEditText=(EditText) findViewById(R.id.pass);
        Button l=(Button) findViewById(R.id.login);
        Button back=(Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backin=new Intent(login.this,MainActivity.class);
                startActivity(backin);
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

    }
    private void loginUser()
    {
        boolean flag=true;
        String username=usernameEditText.getText().toString();
        String password=passwordEditText.getText().toString();

        if(username.isEmpty() || password.isEmpty())
        {
            Toast.makeText(login.this,"Enter all fields",Toast.LENGTH_SHORT).show();
            flag=false;
        }
        if(flag)
        {
            SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
            String user=sharedPreferences.getString("Username",null);
            String pass=sharedPreferences.getString("Password",null);
            if(username.equals(user) && password.equals(pass)){
                Toast.makeText(login.this,"Login done",Toast.LENGTH_SHORT).show();
                usernameEditText.setText(" ");
                passwordEditText.setText(" ");
            }
            else
            {
                Toast.makeText(login.this,"Login failed",Toast.LENGTH_SHORT).show();
            }
        }

    }
}