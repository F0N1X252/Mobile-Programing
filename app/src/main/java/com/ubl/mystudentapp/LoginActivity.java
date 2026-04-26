package com.ubl.mystudentapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email_login);
        etPassword = findViewById(R.id.et_password_login);
        Button btnLogin = findViewById(R.id.btn_login);
        TextView tvRegisterLink = findViewById(R.id.tv_register_link);

        sharedPreferences = getSharedPreferences("StudentPrefs", Context.MODE_PRIVATE);

        btnLogin.setOnClickListener(v -> login());

        tvRegisterLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void login() {
        String emailInput = etEmail.getText().toString().trim();
        String passwordInput = etPassword.getText().toString().trim();

        String registeredEmail = sharedPreferences.getString("email", "");
        String registeredPassword = sharedPreferences.getString("password", "");

        if (emailInput.isEmpty() || passwordInput.isEmpty()) {
            Toast.makeText(this, getString(R.string.msg_please_fill_login), Toast.LENGTH_SHORT).show();
            return;
        }

        if (Objects.equals(emailInput, registeredEmail) && Objects.equals(passwordInput, registeredPassword)) {
            Toast.makeText(this, getString(R.string.msg_login_success), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.msg_login_failed), Toast.LENGTH_SHORT).show();
        }
    }
}