package com.example.loginandsignupusingsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signuppage extends AppCompatActivity {

    TextView logintv;
    EditText signupemailidet,signuppasswordet,confirmpasswordet;
    Button signupbtn;
    DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);

        initializeVariables();
        logintv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(signuppage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signupemailidet.getText().toString();
                String  password = signuppasswordet.getText().toString();
                String confirmpass = confirmpasswordet.getText().toString();

                if(!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
                    signupemailidet.setError("Invalid Email ID");
                }
                else if(email==null){
                    signupemailidet.setError("email cannot be null");
                }
                else if(password==null){
                    signuppasswordet.setError("password cannot be null");
                }
                else if(password.length()<8){
                    signuppasswordet.setError("password should be not be less than 8 character");
                }
                else if(!password.equals(confirmpass)){
                    confirmpasswordet.setError("confirm password is not same");
                }
                else{
                    boolean checkemailresult = mydb.checkemail(email);
                    if(checkemailresult == false){
                        boolean regResult = mydb.insertData(email,password);
                        if(regResult==true){
                            Toast.makeText(signuppage.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(signuppage.this,MainActivity.class));
                        }
                        else{
                            Toast.makeText(signuppage.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(signuppage.this, "user already exists \n please Login", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initializeVariables(){
        logintv = findViewById(R.id.signuplogintv);
        signupemailidet = findViewById(R.id.signupemailet);
        signuppasswordet = findViewById(R.id.signuppasswordet);
        confirmpasswordet = findViewById(R.id.signupconfirmpasswordet);
        signupbtn = findViewById(R.id.signuppagebtn);
        mydb = new DatabaseHelper(this);
    }
}