package com.example.loginandsignupusingsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText loginemailidet , loginpasswordet;
    Button loginbtn;
    TextView forgotpasstv,signuptv;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();

        signuptv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,signuppage.class);
                startActivity(intent);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginemailidet.getText().toString();
                String password = loginpasswordet.getText().toString();

                if(email==null){
                    loginemailidet.setError("please enter email");
                }
                else if(password==null){
                    loginpasswordet.setError("please enter password");
                }
                else{
                    boolean result = mydb.checkemailpassword(email,password);
                    if(result== true){
                        startActivity(new Intent(MainActivity.this,Dashboard.class));
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initializeVariables() {
        loginemailidet = findViewById(R.id.loginemailet);
        loginpasswordet =findViewById(R.id.loginpasswordet);
        loginbtn = findViewById(R.id.loginpagebtn);
        forgotpasstv =findViewById(R.id.forgotpass);
        signuptv = findViewById(R.id.loginsignuptv);
        mydb = new DatabaseHelper(this);
    }
}