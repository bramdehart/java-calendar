package edu.avans.library.domain;
import java.util.Calendar;

/**
 * The class <code>CYear</code> contains methods and variables that are year-related.
 * It is mainly called by <code>CCalendar</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see CCalendar
 */
public class CYear {
    private Calendar calendar;
    private static Integer CURRENT_YEAR;
    private Integer activeYear, prevYear, nextYear; // for now private

    public CYear(Calendar calendar) {
        this.calendar = calendar;
        setYears();
    }

    private void setYears() {
        setCurrentYear();
        setActiveYear(CURRENT_YEAR);
        setPreviousYear();
        setNextYear();
    }

    public Integer getPreviousYear() {
        return prevYear;
    }

    public Integer getNextYear() {
        return nextYear;
    }

    public Integer getCurrentYear() {
        return CURRENT_YEAR;
    }

    public Integer getActiveYear() {
        return activeYear;
    }

    public void setActiveYear(Integer year) {
        activeYear = year;
    }

    public void setPreviousYear() {
        prevYear = activeYear-1;
    }

    public void setNextYear() {
        nextYear = activeYear+1;
    }

    public void setCurrentYear() {
        CURRENT_YEAR = this.calendar.get(Calendar.YEAR);
    }
}
