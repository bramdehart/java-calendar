package edu.avans.library.businesslogic;
import edu.avans.library.datastorage.*;
import edu.avans.library.domain.Appointment;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * The <code>CalendarManager</code> manages communication between the different classes in the presentation, domain and data-storage level.
 * When <code>CalendarManager</code> is initialized, a database connection is ensured.
 * @author Bram de Hart
 * @version 1.0
 */
public class CalendarManager {
    private AppointmentDAO appointment = new AppointmentDAO();
    private CategoryDAO category = new CategoryDAO();

    /**
     * Gets all appointments of a given date.
     * @param date the date the appointments needs to be retrieved from
     */
    public ArrayList<Appointment> getAppointments(Date date) {
        return appointment.getAppointments(date);
    }

    /**
     * Inserts a new appointment in the database.
     * @param date the date the appointment needs to be added to
     * @param title the title of the appointment
     * @param location the location of the appointment
     * @param description a description of the appointment
     * @param startTime the starttime of the appointment
     * @param endTime the endtime of the appointment
     */
    public boolean addAppointment(Date date, String title, String description, String location, Time startTime, Time endTime) {
        return appointment.addAppointment(date, title, description, location, startTime, endTime);
    }

    /**
     * Deletes an appointment from the database.
     * @param appointmentId the id of the appointment that needs to be removed
     */
    public boolean deleteAppointment(Integer appointmentId) {
        return appointment.deleteAppointment(appointmentId);
    }
}
