package com.example.tacademy.eattogether.Ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.tacademy.eattogether.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ScheduleActivity extends AppCompatActivity
        implements CalendarView.OnDateChangeListener {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    CalendarView widget;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        widget = (CalendarView) findViewById(R.id.calendarView);
        textView = (TextView)findViewById(R.id.textView);
        textView.setText("No Selection");

        widget.setOnDateChangeListener(this);
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month,
                                    int dayOfMonth) {
        textView.setText(FORMATTER.format(view.getDate()));
    }
}
