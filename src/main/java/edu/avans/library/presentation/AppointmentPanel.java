package edu.avans.library.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import edu.avans.library.businesslogic.CalendarManager;
import edu.avans.library.presentation.SpringUtilities;


/**
 * The <code>AppointmentPanel</code> ensures the panel of the <code>AppointmentFrame</code>.
 * Its shows the option to add an appointment and is placed within <code>AppointmentFrame</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see AppointmentFrame
 * @see edu.avans.library.domain.Appointment
 */
public class AppointmentPanel extends JPanel {
    private Integer month, day, year;
    private Date date;
    private CalendarPanel calendarPanel;
    private JTextField nameTextField, locationTextField, startTimeTextField, endTimeTextField, notesTextField;
    private CalendarManager manager = new CalendarManager();

    /**
     * Constructor. Sets the global variables and calls the draw method.
     * @param month the month of the clicked daypanel
     * @param day the day of the clicked daypanel
     * @param year the year of the clicked datoabek
     * @param calendarPanel the calendarpanel the clicked daypabel is part of, to have access to its (parents) methods
     */
    public AppointmentPanel(Integer month, Integer day, Integer year, CalendarPanel calendarPanel) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.calendarPanel = calendarPanel;
        this.date = calendarPanel.mainPanel.mainFrame.calendar.getDate(month, day, year);

        drawAppointmentPanel();
    }

    /**
     * Draws the appointment panel.
     */
    public void drawAppointmentPanel() {

        // set layout
        setLayout(new SpringLayout());

        // define labels
        String[] labels = {"Name", "Location", "Starttime", "Endtime", "Notes", ""};
        int numPairs = labels.length;

        // button
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(200,40));
        saveButton.addActionListener(new saveAppointmentHandler());

        // fill the panel
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            add(l);

            if (i+1 < numPairs) {
                // textfields
                JTextField textField = new JTextField(10);
                l.setLabelFor(textField);
                add(textField);
            }
            else {
                // button
                add(saveButton);
            }
        }



        // define labels
//        JLabel nameLabel = new JLabel("Name");
//        JLabel locationLabel = new JLabel("Location");
//        JLabel startTimeLabel = new JLabel("Starttime");
//        JLabel endTimeLabel = new JLabel("Endtime");
//        JLabel notesLabel = new JLabel("Notes");

        // define textfields
//        nameTextField = new JTextField();
//        locationTextField = new JTextField();
//        startTimeTextField = new JTextField();
//        endTimeTextField = new JTextField();
//        notesTextField = new JTextField();

        // bind labels / textfields
//        nameLabel.setLabelFor(nameTextField);
//        locationLabel.setLabelFor(locationTextField);
//        startTimeLabel.setLabelFor(startTimeTextField);
//        endTimeLabel.setLabelFor(endTimeTextField);
//        notesLabel.setLabelFor(notesTextField);

        // add labels
//        add(nameLabel);
//        add(locationLabel);
//        add(startTimeLabel);
//        add(endTimeLabel);
//        add(notesLabel);

        // add labels
//        add(nameLabel, nameTextField);
//        add(locationLabel, locationTextField);
//        add(startTimeLabel, startTimeTextField);
//        add(endTimeLabel, endTimeTextField);
//        add(notesLabel, notesTextField);

        // lay out the panel.
        SpringUtilities.makeCompactGrid(this,
            numPairs, 2,   //rows, cols
            10, 10,   //initX, initY
            10, 10    //xPad, yPad
        );

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
            String name = nameTextField.getText();
            String notes = notesTextField.getText();
            String location = locationTextField.getText();
            //Integer startTime = Integer.parseInt(startTime.getText());
            //Integer endTime = Integer.parseInt(endTime.getText());

            if (notes.isEmpty()) { notes = null; }
            if (location.isEmpty()) { location = null; }

//            // check input
//            if (title == null || title.isEmpty()) {
//                JOptionPane.showMessageDialog(null, "The title of the event must be filled in.", "Invalid title", JOptionPane.ERROR_MESSAGE);
//            } else if (startTimeHours2 != (int)startTimeHours2 || startTimeMinutes2 != (int)startTimeMinutes2 || startTimeHours2 < 0 || startTimeHours2 > 23 || startTimeMinutes2 < 0 || startTimeMinutes2 > 60) {
//                JOptionPane.showMessageDialog(null, "The start time is invalid. Allowed format: (00 through 23) : (00 through 60).", "Invalid start time", JOptionPane.ERROR_MESSAGE);
//            } else if (endTimeHours2 != (int)endTimeHours2 || endTimeMinutes2 != (int)endTimeMinutes2 || endTimeHours2 < 0 || endTimeHours2 > 23 || endTimeMinutes2 < 0 || endTimeMinutes2 > 60) {
//                JOptionPane.showMessageDialog(null, "The end time is invalid. Allowed format: (00 through 23) : (00 through 60).", "Invalid end time", JOptionPane.ERROR_MESSAGE);
//            }
//            else {
//                Time startTime = null;
//                Time endTime = null;
//                DateFormat formatter = new SimpleDateFormat("HH:mm");
//
//                // format starttime
//                try {
//                    startTime = new Time(formatter.parse(startTimeHours2+":"+startTimeMinutes2).getTime());
//                } catch (ParseException e1) {
//                    e1.printStackTrace();
//                }
//
//                // format endtime
//                try {
//                    endTime = new Time(formatter.parse(endTimeHours2+":"+endTimeMinutes2).getTime());
//                } catch (ParseException e2) {
//                    e2.printStackTrace();
//                }
//
//                // add appointment
//                manager.addAppointment(date, title, description, location, startTime, endTime);
//            }
        }
    }
}
