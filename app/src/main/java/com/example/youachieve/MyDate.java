package com.example.youachieve;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

public class MyDate extends Date {

    public MyDate(long time) {
        super(time);
    }

    public MyDate(Date date) {
        super(date.getYear(), date.getMonth(), date.getDate(),
                date.getHours(), date.getMinutes(), date.getSeconds());
    }

    public String getMonthString() {
        switch (this.getMonth()) {
            case Calendar.JANUARY:
                return "января";
            case Calendar.FEBRUARY:
                return "февраля";
            case Calendar.MARCH:
                return "марта";
            case Calendar.APRIL:
                return "апреля";
            case Calendar.MAY:
                return "мая";
            case Calendar.JUNE:
                return "июня";
            case Calendar.JULY:
                return "июля";
            case Calendar.AUGUST:
                return "августа";
            case Calendar.SEPTEMBER:
                return "сентября";
            case Calendar.OCTOBER:
                return "октября";
            case Calendar.NOVEMBER:
                return "ноября";
            case Calendar.DECEMBER:
                return "декабря";
            default:
                return "none";
        }
    };

    @NonNull
    public String getTimeString() {
        return String.format("%02d", this.getHours()) + ":" + String.format("%02d", this.getMinutes());
    }

    @NonNull
    public String toString() {
        Date dateNow = new Date();
        String res;

        int monthNow = dateNow.getDate();
        int month = this.getDate();
        if (month == monthNow)
            res = "сегодня";
        else if (month == monthNow - 1)
            res = "вчера";
        else if (month == monthNow + 1)
            res = "завтра";
        else
            res = String.valueOf(this.getDate()) + " " + this.getMonthString();

        int yearNow = dateNow.getYear();
        int year = this.getYear();
        if (year != yearNow)
            res += " " + String.valueOf(this.getYear());

        res += " в " + this.getTimeString();

        return res;
    }
}
