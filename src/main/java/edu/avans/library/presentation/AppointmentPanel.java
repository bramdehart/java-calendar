package edu.avans.library.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import edu.avans.library.businesslogic.CalendarManager;

/**
 * The <code>AppointmentPanel</code> ensures the panel of the <code>AppointmentFrame</code>.
 * Its shows the option to add an appointment and is placed within <code>AppointmentFrame</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see AppointmentFrame
 * @see edu.avans.library.domain.Appointment
 */
public class AppointmentPanel extends JPanel {
    private Date date;
    private JFrame appointmentFrame;
    private CalendarPanel calendarPanel;
    private JTextField nameTextField, locationTextField, startTimeTextField, endTimeTextField, notesTextField;
    private Time formattedStartTime, formattedEndTime;
    private CalendarManager manager = new CalendarManager();

    /**
     * Constructor. Sets the global variables and calls the draw method.
     * @param month the month of the clicked daypanel
     * @param day the day of the clicked daypanel
     * @param year the year of the clicked daypanel
     * @param calendarPanel the calendarpanel the clicked daypabel is part of, to have access to its (parents) methods
     */
    public AppointmentPanel(Integer month, Integer day, Integer year, CalendarPanel calendarPanel, JFrame appointmentFrame) {
        this.calendarPanel = calendarPanel;
        this.appointmentFrame = appointmentFrame;
        this.date = calendarPanel.mainPanel.mainFrame.calendar.getDate(month, day, year);

        drawAppointmentPanel();
    }

    /**
     * Draws the appointment panel.
     */
    public void drawAppointmentPanel() {
        setLayout(new SpringLayout());
        String[] labels = {"Name", "Location", "Start time", "End time", "Notes", ""};
        int numPairs = labels.length;

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(200,40));
        saveButton.addActionListener(new saveAppointmentHandler());

        ArrayList<JTextField> textFieldList = listTextFields();

        // fill the panel
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            add(l);
            if (i+1 < numPairs) {
                add(textFieldList.get(i));
            }
            else {
                add(saveButton);
            }
        }

        // lay out the panel
        SpringUtilities.makeCompactGrid(this,
            numPairs, 2, //rows, cols
            10, 10, //initX, initY
            10, 10 //xPad, yPad
        );
    }

    /**
     * List the textfields for use with the for-loop in <code>drawAppointments</code>.
     * @return ArrayList of textfields
     */
    private ArrayList<JTextField> listTextFields() {
        ArrayList<JTextField> textFieldList  = new ArrayList<>();
        textFieldList.add(nameTextField = new JTextField());
        textFieldList.add(locationTextField = new JTextField());
        textFieldList.add(startTimeTextField = new JTextField());
        textFieldList.add(endTimeTextField = new JTextField());
        textFieldList.add(notesTextField = new JTextField());

        return textFieldList;
    }

    /**
     * Shows an message dialog when the name of an event isn't filled in.
     */
    private void showNameError() {
       JOptionPane.showMessageDialog(null, "The name of the event must be filled in.", "Invalid name", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show an message dialog when the filled in times aren't valid.
     */
    private void showTimeError() {
        JOptionPane.showMessageDialog(null,
               "The start time or end time are invalid.\n" +
               "Allowed format: (00 through 23) : (00 through 59).\n" +
               "End time must be greater than start time.", "Invalid times",
       JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows an message dialog when an event is succesfully added.
     * @param name the name of the event.
     */
    private void showSuccesMessage(String name) {
        JOptionPane.showMessageDialog(null, "Your event \""+name+"\" is succesfully added.", "Event added", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Sets the global formatted time variables, based on a time string
     * @param time 4 digit time as a string
     * @param timeType 0 or 1; startTime or endTime
     * @return true or false; validated and setted or not
     */
    private Boolean setFormattedTime(String time, Integer timeType) {
        Boolean validated = true;
        Time formattedTime = new Time(new Date().getTime());

        // format time
        DateFormat formatter = new SimpleDateFormat("HH:mm");

        try {
            new SimpleDateFormat("HH:mm").parse(time);
            // good format
            formattedTime = new Time(formatter.parse(time).getTime());
        } catch (ParseException e) {
            // bad format
            validated = false;
        } finally {
            if (validated) {
                if (timeType == 0) {
                    // start time
                    formattedStartTime = formattedTime;
                }
                else if (timeType == 1){
                    // end time
                    formattedEndTime = formattedTime;
                }
            }
        }

        return validated;
    }

    /**
     * Inner class. Triggers an actionlistener when the <code>addAppointmentButton</code> is clicked.
     */
    class saveAppointmentHandler implements ActionListener {
        /**
         * Opens new frame where a new appointment can be added.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            Boolean validName = true;
            Boolean validTimes = true;

            // get values
            String name = nameTextField.getText();
            String notes = notesTextField.getText();
            String location = locationTextField.getText();
            String startTime = startTimeTextField.getText().replaceAll("\\s+",""); // remove whitespace
            String endTime = endTimeTextField.getText().replaceAll("\\s+",""); // remove whitespace

            // fields to null of not filled in
            if (notes.isEmpty()) { notes = null; }
            if (location.isEmpty()) { location = null; }

            // validate name
            if (name == null || name.isEmpty()) {
                validName = false;
            }
            // validate times
            if (!setFormattedTime(startTime, 0) || !setFormattedTime(endTime, 1)) {
                validTimes = false;
            }
            if (validTimes) {
                // is end time greater then start time
                if (Integer.parseInt(startTime.replaceAll("[^\\d]","")) > Integer.parseInt(endTime.replaceAll("[^\\d]",""))) {
                    validTimes = false;
                }
            }

            if (validName && validTimes) {
                // add appointment
                manager.addAppointment(date, name, notes, location, formattedStartTime, formattedEndTime);
                // close frame
                appointmentFrame.setVisible(false);
                appointmentFrame.dispose();
                // repaint panels and show succes message
                calendarPanel.monthPanel.redrawMonthPanel();
                showSuccesMessage(name);
            }
            else {
                // show errors
                if(!validName) { showNameError(); }
                if(!validTimes) { showTimeError(); }
            }
        }
    }
}
