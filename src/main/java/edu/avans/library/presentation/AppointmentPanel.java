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
    private JTextField nameTextField, locationTextField, startTimeHours, startTimeMinutes, endTimeHours, endTimeMinutes, descriptionTextField;
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
        setLayout(new java.awt.GridLayout( 0,1 ));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel nameLabel = new JLabel("Event");
        JLabel startTimeLabel = new JLabel("Start-time");
        JLabel endTimeLabel = new JLabel("End-time");
        JLabel descriptionLabel = new JLabel("Description");
        JLabel locationLabel = new JLabel("Location");

        // define components
        nameTextField = new JTextField();
        locationTextField = new JTextField();
        startTimeHours = new JTextField();
        startTimeHours.setPreferredSize(new Dimension(178, 40));
        startTimeMinutes = new JTextField();
        startTimeMinutes.setPreferredSize(new Dimension(178, 40));
        endTimeHours = new JTextField();
        endTimeHours.setPreferredSize(new Dimension(178, 40));
        endTimeMinutes = new JTextField();
        endTimeMinutes.setPreferredSize(new Dimension(178, 40));
        descriptionTextField = new JTextField();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new saveAppointmentHandler());
        saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel seperatorLabel1 = new JLabel(" : ");
        seperatorLabel1.setPreferredSize(new Dimension(14, 40));
        seperatorLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel seperatorLabel2 = new JLabel(" : ");
        seperatorLabel2.setPreferredSize(new Dimension(14, 40));
        seperatorLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        // make time from panel and add components
        JPanel startTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        startTimePanel.add(startTimeHours);
        startTimePanel.add(seperatorLabel1);
        startTimePanel.add(startTimeMinutes);

        // make time until panel and add components
        JPanel endTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        endTimePanel.add(endTimeHours);
        endTimePanel.add(seperatorLabel2);
        endTimePanel.add(endTimeMinutes);

        // add components
        add(appointmentNameLabel);
        add(nameTextField);

        add(locationLabel);
        add(locationTextField);

        add(startTimeLabel);
        add(startTimePanel);

        add(endTimeLabel);
        add(endTimePanel);

        add(descriptionLabel);
        add(descriptionTextField);

        add(saveButton);
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
            String title = nameTextField.getText();
            String description = descriptionTextField.getText();
            String location = locationTextField.getText();
            Integer startTimeHours2 = Integer.parseInt(startTimeHours.getText());
            Integer startTimeMinutes2 = Integer.parseInt(startTimeMinutes.getText());
            Integer endTimeHours2 = Integer.parseInt(endTimeHours.getText());
            Integer endTimeMinutes2 = Integer.parseInt(endTimeMinutes.getText());
            if (description.isEmpty()) { description = null; }
            if (location.isEmpty()) { location = null; }

            // check input
            if (title == null || title.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The title of the event must be filled in.", "Invalid title", JOptionPane.ERROR_MESSAGE);
            } else if (startTimeHours2 != (int)startTimeHours2 || startTimeMinutes2 != (int)startTimeMinutes2 || startTimeHours2 < 0 || startTimeHours2 > 23 || startTimeMinutes2 < 0 || startTimeMinutes2 > 60) {
                JOptionPane.showMessageDialog(null, "The start time is invalid. Allowed format: (00 through 23) : (00 through 60).", "Invalid start time", JOptionPane.ERROR_MESSAGE);
            } else if (endTimeHours2 != (int)endTimeHours2 || endTimeMinutes2 != (int)endTimeMinutes2 || endTimeHours2 < 0 || endTimeHours2 > 23 || endTimeMinutes2 < 0 || endTimeMinutes2 > 60) {
                JOptionPane.showMessageDialog(null, "The end time is invalid. Allowed format: (00 through 23) : (00 through 60).", "Invalid end time", JOptionPane.ERROR_MESSAGE);
            }
            else {
                Time startTime = null;
                Time endTime = null;
                DateFormat formatter = new SimpleDateFormat("HH:mm");

                // format starttime
                try {
                    startTime = new Time(formatter.parse(startTimeHours2+":"+startTimeMinutes2).getTime());
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

                // format endtime
                try {
                    endTime = new Time(formatter.parse(endTimeHours2+":"+endTimeMinutes2).getTime());
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }

                // add appointment
                manager.addAppointment(date, title, description, location, startTime, endTime);
            }
        }
    }
}
