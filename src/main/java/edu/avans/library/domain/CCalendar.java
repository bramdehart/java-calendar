package edu.avans.library.domain;
import java.util.Calendar;

public class CCalendar {
    private java.util.Calendar calendar;
    private CMonth month;

    public CCalendar() {
        calendar = Calendar.getInstance();
        month = new CMonth(CCalendar.this);
    }
}
