package edu.avans.library.domain;
import java.util.Calendar;

public class CCalendar {
    private java.util.Calendar calendar;
    public CYear year;
    public CMonth month;
    public CDay day;

    public CCalendar() {
        calendar = Calendar.getInstance();
        year = new CYear(calendar);
        month = new CMonth(calendar);
        day = new CDay(calendar);
    }

    public Integer getDayCount(Integer month, Integer year) {
        return 1;
        //c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}
