package com.keziandre.dothedue;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public static final String PACKAGE_NAME = "com.keziandre.dothedue.";

    public static String[] intervals = new String[]{"24 hours", "1 hour", "30 minutes", "15 minutes", "10 minutes", "5 minutes", "1 minute", "0 minutes"}; //for testing purposes
    public static String[] categories = new String[]{"School", "Leisure", "Orgs", "Games", "Family", "Friends"}; //for testing purposes
    public static String[] colors = new String[]{"Red", "Orange", "Yellow", "Green", "Blue", "Violet"}; //for testing purposes


    ImageButton ibSearch;
    ImageButton ibNew;
    ImageButton ibSettings;

    LinearLayout task;

    RecyclerView rvTask;
    public static DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibSearch = (ImageButton) findViewById(R.id.ibHome_search);
        ibNew = (ImageButton) findViewById(R.id.ibHome_new);
        ibSettings = (ImageButton) findViewById(R.id.ibHome_settings);

        task = (LinearLayout) findViewById(R.id.task);

        rvTask = (RecyclerView) findViewById(R.id.rv_task);

        dbHelper = new DatabaseHelper(getBaseContext());
//        dbHelper.addTask(new Task("MOBIDEV", "11 - 15 - 17", "23:59", "GForms", "No Notes", 2, 1, 3));
//        dbHelper.addTask(new Task("SYSDEVE", "11 - 17 - 17", "16:00", "Canvas", "Revise Chapter 6", 1, 3, 2));
//        dbHelper.addTask(new Task("INFOVIS", "11 - 20 - 17", "23:59", "GMail", "Check Midterm Project Guidelines", 3, 2, 1));

        Cursor tasks = dbHelper.getAllTaskCursor();

        TaskAdapter taskAdapter = new TaskAdapter(getBaseContext(), tasks);

        //ONCLICK PER TASK
        taskAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(long t) {
                Intent i = new Intent(getBaseContext(), ViewTaskActivity.class);
                i.putExtra("lastActivity", "MainActivity");

                i.putExtra(Task.COLUMN_ID, t);
                i.putExtra(Task.COLUMN_TITLE, dbHelper.getTask(t).getTitle());
                i.putExtra(Task.COLUMN_DATE, dbHelper.getTask(t).getDate());
                i.putExtra(Task.COLUMN_TIME, dbHelper.getTask(t).getTime());
                i.putExtra(Task.COLUMN_LOCATION, dbHelper.getTask(t).getLocation());
                i.putExtra(Task.COLUMN_NOTES, dbHelper.getTask(t).getNotes());
                i.putExtra(Task.COLUMN_REMINDER, dbHelper.getTask(t).getReminder());
                i.putExtra(Task.COLUMN_CATEGORY, dbHelper.getTask(t).getCategory());
                i.putExtra(Task.COLUMN_COLOR, dbHelper.getTask(t).getColor());

                startActivity(i);
                finish();
            }
        });

        rvTask.setAdapter(taskAdapter);
        rvTask.setLayoutManager(new LinearLayoutManager(getBaseContext()));


//        Main Navigation Buttons

        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.putExtra("lastActivity", "MainActivity");

                startActivity(i);
                finish();
            }
        });

        ibNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), NewTaskActivity.class);
                i.putExtra("lastActivity", "MainActivity");

                startActivity(i);
                finish();
            }
        });

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), SettingsActivity.class);
                i.putExtra("lastActivity", "MainActivity");

                startActivity(i);
                finish();
            }
        });

    }

}
