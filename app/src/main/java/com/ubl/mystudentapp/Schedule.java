package com.ubl.mystudentapp;

public class Schedule {
    private final String courseName;
    private final String day;
    private final String startTime;
    private final String endTime;
    private final String room;
    private final String lecturer;
    private final String groupName;

    public Schedule(String courseName, String day, String startTime, String endTime, String room, String lecturer, String groupName) {
        this.courseName = courseName;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.lecturer = lecturer;
        this.groupName = groupName;
    }

    public String getCourseName() { return courseName; }
    public String getDay() { return day; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getRoom() { return room; }
    public String getLecturer() { return lecturer; }
    public String getGroupName() { return groupName; }
}