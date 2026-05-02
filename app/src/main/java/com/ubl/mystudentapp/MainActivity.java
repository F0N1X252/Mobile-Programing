package com.ubl.mystudentapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etFullname, etPassword, etEmail, etAddress;
    private Spinner spinnerDepartment;
    private RadioGroup rgSemester;
    private CheckBox cbAgreement;
    private TextView tvResultDisplay;
    private LinearLayout resultContainer;
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
        spinnerDepartment = findViewById(R.id.spinner_department);
        rgSemester = findViewById(R.id.rg_semester);
        cbAgreement = findViewById(R.id.cb_agreement);
        tvResultDisplay = findViewById(R.id.tv_result_display);
        resultContainer = findViewById(R.id.result_container);
        Button btnRegister = findViewById(R.id.btn_register);

        sharedPreferences = getSharedPreferences("StudentPrefs", Context.MODE_PRIVATE);

        setupSpinner();

        btnRegister.setOnClickListener(v -> saveData());
    }

    private void setupSpinner() {
        List<String> departments = new ArrayList<>();
        departments.add(getString(R.string.dept_prompt));
        departments.add(getString(R.string.dept_informatics));
        departments.add(getString(R.string.dept_is));
        departments.add(getString(R.string.dept_cs));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(adapter);
    }

    private void saveData() {
        String fullname = etFullname.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String department = spinnerDepartment.getSelectedItem().toString();
        int selectedSemesterId = rgSemester.getCheckedRadioButtonId();

        // Validation
        if (fullname.isEmpty() || password.isEmpty() || email.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, getString(R.string.msg_please_fill_all), Toast.LENGTH_SHORT).show();
            resetResult();
            return;
        }

        if (spinnerDepartment.getSelectedItemPosition() == 0) {
            Toast.makeText(this, getString(R.string.error_dept), Toast.LENGTH_SHORT).show();
            resetResult();
            return;
        }

        if (selectedSemesterId == -1) {
            Toast.makeText(this, getString(R.string.error_semester), Toast.LENGTH_SHORT).show();
            resetResult();
            return;
        }

        if (!cbAgreement.isChecked()) {
            Toast.makeText(this, getString(R.string.error_agreement), Toast.LENGTH_SHORT).show();
            resetResult();
            return;
        }

        RadioButton rbSelected = findViewById(selectedSemesterId);
        String semester = rbSelected.getText().toString();

        // Save data
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fullname", fullname);
        editor.putString("password", password);
        editor.putString("email", email);
        editor.putString("address", address);
        editor.putString("department", department);
        editor.putString("semester", semester);
        editor.apply();

        // Show Result in TextView
        resultContainer.setBackgroundColor(Color.parseColor("#E8F5E9")); // Light Green
        tvResultDisplay.setGravity(Gravity.START);
        tvResultDisplay.setTextAlignment(TextView.TEXT_ALIGNMENT_TEXT_START);
        
        String resultText = getString(R.string.result_header) + "\n\n" +
                getString(R.string.label_name_result) + " : " + fullname + "\n" +
                getString(R.string.label_email_result) + " : " + email + "\n" +
                getString(R.string.label_address_result) + " : " + address + "\n" +
                getString(R.string.label_jurusan_result) + " : " + department + "\n" +
                getString(R.string.label_semester_result) + " : " + semester;
        
        tvResultDisplay.setText(resultText);
        Toast.makeText(this, getString(R.string.msg_save_success), Toast.LENGTH_SHORT).show();
    }

    private void resetResult() {
        resultContainer.setBackgroundColor(Color.parseColor("#F0F0F0")); // Light Gray
        tvResultDisplay.setGravity(Gravity.CENTER);
        tvResultDisplay.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        tvResultDisplay.setText(getString(R.string.result_placeholder));
    }
}