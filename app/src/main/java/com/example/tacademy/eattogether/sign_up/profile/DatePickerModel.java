package com.example.tacademy.eattogether.sign_up.profile;

import java.io.Serializable;

/**
 * Created by Tacademy on 2017-08-09.
 */

public class DatePickerModel implements Serializable{
    int selectedYear, selectedMonth, selectedDay;

    public DatePickerModel() {
    }

    public DatePickerModel(int selectedYear, int selectedMonth, int selectedDay) {
        this.selectedYear = selectedYear;
        this.selectedMonth = selectedMonth;
        this.selectedDay = selectedDay;
    }

    public int getSelectedYear() {

        return selectedYear;
    }

    public void setSelectedYear(int selectedYear) {
        this.selectedYear = selectedYear;
    }

    public int getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(int selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public int getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(int selectedDay) {
        this.selectedDay = selectedDay;
    }
}
