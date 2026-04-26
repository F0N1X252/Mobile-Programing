package com.ubl.mystudentapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private List<Schedule> scheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        RecyclerView rvSchedule = findViewById(R.id.rv_schedule);
        rvSchedule.setLayoutManager(new LinearLayoutManager(this));

        prepareData();

        ScheduleAdapter adapter = new ScheduleAdapter(scheduleList);
        rvSchedule.setAdapter(adapter);
    }

    private void prepareData() {
        scheduleList = new ArrayList<>();
        scheduleList.add(new Schedule("Mobile Programing", "Selasa", "08:00", "10:30", "Lab ICT 11", "Riskiana Wulan, S.Kom, M.Kom", "AI"));
        scheduleList.add(new Schedule("Basis Data", "Selasa", "13:00", "15:30", "Ruang 4.3.1", "Siti Aminah, M.Kom", "BB"));
        scheduleList.add(new Schedule("Jaringan Komputer", "Rabu", "10:40", "13:10", "Lab Jaringan", "Andi Wijaya, S.T", "CC"));
        scheduleList.add(new Schedule("Etika Profesi", "Kamis", "08:00", "09:40", "Ruang 3.2.1", "Drs. Heru Susanto", "AA"));
        scheduleList.add(new Schedule("Sistem Operasi", "Jumat", "14:00", "16:30", "Ruang 5.1.2", "Eka Putri, M.T", "DD"));
    }
}