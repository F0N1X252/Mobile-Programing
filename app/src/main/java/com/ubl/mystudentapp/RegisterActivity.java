package com.ubl.mystudentapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFullname, etPassword, etEmail, etAddress;
    private Spinner spinnerDepartment;
    private RadioGroup rgSemester;
    private CheckBox cbAgreement;
    private TextView tvResultDisplay, tvErrorMessage;
    private LinearLayout resultContainer;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullname = findViewById(R.id.et_fullname);
        etPassword = findViewById(R.id.et_password);
        etEmail = findViewById(R.id.et_email);
        etAddress = findViewById(R.id.et_address);
        spinnerDepartment = findViewById(R.id.spinner_department);
        rgSemester = findViewById(R.id.rg_semester);
        cbAgreement = findViewById(R.id.cb_agreement);
        tvResultDisplay = findViewById(R.id.tv_result_display);
        tvErrorMessage = findViewById(R.id.tv_error_message);
        resultContainer = findViewById(R.id.result_container);
        Button btnRegister = findViewById(R.id.btn_register);
        TextView tvLoginLink = findViewById(R.id.tv_login_link);

        sharedPreferences = getSharedPreferences("StudentPrefs", Context.MODE_PRIVATE);

        setupSpinner();

        btnRegister.setOnClickListener(v -> saveData());

        tvLoginLink.setOnClickListener(v -> finish());
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
            showError(getString(R.string.msg_please_fill_all));
            return;
        }

        if (spinnerDepartment.getSelectedItemPosition() == 0) {
            showError(getString(R.string.error_dept));
            return;
        }

        if (selectedSemesterId == -1) {
            showError(getString(R.string.error_semester));
            return;
        }

        if (!cbAgreement.isChecked()) {
            showError(getString(R.string.error_agreement));
            return;
        }

        // Success - hide error
        tvErrorMessage.setVisibility(View.GONE);

        RadioButton rbSelected = findViewById(selectedSemesterId);
        String semester = rbSelected.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fullname", fullname);
        editor.putString("password", password);
        editor.putString("email", email);
        editor.putString("address", address);
        editor.putString("department", department);
        editor.putString("semester", semester);
        editor.apply();

        // Update UI Result Box
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
        Toast.makeText(this, getString(R.string.msg_register_success), Toast.LENGTH_SHORT).show();
    }

    private void showError(String message) {
        tvErrorMessage.setText(message);
        tvErrorMessage.setVisibility(View.VISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        resetResult();
    }

    private void resetResult() {
        resultContainer.setBackgroundColor(Color.parseColor("#F0F0F0")); // Light Gray
        tvResultDisplay.setGravity(Gravity.CENTER);
        tvResultDisplay.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        tvResultDisplay.setText(getString(R.string.result_placeholder));
    }
}