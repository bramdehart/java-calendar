package edu.avans.library.domain;
import edu.avans.library.businesslogic.CalendarManager;
import java.util.Calendar;

/**
 * <code>CYear</code> contains methods and variables that are year-related.
 * It is mainly called by <code>CCalendar</code> and the <code>CalendarManager</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see CCalendar
 * @see edu.avans.library.businesslogic.CalendarManager
 */
public class CYear {
//    private Calendar calendar;
    private Integer activeYear, prevYear, nextYear, currentYear; // for now private

    /**
     * Constructor. Sets the global year-variables.
     */
    public CYear() {
        //this.calendar = calendar;
        setYears();
    }

    /**
     * Sets the global year-variables.
     */
    private void setYears() {
        setCurrentYear();
        setActiveYear(currentYear);
        setPreviousYear();
        setNextYear();
    }

    /**
     * Gets the previous year, based on the active year.
     * @return the previous year
     */
    public Integer getPreviousYear() {
        return prevYear;
    }

    /**
     * Gets the next year, based on the active year.
     * @return the next year
     */
    public Integer getNextYear() {
        return nextYear;
    }

    /**
     * Gets the current year.
     * @return the current year
     */
    public Integer getCurrentYear() {
        return currentYear;
    }

    /**
     * Gets the active year.
     * @return the active year
     */
    public Integer getActiveYear() {
        return activeYear;
    }

    /**
     * Sets the active year.
     * @param year the year that needs to be active
     */
    public void setActiveYear(Integer year) {
        activeYear = year;
    }

    /**
     * Sets the previous year, based on the active year.
     */
    public void setPreviousYear() {
        prevYear = activeYear-1;
    }

    /**
     * Sets the next year, based on the active year.
     */
    public void setNextYear() {
        nextYear = activeYear+1;
    }

    /**
     * Sets the current year.
     */
    public void setCurrentYear() {
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
    }
}
