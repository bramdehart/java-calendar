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
    private Calendar calendar;
    private static Integer CURRENT_DAY;
    private Integer activeDay, prevDay, nextDay; // for now private

    public CDay(Calendar calendar) {
        this.calendar = calendar;
        setDays();
    }

    private void setDays() {
        setCurrentDay();
        setActiveDay(CURRENT_DAY);
        setPreviousDay();
        setNextDay();
    }

    public Integer getPreviousDay() {
        return prevDay;
    }

    public Integer getNextDay() {
        return nextDay;
    }

    public Integer getCurrentDay() {
        return CURRENT_DAY;
    }

    public Integer getActiveDay() {
        return activeDay;
    }

    public void setActiveDay(Integer day) {
        activeDay = day;
    }

    public void setPreviousDay() {
        prevDay = activeDay-1;
    }

    public void setNextDay() {
        nextDay = activeDay+1;
    }

    public void setCurrentDay() {
        CURRENT_DAY = this.calendar.get(Calendar.DAY_OF_MONTH);
    }
}
