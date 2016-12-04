package edu.avans.library.domain;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <code>CWeek</code> contains methods and variables that are week-related.
 * It is mainly called by <code>CCalendar</code> and the <code>CalendarManager</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see CCalendar
 * @see edu.avans.library.businesslogic.CalendarManager
 */
public class CWeek {
    private Integer activeWeek, prevWeek, nextWeek, currentWeek; // for now private
    private Calendar cal = Calendar.getInstance();

    /**
     * Contstructor. Sets the global month-variables.
     */
    public CWeek() {
        setWeeks();
    }

    /**
     * Sets the global week-variables.
     */
    private void setWeeks() {
        setCurrentWeek();
        setActiveWeek(currentWeek);
        setPreviousWeek();
        setNextWeek();
    }

    /**
     * Gets the previous week.
     * @return the previous week
     */
    public Integer getPreviousWeek() {
        return prevWeek;
    }

    /**
     * Gets the next week.
     * @return the next week
     */
    public Integer getNextWeek() {
        return nextWeek;
    }

    /**
     * Gets the current week.
     * @return the current week
     */
    public Integer getCurrentWeek() {
        return currentWeek;
    }

    /**
     * Gets the active week.
     * @return the active week
     */
    public Integer getActiveWeek() {
        return activeWeek;
    }

    /**
     * Sets the previous week, based on the current week.
     */
    public void setPreviousWeek() {
        prevWeek = activeWeek-1;
    }

    /**
     * Sets the next week, based on the active week.
     */
    public void setNextWeek() {
        nextWeek = activeWeek+1;
    }

    /**
     * Sets the current week.
     */
    public void setCurrentWeek() {
        currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Sets the active week.
     * @param week the week that needs to be active
     */
    public void setActiveWeek(Integer week) {
        activeWeek = week;
    }

    /**
     * Returns the weeknumber based on a given date.
     * @param date the date from which its week number needs to be requested
     * @return the weeknumber of the given date
     */
    public Integer getWeekNumber(Date date) {
        cal.setTime(date);
        int week = cal.get(Calendar.WEEK_OF_YEAR);

        return week;
    }

    /**
     * Gets the weekday name.
     * @param date the date the weekday needs to be retrieved
     * @return the weekday as a string
     */
    public String getWeekDayName(Date date) {
        return new SimpleDateFormat("EEEE").format(date);
    }


}
