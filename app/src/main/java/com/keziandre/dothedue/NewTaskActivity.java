package com.keziandre.dothedue;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import java.util.Calendar;

import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class NewTaskActivity extends AppCompatActivity{

    ImageButton ibSearch;
    ImageButton ibNew;
    ImageButton ibSettings;
    ImageButton ibAddImage;

    Button dateBtn;
    Button timeBtn;
//    Button remindBtn;
    Button addBtn;

    EditText etTitle;
    EditText etDate;
    EditText etTime;
    EditText etLocation;
    EditText etNotes;

    Spinner spinReminder;
    Spinner spinCategory;
    Spinner spinColor;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        // back button on action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ibSearch = (ImageButton) findViewById(R.id.ibNewTask_search);
        ibNew = (ImageButton) findViewById(R.id.ibNewTask_new);
        ibSettings = (ImageButton) findViewById(R.id.ibNewTask_settings);
        ibAddImage = (ImageButton) findViewById(R.id.addimage_button);

        dateBtn = (Button) findViewById(R.id.date_btn);
        timeBtn = (Button) findViewById(R.id.time_btn);
//        remindBtn = (Button) findViewById(R.id.reminder_btn);
        addBtn = (Button) findViewById(R.id.add_button);

        etTitle = (EditText) findViewById(R.id.et_title);
        etDate = (EditText) findViewById(R.id.et_date);
        etTime = (EditText) findViewById(R.id.et_time);
        etLocation = (EditText) findViewById(R.id.et_location);
        etNotes = (EditText) findViewById(R.id.et_notes);

        spinReminder = (Spinner) findViewById(R.id.spin_reminder);
        spinCategory = (Spinner) findViewById(R.id.spin_category);
        spinColor = (Spinner) findViewById(R.id.spin_color);

//        Main Navigation Buttons


        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.putExtra("lastActivity", "NewTaskActivity");

                startActivity(i);
                finish();
            }
        });

        ibNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), NewTaskActivity.class);
                i.putExtra("lastActivity", "NewTaskActivity");

                startActivity(i);
                finish();
            }
        });

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), SettingsActivity.class);
                i.putExtra("lastActivity", "NewTaskActivity");

                startActivity(i);
                finish();
            }
        });

//        New Task Buttons

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(NewTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        String newDate = (monthOfYear+1) + " - " + dayOfMonth + " - " + year;
                        etDate.setText(newDate);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(NewTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                            {
                                String newDate = (monthOfYear+1) + " - " + dayOfMonth + " - " + year;
                                etDate.setText(newDate);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(NewTaskActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                            {
                                etTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(NewTaskActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                            {
                                etTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

//        remindBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RemindDialog rd = new RemindDialog();
//                rd.show(getSupportFragmentManager(), "");
//            }
//        });

        ibAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewTaskActivity.this, "*Open Upload Image Dialog*", Toast.LENGTH_SHORT).show();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Confirmation Dialog
                new AlertDialog.Builder(NewTaskActivity.this)
                        .setTitle("Add Task")
                        .setIcon(R.drawable.add_dialog)
                        .setMessage("Are you done adding task details?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                String title = etTitle.getText().toString();
                                String date = etDate.getText().toString();
                                String time = etTime.getText().toString();
                                String location = etLocation.getText().toString();
                                String notes = etNotes.getText().toString();
                                int reminder = spinReminder.getSelectedItemPosition();
                                int category = spinCategory.getSelectedItemPosition();
                                int color = spinColor.getSelectedItemPosition();

                                Task newTask = new Task(title, date, time, location, notes, reminder, category, color);
                                boolean added = MainActivity.dbHelper.addTask(newTask);

                                if(added)
                                {
                                    Toast.makeText(NewTaskActivity.this, "Task was added Successfully!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                                    i.putExtra("lastActivity", "EditTaskActivity");

                                    startActivity(i);
                                    finish();
                                }
                                else
                                    Toast.makeText(NewTaskActivity.this, "Task was not added", Toast.LENGTH_SHORT).show();

                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        ArrayAdapter<String> interval_adapter = new ArrayAdapter<String>(NewTaskActivity.this, android.R.layout.simple_spinner_dropdown_item, MainActivity.intervals);
        spinReminder.setAdapter(interval_adapter);

        ArrayAdapter<String> category_adapter = new ArrayAdapter<String>(NewTaskActivity.this, android.R.layout.simple_spinner_dropdown_item, MainActivity.categories);
        spinCategory.setAdapter(category_adapter);

        ArrayAdapter<String> color_adapter = new ArrayAdapter<String>(NewTaskActivity.this, android.R.layout.simple_spinner_dropdown_item, MainActivity.colors);
        spinColor.setAdapter(color_adapter);

    }

    // actual back button function
    public boolean onOptionsItemSelected(MenuItem item)
    {
        String lastactivity = getIntent().getExtras().getString("lastActivity");
        Intent myIntent = null;
        try {
            // create task to pass though intent
            final Task currentTask = new Task(getIntent().getStringExtra(Task.COLUMN_TITLE),
                    getIntent().getStringExtra(Task.COLUMN_DATE),
                    getIntent().getStringExtra(Task.COLUMN_TIME),
                    getIntent().getStringExtra(Task.COLUMN_LOCATION),
                    getIntent().getStringExtra(Task.COLUMN_NOTES),
                    getIntent().getExtras().getInt(Task.COLUMN_REMINDER),
                    getIntent().getExtras().getInt(Task.COLUMN_CATEGORY),
                    getIntent().getExtras().getInt(Task.COLUMN_COLOR));

            myIntent = new Intent(getApplicationContext(), Class.forName(MainActivity.PACKAGE_NAME + lastactivity));

            myIntent.putExtra(Task.COLUMN_TITLE, currentTask.getTitle());
            myIntent.putExtra(Task.COLUMN_DATE, currentTask.getDate());
            myIntent.putExtra(Task.COLUMN_TIME, currentTask.getTime());
            myIntent.putExtra(Task.COLUMN_LOCATION, currentTask.getLocation());
            myIntent.putExtra(Task.COLUMN_NOTES, currentTask.getNotes());
            myIntent.putExtra(Task.COLUMN_REMINDER, currentTask.getReminder());
            myIntent.putExtra(Task.COLUMN_CATEGORY, currentTask.getCategory());
            myIntent.putExtra(Task.COLUMN_COLOR, currentTask.getColor());

            // insert confirmation dialog
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        startActivityForResult(myIntent, 0);
        return true;
    }

    // to remove focus on edit texts when clicked outside
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}
