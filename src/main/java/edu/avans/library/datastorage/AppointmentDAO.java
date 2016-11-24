package edu.avans.library.datastorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.avans.library.domain.Appointment;

/**
 * <code>AppointmentDAO</code> handles database requests that are related to <code>Appointment</code>.
 * It is called by the <code>CalendarManager</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see edu.avans.library.domain.Appointment
 * @see edu.avans.library.businesslogic.CalendarManager
 */
public class AppointmentDAO {
    private DatabaseConnection connection = new DatabaseConnection("jdbc:mysql://127.0.0.1/javaCalendar", "root","root");
    /**
     * Gets all appointments of a given date.
     * @param date the date the appointments needs to be retrieved from
     */
    public ArrayList<Appointment> getAppointments(Date date) {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);

        if(date != null) {
            // First open a database connnection
            if (connection.open()) {
                // If a connection was successfully setup, execute the SELECT statement.
                ResultSet resultset = connection.executeQuery(
                        "SELECT * FROM appointment WHERE date = '" + dateString + "' ORDER BY startTime;");

                if (resultset != null) {
                    try {
                        while (resultset.next()) {
                            // get fields
                            Integer appointmentId = resultset.getInt("id");
                            String title = resultset.getString("title");
                            String description = resultset.getString("description");
                            String location = resultset.getString("location");
                            Time startTime = resultset.getTime("startTime");
                            Time endTime = resultset.getTime("endTime");

                            // add appointment to list
                            Appointment appointment = new Appointment(appointmentId, title, description, location, date, startTime, endTime);
                            appointments.add(appointment);
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                }

                // We had a database connection opened. Since we're finished,
                // we need to close it.
                connection.close();
            }
        }

        return appointments;
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
    public Boolean addAppointment(Date date, String title, String description, String location, Time startTime, Time endTime) {
        List<Integer> resultIds = new ArrayList<Integer>();

        if (date != null && title != null && date != null && startTime != null && endTime != null) {
            // First open a database connnection
            if (connection.open()) {
                // If a connection was successfully setup, execute the statement.
                resultIds = connection.executePrepared("INSERT INTO appointment (title, description, location, date, startTime, endTime) VALUES(?,?,?,?,?,?);", title,description,location,date,startTime,endTime);
            }

            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.close();
        }
        return resultIds.isEmpty();
    }

    /**
     * Deletes an appointment from the database.
     * @param appointmentId the id of the appointment.
     */
    public boolean deleteAppointment(Integer appointmentId) {
        boolean result = false;
        if (appointmentId != null) {
            // First open a database connnection
            if (connection.open()) {
                // If a connection was successfully setup, execute the statement.
                result = connection.execute("DELETE FROM appointment WHERE appointmentId = '"+appointmentId+"';");
            }

            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.close();
        }
        return result;
    }
}
