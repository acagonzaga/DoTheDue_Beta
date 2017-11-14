package com.keziandre.dothedue;

import java.util.Date;

public class Task {

    public static final String TABLE_NAME = "task";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_REMINDER = "reminder";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_COLOR = "color";

    private long id;
    private String title;
    private String date;
    private String time;
    private String location;
    private String notes;
    private int reminder;
    private int category;
    private int color;

    public Task(String title, String date, String time, String location, String notes, int reminder, int category, int color) {
//    public Task(String title) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.notes = notes;
        this.reminder = reminder;
        this.category = category;
        this.color = color;
    }

    public Task(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getReminder() {
        return reminder;
    }

    public void setReminder(int reminder) {
        this.reminder = reminder;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
