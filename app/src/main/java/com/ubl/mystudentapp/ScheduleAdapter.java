package com.ubl.mystudentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<Schedule> schedules;

    public ScheduleAdapter(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Schedule schedule = schedules.get(position);
        holder.tvCourseName.setText(schedule.getCourseName());
        holder.tvDayTime.setText(schedule.getDay() + ", " + schedule.getStartTime() + " - " + schedule.getEndTime());
        holder.tvRoomGroup.setText("Ruangan: " + schedule.getRoom() + " | Kelompok: " + schedule.getGroupName());
        holder.tvLecturer.setText("Dosen: " + schedule.getLecturer());
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseName, tvDayTime, tvRoomGroup, tvLecturer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvDayTime = itemView.findViewById(R.id.tv_day_time);
            tvRoomGroup = itemView.findViewById(R.id.tv_room_group);
            tvLecturer = itemView.findViewById(R.id.tv_lecturer);
        }
    }
}