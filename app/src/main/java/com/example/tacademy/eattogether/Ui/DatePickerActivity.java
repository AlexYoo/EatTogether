package com.example.tacademy.eattogether.Ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.tacademy.eattogether.R;

public class DatePickerActivity extends AppCompatActivity {

    //DatePicker->생년월일 선택
    DatePicker datePicker;
    int selectedYear, selectedMonth, selectedDay;
    Button saveButton;
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        saveButton = (Button)findViewById(R.id.saveButton);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.setSpinnersShown(true);
        datePicker.setCalendarViewShown(false);
        datePicker.init(datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        String msg = String.format("%d / %d / %d", year, monthOfYear + 1, dayOfMonth);
                    }

                });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedYear = datePicker.getYear();
                selectedMonth = datePicker.getMonth();
                selectedDay= datePicker.getDayOfMonth();
                //S.getInstance().log(selectedYear + ", " + selectedMonth + ", " + selectedDay );
                finish();
            }
        });


    }


}


