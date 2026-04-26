package com.ubl.mystudentapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFullname, etPassword, etEmail, etAddress;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullname = findViewById(R.id.et_fullname);
        etPassword = findViewById(R.id.et_password);
        etEmail = findViewById(R.id.et_email);
        etAddress = findViewById(R.id.et_address);
        Button btnRegister = findViewById(R.id.btn_register);
        TextView tvLoginLink = findViewById(R.id.tv_login_link);

        sharedPreferences = getSharedPreferences("StudentPrefs", Context.MODE_PRIVATE);

        btnRegister.setOnClickListener(v -> saveData());

        tvLoginLink.setOnClickListener(v -> finish());
    }

    private void saveData() {
        String fullname = etFullname.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String address = etAddress.getText().toString().trim();

        if (fullname.isEmpty() || password.isEmpty() || email.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, getString(R.string.msg_please_fill_all), Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fullname", fullname);
        editor.putString("password", password);
        editor.putString("email", email);
        editor.putString("address", address);
        editor.apply();

        Toast.makeText(this, getString(R.string.msg_register_success), Toast.LENGTH_SHORT).show();
        finish();
    }
}