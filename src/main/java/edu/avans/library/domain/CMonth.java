package edu.avans.library.domain;
//import javax.swing.*;
//import java.awt.*;
//import java.time.Month;
import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * <code>CMonth</code> contains methods and variables that are month-related.
 * It is mainly called by <code>CCalendar</code> and the <code>CalendarManager</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see CCalendar
 * @see edu.avans.library.businesslogic.CalendarManager
 */
public class CMonth {
//    private Calendar calendar;
    private static Integer CURRENT_MONTH;
    private Integer activeMonth, prevMonth, nextMonth; // for now private

    /**
     * Contstructor. Sets the global month-variables.
     */
    public CMonth() {
//        this.calendar = calendar;
        setMonths();

        System.out.println(getPreviousMonth());
        System.out.println(getCurrentMonth());
        System.out.println(getNextMonth());
        System.out.println(getMonthName(getPreviousMonth()));
        System.out.println(getMonthName(getCurrentMonth()));
        System.out.println(getMonthName(getNextMonth()));
    }

    /**
     * Sets the global month-variables.
     */
    private void setMonths() {
        setCurrentMonth();
        setActiveMonth(CURRENT_MONTH);
        setPreviousMonth();
        setNextMonth();
    }

    /**
     * Gets the previous month.
     * @return the previous month
     */
    public Integer getPreviousMonth() {
        return prevMonth;
    }

    /**
     * Gets the next month.
     * @return the next month
     */
    public Integer getNextMonth() {
        return nextMonth;
    }

    /**
     * Gets the current month.
     * @return the current month
     */
    public Integer getCurrentMonth() {
        return CURRENT_MONTH;
    }

    /**
     * Gets the active month.
     * @return the active month
     */
    public Integer getActiveMonth() {
        return activeMonth;
    }

    /**
     * Sets the previous month, based on the current month.
     */
    public void setPreviousMonth() {
        prevMonth = activeMonth-1;
    }

    /**
     * Sets the next month, based on the active month.
     */
    public void setNextMonth() {
        nextMonth = activeMonth+1;
    }

    /**
     * Sets the current month.
     */
    public void setCurrentMonth() {
        CURRENT_MONTH = Calendar.getInstance().get(Calendar.MONTH);
    }

    /**
     * Sets the active month.
     * @param month the month that needs to be active
     */
    public void setActiveMonth(Integer month) {
        activeMonth = month;
    }

    /**
     * Gets to month name.
     * @param month the month as an integer (zero-based)
     * @return the month name as a string
     */
    public String getMonthName(Integer month) {
        return new DateFormatSymbols().getMonths()[month];
    }


    /**
     * Gets the sum of days in a given month.
     * @param month the month
     * @param year the year the month is part of
     * @return
     */
    public Integer getDayCount(Integer month, Integer year) {
        //c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return 1;
    }
}
