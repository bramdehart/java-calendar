package edu.avans.library.domain;
import java.util.Calendar;

/**
 * <code>CCalendar</code> represents the logical side of the calendar application.
 * It is mainly called by the <code>CalendarManager</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see edu.avans.library.businesslogic.CalendarManager
 */
public class CCalendar {
    //private java.util.Calendar calendar;
    public CYear year;
    public CMonth month;
    public CDay day;

    /**
     * Constructor of the CCalendar object.
     */
    public CCalendar() {
        //calendar = Calendar.getInstance();
        year = new CYear();
        month = new CMonth();
        day = new CDay();
    }
}