package edu.avans.library.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <code>CCalendar</code> represents the logical side of the calendar application.
 * It is mainly called by the <code>CalendarManager</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see edu.avans.library.businesslogic.CalendarManager
 */
public class CCalendar {
    public CYear year;
    public CMonth month;
    public CWeek week;
    public CDay day;

    /**
     * Constructor of the CCalendar object.
     */
    public CCalendar() {
        initCalendar();
    }

    /**
     * Inits a new calendar
     */
    void initCalendar() {
        year = new CYear();
        month = new CMonth();
        week = new CWeek();
        day = new CDay();
    }

    /**
     * Updates the active date by moving one month earlier.
     */
    public void toPrevMonth() {
        day.setActiveDay(1);
        if (month.getActiveMonth() == 0) {
            // active month is januari, set new one to december
            month.setActiveMonth(11);
            // set new year
            year.setActiveYear(year.getPreviousYear());
        }
        else {
            // set previous month to active month
            month.setActiveMonth(month.getPreviousMonth());
        }
        week.setActiveWeek(week.getWeekNumber(getDate(month.getActiveMonth(), day.getActiveDay(), year.getActiveYear())));

        day.setPreviousDay();
        day.setNextDay();
        week.setPreviousWeek();
        week.setNextWeek();
        month.setPreviousMonth();
        month.setNextMonth();
        year.setPreviousYear();
        year.setNextYear();
    }

    /**
     * Updates the active date by moving to the current month.
     */
    public void toCurrentMonth() {
        day.setActiveDay(1);
        day.setPreviousDay();
        day.setNextDay();
        week.setActiveWeek(week.getCurrentWeek());
        week.setPreviousWeek();
        week.setNextWeek();
        month.setActiveMonth(month.getCurrentMonth());
        month.setPreviousMonth();
        month.setNextMonth();
        year.setActiveYear(year.getCurrentYear());
        year.setPreviousYear();
        year.setNextYear();
    }

    /**
     * Updates the active date by moving one month later.
     */
    public void toNextMonth() {
        day.setActiveDay(1);
        if (month.getActiveMonth() == 11) {
            // active month is december, set new one to januari
            month.setActiveMonth(0);
            // set new year
            year.setActiveYear(year.getNextYear());
        }
        else {
            // set next month to active month
            month.setActiveMonth(month.getNextMonth());
        }
        week.setActiveWeek(week.getWeekNumber(getDate(month.getActiveMonth(), day.getActiveDay(), year.getActiveYear())));

        day.setPreviousDay();
        day.setNextDay();
        week.setPreviousWeek();
        week.setNextWeek();
        month.setPreviousMonth();
        month.setNextMonth();
        year.setPreviousYear();
        year.setNextYear();
    }

    /**
     * Updates the active date by overriding month, day, year and week.
     */
    public void toDate(Integer month, Integer day, Integer year) {
        if (month > 12) {
            month = 1;
        }

        this.day.setActiveDay(day);
        this.month.setActiveMonth(month-1);
        this.year.setActiveYear(year);
        this.week.setActiveWeek(this.week.getWeekNumber(getDate(month, day, year)));
        this.day.setPreviousDay();
        this.day.setNextDay();
        this.month.setPreviousMonth();
        this.month.setNextMonth();
        this.year.setPreviousYear();
        this.year.setNextYear();
        this.week.setPreviousWeek();
        this.week.setNextWeek();
    }

    /**
     * Returns the weeknumber based on a given date.
     * @param month the month of the given date
     * @param day the day of the given date
     * @param year the year of the given date
     * @return
     */
    public Date getDate(Integer month, Integer day, Integer year) {
        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
//        System.out.println(month);
//        System.out.println(day);
//        System.out.println(year);

        String dateString = (month+1)+"/"+day+"/"+year;
        Date date = new Date();

        try {
            date = formatter.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}