package edu.avans.library.domain;
import java.util.Calendar;

/**
 * The class <code>CCalendar</code> is the main class within the domain-level in which other domain-level classes are controlled.
 * It represents the logical side of the calendar application.
 * @author Bram de Hart
 * @version 1.0
 */
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
        //c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return 1;
    }

}
