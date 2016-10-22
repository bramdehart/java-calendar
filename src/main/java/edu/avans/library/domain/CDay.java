package edu.avans.library.domain;
import java.util.Calendar;

/**
 * <code>CDay</code> contains methods and variables that are day-related.
 * It is mainly called by <code>CCalendar</code> and the <code>CalendarManager</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see CCalendar
 * @see edu.avans.library.businesslogic.CalendarManager
 */
public class CDay {
//    private Calendar calendar;
    private static Integer CURRENT_DAY;
    private Integer activeDay, prevDay, nextDay; // for now private

    /**
     * Constructor. Sets the global day-variables.
     */
    public CDay() {
//        this.calendar = calendar;
        setDays();
    }

    /**
     * Sets the global day-variables.
     */
    private void setDays() {
        setCurrentDay();
        setActiveDay(CURRENT_DAY);
        setPreviousDay();
        setNextDay();
    }

    /**
     * Gets the previous day.
     * @return the previous day
     */
    public Integer getPreviousDay() {
        return prevDay;
    }

    /**
     * Gets the next day.
     * @return the next day
     */
    public Integer getNextDay() {
        return nextDay;
    }

    /**
     * Gets the current day.
     * @return the current day
     */
    public Integer getCurrentDay() {
        return CURRENT_DAY;
    }

    /**
     * Gets the active day.
     * @return the active day
     */
    public Integer getActiveDay() {
        return activeDay;
    }

    /**
     * Sets the active day.
     * @param day the day that needs to be active
     */
    public void setActiveDay(Integer day) {
        activeDay = day;
    }

    /**
     * Sets the previous day, based on the active day.
     */
    public void setPreviousDay() {
        prevDay = activeDay-1;
    }

    /**
     * Sets the next daym based on the active day.
     */
    public void setNextDay() {
        nextDay = activeDay+1;
    }

    /**
     * Sets the current day.
     */
    public void setCurrentDay() {
        CURRENT_DAY = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
}
