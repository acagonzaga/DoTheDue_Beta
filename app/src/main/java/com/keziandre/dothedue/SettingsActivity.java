package com.keziandre.dothedue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SettingsActivity extends AppCompatActivity {

    ImageButton ibSearch;
    ImageButton ibNew;
    ImageButton ibSettings;

    ToggleButton tbTime;
    ToggleButton tbSort;
    ToggleButton tbAlert;
    ToggleButton tbLocation;
    ToggleButton tbLocAlerts;
    ToggleButton tbSounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // back button on action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ibSearch = (ImageButton) findViewById(R.id.ibSett_search);
        ibNew = (ImageButton) findViewById(R.id.ibSett_new);
        ibSettings = (ImageButton) findViewById(R.id.ibSett_settings);

        tbTime = (ToggleButton) findViewById(R.id.toggle_time);
        tbSort = (ToggleButton) findViewById(R.id.toggle_sort);
        tbAlert = (ToggleButton) findViewById(R.id.toggle_alert);
        tbLocation = (ToggleButton) findViewById(R.id.toggle_location);
        tbLocAlerts = (ToggleButton) findViewById(R.id.toggle_localerts);
        tbSounds = (ToggleButton) findViewById(R.id.toggle_sounds);

//        Set settings based from Shared Preferences
        SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        tbTime.setChecked(dsp.getBoolean("time", false));
        tbSort.setChecked(dsp.getBoolean("sort", false));
        tbAlert.setChecked(dsp.getBoolean("alert", false));
        tbLocation.setChecked(dsp.getBoolean("location", false));
        tbLocAlerts.setChecked(dsp.getBoolean("locAlerts", false));
        tbSounds.setChecked(dsp.getBoolean("sounds", false));

        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.putExtra("lastActivity", "SettingsActivity");

                startActivity(i);
                finish();
            }
        });

        ibNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), NewTaskActivity.class);
                i.putExtra("lastActivity", "SettingsActivity");

                startActivity(i);
                finish();
            }
        });

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), SettingsActivity.class);
                i.putExtra("lastActivity", "SettingsActivity");

                startActivity(i);
                finish();
            }
        });

//        Settings should be saved in Shared Preferences or something
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void saveSharedPreferences(View view)
    {
        SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor dspEdit = dsp.edit();
        dspEdit.putBoolean("time", tbTime.isChecked());
        dspEdit.putBoolean("sort", tbSort.isChecked());
        dspEdit.putBoolean("alert", tbAlert.isChecked());
        dspEdit.putBoolean("location", tbLocation.isChecked());
        dspEdit.putBoolean("locAlerts", tbLocAlerts.isChecked());
        dspEdit.putBoolean("sounds", tbSounds.isChecked());
        dspEdit.apply();
    }
}
