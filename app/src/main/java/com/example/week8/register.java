package com.example.week8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class register extends AppCompatActivity {
    private EditText user,pass,email,phone,course,age;
    private RadioGroup gender;
    private Button reg,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user=(EditText) findViewById(R.id.user);
        pass=(EditText) findViewById(R.id.pass);
        email=(EditText) findViewById(R.id.email);
        phone=(EditText) findViewById(R.id.phone);
        course=(EditText) findViewById(R.id.course);
        age=(EditText) findViewById(R.id.age);
        gender=(RadioGroup) findViewById(R.id.gender);

        reg=(Button) findViewById(R.id.reg);
        back=(Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backreg=new Intent(register.this,MainActivity.class);
                startActivity(backreg);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }
    private void registerUser()
    {
        boolean flag=true;
        String username=user.getText().toString();
        String password=pass.getText().toString();
        String phonenumber=phone.getText().toString();
        String cour=course.getText().toString();
        String emailtext=email.getText().toString();
        String agetext=age.getText().toString();
        String regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$";
        int id=gender.getCheckedRadioButtonId();
        RadioButton g=findViewById(id);
        String genderText=g!=null?g.getText().toString():null;
        if(username.isEmpty() || password.isEmpty() || phonenumber.isEmpty() || cour.isEmpty() || emailtext.isEmpty() || agetext.isEmpty())
        {
            Toast.makeText(register.this,"Enter all fields",Toast.LENGTH_SHORT).show();
            flag=false;
        }
        if (!password.matches(regexp))
        {
            Toast.makeText(register.this,"Invalid Password patter",Toast.LENGTH_SHORT).show();
            flag=false;
        }
        if(phonenumber.length()!=10)
        {
            Toast.makeText(register.this,"Invalid phone number",Toast.LENGTH_SHORT).show();
            flag=false;
        }
        if(!emailtext.endsWith("@xyz.com") && !emailtext.endsWith("@xyz.edu") && !emailtext.endsWith("@xyz.in")   && !emailtext.endsWith("@xyz.org") )
        {
            Toast.makeText(register.this,"Invalid email",Toast.LENGTH_SHORT).show();
            flag=false;
        }
        if(genderText==null)
        {
            Toast.makeText(register.this,"Select the gender",Toast.LENGTH_SHORT).show();
            flag=false;
        }
        if(flag)
        {
                SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Username",username);
                editor.putString("Password",password);
                editor.putString("Email",emailtext);
                editor.putString("PhoneNumber",phonenumber);
                editor.putString("Gender",genderText);
                editor.putString("Course",cour);
                editor.putString("Age",agetext);
                Toast.makeText(register.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                finish();
            editor.apply();

        }
        else {
            Toast.makeText(register.this,"**Error***",Toast.LENGTH_SHORT).show();
        }

    }
}