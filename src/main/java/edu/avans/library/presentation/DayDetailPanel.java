package edu.avans.library.presentation;

import edu.avans.library.businesslogic.CalendarManager;
import edu.avans.library.domain.Appointment;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The <code>DayDetailPanel</code> ensures the panel in which the details (appointments) of the active day will be placed.
 * It is placed within <code>MainPanel</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see MainPanel
 */
public class DayDetailPanel extends JPanel {
    private Integer day, month, year;
    private Integer dayDetailPanelWidth, dayDetailPanelHeight;
    private MainPanel mainPanel;
    private CalendarManager manager = new CalendarManager();
    private ArrayList<Appointment> appointments;
    private JScrollPane scrollPane;

    /**
     * Constructor. Creates an calendar object and inits the calendar-panel.
     */
    public DayDetailPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        month = mainPanel.mainFrame.calendar.month.getActiveMonth();
        day = mainPanel.mainFrame.calendar.day.getActiveDay();
        year = mainPanel.mainFrame.calendar.year.getActiveYear();
        appointments = manager.getAppointments(mainPanel.mainFrame.calendar.getDate(month,day,year));

        drawDayDetailPanel();
    }

    /**
     * Draws the day-detail panel.
     */
    private void drawDayDetailPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setDayDetailPanelDimensions();
        setDayDetailPanelBounds();

        drawDayLabel();
        drawAppointments();
    }

    /**
     * Updates the day-detail panel dimensions and sets it's new bounds.
     */
    public void resizeDayDetailPanel() {
        setDayDetailPanelDimensions();
        setDayDetailPanelBounds();
    }

    /**
     * Sets the day-detail panel's dimensions.
     */
    private void setDayDetailPanelDimensions() {
        dayDetailPanelWidth = (int) (mainPanel.mainFrame.getContentPane().getWidth() * 0.2);
        dayDetailPanelHeight = mainPanel.mainFrame.getContentPane().getHeight();
    }

    /**
     * Sets the day-detail panel's bounds with the known dimensions.
     */
    private void setDayDetailPanelBounds() {
        setBounds((int) mainPanel.mainFrame.getContentPane().getWidth() - dayDetailPanelWidth, 0, dayDetailPanelWidth, dayDetailPanelHeight);
        //scrollPane.setBounds(0,mainPanel.getTopPanelHeight(),dayDetailPanelWidth, dayDetailPanelHeight - mainPanel.getTopPanelHeight());
    }

    /**
     * Draws the weekday-name and month day heading.
     */
    private void drawDayLabel() {
        String weekDayName = mainPanel.mainFrame.calendar.week.getWeekDayName(mainPanel.mainFrame.calendar.getDate(month, day, year));
        JLabel dayLabel = new JLabel(weekDayName+" "+day);
        dayLabel.setBounds(15,10,dayDetailPanelWidth,50);
        dayLabel.setForeground(Color.decode("#333333"));
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        add(dayLabel);
    }

    /**
     * Redraws the day detail-panel
     */
    public void redrawDayDetailPanel() {
        month = mainPanel.mainFrame.calendar.month.getActiveMonth();
        day = mainPanel.mainFrame.calendar.day.getActiveDay();
        year = mainPanel.mainFrame.calendar.year.getActiveYear();
        appointments = manager.getAppointments(mainPanel.mainFrame.calendar.getDate(month,day,year));

        removeAll();
        drawDayDetailPanel();
        validate();
        repaint();
    }

    /**
     * Draws each appointment with it's contents in the panel.
     */
    private void drawAppointments() {
        JPanel appointmentsPanel = new JPanel();
        appointmentsPanel.setLayout(new BoxLayout(appointmentsPanel, BoxLayout.Y_AXIS));
        appointmentsPanel.setBackground(Color.WHITE);
        appointmentsPanel.setOpaque(true);

        scrollPane = new JScrollPane(appointmentsPanel);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#E2E2E2")));

        // set bounds
        scrollPane.setBounds(0,mainPanel.getTopPanelHeight(),dayDetailPanelWidth, dayDetailPanelHeight - mainPanel.getTopPanelHeight());
        scrollPane.getVerticalScrollBar().setUnitIncrement(25);

        Integer appointmentsSize = appointments.size();

        if(appointmentsSize > 0) {
            for (Integer i = 0; i < appointments.size(); i++) {
                JPanel appointmentPanel = new JPanel();
                appointmentPanel.setLayout(new BoxLayout(appointmentPanel, BoxLayout.Y_AXIS));
                appointmentPanel.setBorder(new EmptyBorder(10, 12, 10, 12));
                appointmentPanel.setOpaque(false);
                Appointment appointment = appointments.get(i);

                Boolean hasLocation = true;
                Boolean hasDescription = true;
                if (appointment.location == null) {
                    hasLocation = false;
                }
                if (appointment.description == null) {
                    hasDescription = false;
                }

                // format times
                String startTime = appointment.startTime.toString();
                startTime = startTime.substring(0, startTime.length() - 3);
                String endTime = appointment.endTime.toString();
                endTime = endTime.substring(0, endTime.length() - 3);

                Border labelBorder = new EmptyBorder(2,0,2,0);
                JLabel time = new JLabel(startTime+" - "+endTime);
                time.setFont(new Font("Arial", Font.PLAIN, 16));
                time.setBorder(new EmptyBorder(0,0,10,0));
                time.setForeground(Color.decode("#333333"));
                JLabel title = new JLabel("Name: "+appointment.title);
                title.setBorder(labelBorder);
                title.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel location = new JLabel("Location: "+appointment.location);
                location.setBorder(labelBorder);
                location.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel description = new JLabel("Notes: "+appointment.description);
                description.setBorder(labelBorder);
                description.setFont(new Font("Arial", Font.PLAIN, 14));

                // delete button
                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(new deleteAppointmentHandler(appointment.appointmentId, appointment.title));
                deleteButton.setMargin(new Insets(6, 32, 6, 32));

                appointmentPanel.add(time);
                appointmentPanel.add(title);
                if (hasLocation) {
                    appointmentPanel.add(location);
                }
                if (hasDescription) {
                    appointmentPanel.add(description);
                }
                appointmentPanel.add(new JLabel(" "));
                appointmentPanel.add(deleteButton);

                appointmentsPanel.add(appointmentPanel);
                //appointmentsPanel.add(new JSeparator());
            }
        }
        else {
            JLabel noResultsLabel = new JLabel("No events found");
            noResultsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            noResultsLabel.setBorder(new EmptyBorder(10, 12, 10, 12));
            appointmentsPanel.add(noResultsLabel);
        }

        add(scrollPane);
    }

    /**
     * Inner class. Triggers an actionlistener when a delete button is clicked.
     */
    class deleteAppointmentHandler implements ActionListener {
        private Integer appointmentId;
        private String appointmentName;

        /**
         * Constructor, stores the appointment ID.
         * @param appointmentId
         */
        public deleteAppointmentHandler(Integer appointmentId, String appointmentName) {
            this.appointmentId = appointmentId;
            this.appointmentName = appointmentName;
        }

        /**
         * Deletes an appointment based on an appointment id
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the event \""+appointmentName+"\"?", "Delete event", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                // delete appointment
                manager.deleteAppointment(appointmentId);
                // redraw panels
                mainPanel.calendarPanel.monthPanel.redrawMonthPanel();
                mainPanel.dayDetailPanel.redrawDayDetailPanel();
            }
        }
    }
}
