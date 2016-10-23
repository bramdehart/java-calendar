package edu.avans.library.businesslogic;
import edu.avans.library.datastorage.*;
import edu.avans.library.domain.*;
import edu.avans.library.main.Main;
import edu.avans.library.presentation.*;

/**
 * The <code>CalendarManager</code> manages communication between the different classes in the presentation, domain and data-storage level.
 * @author Bram de Hart
 * @version 1.0
 */


public class CalendarManager {
    /**
     * Gets the active month name.
     * @return the active month name
     */
    public String getActiveMonthName(CCalendar calendar) {
        return calendar.month.getMonthName(calendar.month.getActiveMonth());
    }

    /**
     * Gets the active year.
     * @return the active year
     */
    public Integer getActiveYear(CCalendar calendar) {
        return calendar.year.getActiveYear();
    }

    /**
     * Makes the
     */
    public void nextMonth() {

    }

    public void prevMonth() {

    }
}
