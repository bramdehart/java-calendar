package edu.avans.library.domain;

import java.util.Date;
import java.sql.Time;

/**
 * The class <code>Appointent</code> represents an appointment.
 * They are served via the <code>CalendarManager</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see Category
 * @see edu.avans.library.businesslogic.CalendarManager
 */

public class Appointment {
    public Integer appointmentId;
    public String title, description, location;
    private Date date;
    public Time startTime, endTime;

    /**
     * Constructor. Sets the given variables/
     */
    public Appointment(Integer appointmentId, String title, String description, String location, Date date, Time startTime, Time endTime) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
