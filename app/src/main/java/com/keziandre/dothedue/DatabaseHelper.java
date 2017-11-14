package com.keziandre.dothedue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String SCHEMA = "tasks";
    public static final int VERSION = 4;

    public DatabaseHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
        * CREATE TABLE tasks
        *   (_id INTEGER PRIMARY KEY AUTOINCREMENT,
        *   title TEXT NOT NULL,
        *   date DATE NOT NULL,
        *   time LONG NOT NULL,
        *   location TEXT,
        *   notes TEXT,
        *   reminder INTEGER,
        *   category INTEGER,
        *   color INTEGER);
        */

        String SQL = "CREATE TABLE " + Task.TABLE_NAME + " ( "
                + Task.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Task.COLUMN_TITLE + " TEXT NOT NULL, "
                + Task.COLUMN_DATE + " TEXT NOT NULL, "
                + Task.COLUMN_TIME + " TEXT NOT NULL, "
                + Task.COLUMN_LOCATION + " TEXT, "
                + Task.COLUMN_NOTES + " TEXT, "
                + Task.COLUMN_REMINDER + " INTEGER, "
                + Task.COLUMN_CATEGORY + " INTEGER, "
                + Task.COLUMN_COLOR + " INTEGER);";

        sqLiteDatabase.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + Task.TABLE_NAME + ";";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public boolean addTask(Task t)
    {
        // INSERT INTO tasks (title, date, time, location, notes, reminder, category, color)
        //              VALUES (t.getTitle(), t.getDate(), t.getTime(), t.getLocation()
        //                      t.getNotes(), t.getReminder(), t.getCategory(). t.getColor());

        SQLiteDatabase sqldb = getWritableDatabase();
        ContentValues cv = convertTask(t);

        long id = sqldb.insert(Task.TABLE_NAME, null, cv);
        return (id != -1);
    }

    public Task getTask(long id)
    {
        SQLiteDatabase sqldb = getReadableDatabase();
        Cursor c = sqldb.query(Task.TABLE_NAME, null, Task.COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        Task task = null;
        if(c.moveToFirst())
        {
            task = new Task();
            String title = c.getString(c.getColumnIndex(Task.COLUMN_TITLE));
            String date = c.getString(c.getColumnIndex(Task.COLUMN_DATE));
            String time = c.getString(c.getColumnIndex(Task.COLUMN_TIME));
            String location = c.getString(c.getColumnIndex(Task.COLUMN_LOCATION));
            String notes = c.getString(c.getColumnIndex(Task.COLUMN_NOTES));
            int reminder = c.getInt(c.getColumnIndex(Task.COLUMN_REMINDER));
            int category = c.getInt(c.getColumnIndex(Task.COLUMN_CATEGORY));
            int color = c.getInt(c.getColumnIndex(Task.COLUMN_COLOR));

            task.setTitle(title);
            task.setDate(date);
            task.setTime(time);
            task.setLocation(location);
            task.setNotes(notes);
            task.setReminder(reminder);
            task.setCategory(category);
            task.setColor(color);
            task.setId(id);
        }
        c.close(); // close cursor
        sqldb.close(); // close db

        return task;
    }

    public void editTask(Task newTaskDetails, long id)
    {
        SQLiteDatabase sqldb = getWritableDatabase();
        ContentValues cv = convertTask(newTaskDetails);
        sqldb.update(Task.TABLE_NAME, cv, Task.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        sqldb.close();
    }

    public boolean deleteTask(long id)
    {
        SQLiteDatabase sqldb = getWritableDatabase();
        return sqldb.delete(Task.TABLE_NAME, Task.COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public Cursor getAllTaskCursor()
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Task.TABLE_NAME, null, null, null, null, null, null);
    }

    public ContentValues convertTask(Task t)
    {
        ContentValues cv = new ContentValues();
        cv.put(Task.COLUMN_TITLE, t.getTitle());
        cv.put(Task.COLUMN_DATE, t.getDate());
        cv.put(Task.COLUMN_TIME, t.getTime());
        cv.put(Task.COLUMN_LOCATION, t.getLocation());
        cv.put(Task.COLUMN_NOTES, t.getNotes());
        cv.put(Task.COLUMN_REMINDER, t.getReminder());
        cv.put(Task.COLUMN_CATEGORY, t.getCategory());
        cv.put(Task.COLUMN_COLOR, t.getColor());

        return cv;
    }
}
