package com.ubl.mystudentapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etFullname, etPassword, etEmail, etAddress;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        etFullname = findViewById(R.id.et_fullname);
        etPassword = findViewById(R.id.et_password);
        etEmail = findViewById(R.id.et_email);
        etAddress = findViewById(R.id.et_address);
        Button btnRegister = findViewById(R.id.btn_register);

        sharedPreferences = getSharedPreferences("StudentPrefs", Context.MODE_PRIVATE);

        btnRegister.setOnClickListener(v -> saveData());
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

        Toast.makeText(this, getString(R.string.msg_save_success), Toast.LENGTH_SHORT).show();
    }
}