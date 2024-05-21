package com.example.firetruck3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        Button btnLogin = findViewById(R.id.buttonLogin);
        Button btnRegister = findViewById(R.id.buttonRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.editTextUsername)).getText().toString().trim();
                String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                } else {
                    if (username.equals("daovinhkhang") && password.equals("0834684568")) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else if (db.checkUser(username, password)) {
                        startActivity(new Intent(LoginActivity.this, MainActivity2.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
