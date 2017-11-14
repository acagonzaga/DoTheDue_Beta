package com.keziandre.dothedue;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTaskActivity extends AppCompatActivity {

    ImageButton ibSearch;
    ImageButton ibNew;
    ImageButton ibSettings;

    ImageButton editBtn;
    ImageButton deleteBtn;

    TextView tvTitle;
    TextView tvDate;
    TextView tvTime;
    TextView tvLocation;
    TextView tvNotes;
    TextView tvReminder;
    TextView tvCategory;
    TextView tvColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        // back button on action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ibSearch = (ImageButton) findViewById(R.id.ibHome_search);
        ibNew = (ImageButton) findViewById(R.id.ibHome_new);
        ibSettings = (ImageButton) findViewById(R.id.ibHome_settings);

        editBtn = (ImageButton) findViewById(R.id.edit_button);
        deleteBtn = (ImageButton) findViewById(R.id.delete_button);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvLocation = (TextView) findViewById(R.id.tv_location);
        tvNotes = (TextView) findViewById(R.id.tv_notes);
        tvReminder = (TextView) findViewById(R.id.tv_reminder);
        tvCategory = (TextView) findViewById(R.id.tv_category);
        tvColor = (TextView) findViewById(R.id.tv_color);

        // create task to pass though intent
        final Task currentTask = new Task(getIntent().getStringExtra(Task.COLUMN_TITLE),
                getIntent().getStringExtra(Task.COLUMN_DATE),
                getIntent().getStringExtra(Task.COLUMN_TIME),
                getIntent().getStringExtra(Task.COLUMN_LOCATION),
                getIntent().getStringExtra(Task.COLUMN_NOTES),
                getIntent().getExtras().getInt(Task.COLUMN_REMINDER),
                getIntent().getExtras().getInt(Task.COLUMN_CATEGORY),
                getIntent().getExtras().getInt(Task.COLUMN_COLOR));

        currentTask.setId(getIntent().getExtras().getLong(Task.COLUMN_ID));

//      Set data into text Views
        tvTitle.setText(currentTask.getTitle());
        tvDate.setText(currentTask.getDate());
        tvTime.setText(currentTask.getTime());
        tvLocation.setText(currentTask.getLocation());
        tvNotes.setText(currentTask.getNotes());
        tvReminder.setText(MainActivity.intervals[currentTask.getReminder()]);
        tvCategory.setText(MainActivity.categories[currentTask.getCategory()]);
        tvColor.setText(MainActivity.colors[currentTask.getColor()]);

        //        Main Navigation Buttons

        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.putExtra("lastActivity", "ViewTaskActivity");

                startActivity(i);
                finish();
            }
        });

        ibNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), NewTaskActivity.class);
                i.putExtra("lastActivity", "ViewTaskActivity");
                i.putExtra(Task.COLUMN_TITLE, currentTask.getTitle());
                i.putExtra(Task.COLUMN_DATE, currentTask.getDate());
                i.putExtra(Task.COLUMN_TIME, currentTask.getTime());
                i.putExtra(Task.COLUMN_LOCATION, currentTask.getLocation());
                i.putExtra(Task.COLUMN_NOTES, currentTask.getNotes());
                i.putExtra(Task.COLUMN_REMINDER, currentTask.getReminder());
                i.putExtra(Task.COLUMN_CATEGORY, currentTask.getCategory());
                i.putExtra(Task.COLUMN_COLOR, currentTask.getColor());
                i.putExtra(Task.COLUMN_ID, currentTask.getId());

                startActivity(i);
                finish();
            }
        });

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), SettingsActivity.class);
                i.putExtra("lastActivity", "ViewTaskActivity");
                i.putExtra(Task.COLUMN_TITLE, currentTask.getTitle());
                i.putExtra(Task.COLUMN_DATE, currentTask.getDate());
                i.putExtra(Task.COLUMN_TIME, currentTask.getTime());
                i.putExtra(Task.COLUMN_LOCATION, currentTask.getLocation());
                i.putExtra(Task.COLUMN_NOTES, currentTask.getNotes());
                i.putExtra(Task.COLUMN_REMINDER, currentTask.getReminder());
                i.putExtra(Task.COLUMN_CATEGORY, currentTask.getCategory());
                i.putExtra(Task.COLUMN_COLOR, currentTask.getColor());
                i.putExtra(Task.COLUMN_ID, currentTask.getId());

                startActivity(i);
                finish();
            }
        });

        // EDIT AND DELETE

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), EditTaskActivity.class);
                i.putExtra("lastActivity", "ViewTaskActivity");
                i.putExtra(Task.COLUMN_TITLE, currentTask.getTitle());
                i.putExtra(Task.COLUMN_DATE, currentTask.getDate());
                i.putExtra(Task.COLUMN_TIME, currentTask.getTime());
                i.putExtra(Task.COLUMN_LOCATION, currentTask.getLocation());
                i.putExtra(Task.COLUMN_NOTES, currentTask.getNotes());
                i.putExtra(Task.COLUMN_REMINDER, currentTask.getReminder());
                i.putExtra(Task.COLUMN_CATEGORY, currentTask.getCategory());
                i.putExtra(Task.COLUMN_COLOR, currentTask.getColor());
                i.putExtra(Task.COLUMN_ID, currentTask.getId());

                startActivity(i);
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ViewTaskActivity.this)
                        .setTitle("Delete Task")
                        .setIcon(R.drawable.delete_dialog)
                        .setMessage("Are you sure you want to delete this task?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                if (MainActivity.dbHelper.deleteTask(currentTask.getId()))
                                    Toast.makeText(ViewTaskActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getBaseContext(), MainActivity.class);
                                i.putExtra("lastActivity", "ViewTaskActivity");

                                startActivity(i);
                                finish();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }

    // actual back button function
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        startActivity(i);
        finish();
        return true;
    }
}
