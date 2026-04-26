package com.ubl.mystudentapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvClock, tvDate;
    private final Handler timeHandler = new Handler(Looper.getMainLooper());
    private final Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            updateTime();
            timeHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvClock = findViewById(R.id.tv_realtime_clock);
        tvDate = findViewById(R.id.tv_current_date);
        Button btnViewSchedule = findViewById(R.id.btn_view_schedule);
        Button btnLogout = findViewById(R.id.btn_logout);

        btnViewSchedule.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ScheduleActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> logout());

        updateTime();
        updateDate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timeHandler.post(timeRunnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timeHandler.removeCallbacks(timeRunnable);
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        tvClock.setText(currentTime);
    }

    private void updateDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d MMMM yyyy", new Locale("id", "ID"));
        String currentDate = sdf.format(Calendar.getInstance().getTime());
        tvDate.setText(currentDate);
    }

    private void logout() {
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}