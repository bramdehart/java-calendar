package edu.avans.library.domain;
import java.util.Date;

/**
 * The class <code>Appointent</code> represents an appointment.
 * They are served via the <code>CalendarManager</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see Category
 * @see edu.avans.library.businesslogic.CalendarManager
 */

public class Appointment {
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
}
