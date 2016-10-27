package edu.avans.library.domain;

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
    private Integer activeMonth, prevMonth, nextMonth, currentMonth; // for now private

    /**
     * Contstructor. Sets the global month-variables.
     */
    public CMonth() {
        setMonths();
    }

    /**
     * Sets the global month-variables.
     */
    private void setMonths() {
        setCurrentMonth();
        setActiveMonth(currentMonth);
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
        return currentMonth;
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
        if (activeMonth > 0) {
            prevMonth = activeMonth-1;
        }
        else {
            prevMonth = 11;
        }
    }

    /**
     * Sets the next month, based on the active month.
     */
    public void setNextMonth() {
        if (activeMonth < 11) {
            nextMonth = activeMonth+1;
        }
        else {
            nextMonth = 0;
        }

    }

    /**
     * Sets the current month.
     */
    public void setCurrentMonth() {
        currentMonth = Calendar.getInstance().get(Calendar.MONTH);
    }

    /**
     * Sets the active month.
     * @param month the month that needs to be active
     */
    public void setActiveMonth(Integer month) {
        if (month > 11) {
            activeMonth = 11;
        }
        else if (month < 0) {
            activeMonth = 0;
        }
        else {
            activeMonth = month;
        }
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
     * @param year the year the month is in
     * @return
     */
    public Integer getDayCount(Integer month, Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Gets the first weekday of the month.
     * @param month the month
     * @param year the year the month is in
     * @return the first weekday of the month, zero based
     */
    public Integer getFirstWeekDay(Integer month, Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
