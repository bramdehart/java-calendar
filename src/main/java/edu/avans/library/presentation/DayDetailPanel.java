package edu.avans.library.presentation;

import edu.avans.library.businesslogic.CalendarManager;
import edu.avans.library.domain.Appointment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.*;
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

    private void drawAppointments() {
        JPanel appointmentsPanel = new JPanel();
        appointmentsPanel.setLayout(new BoxLayout(appointmentsPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(appointmentsPanel);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#E2E2E2")));

        // set bounds
        scrollPane.setBounds(0,mainPanel.getTopPanelHeight(),dayDetailPanelWidth, dayDetailPanelHeight - mainPanel.getTopPanelHeight());
        scrollPane.setOpaque(true);
        scrollPane.getVerticalScrollBar().setUnitIncrement(25);

        Integer appointmentsSize = appointments.size();

        if(appointmentsSize > 0) {
            for (Integer i = 0; i < appointments.size(); i++) {
                JPanel appointmentPanel = new JPanel();
                appointmentPanel.setLayout(new BoxLayout(appointmentPanel, BoxLayout.Y_AXIS));
                appointmentPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

                Appointment appointment = appointments.get(i);

                // format times
                String startTime = appointment.startTime.toString();
                startTime = startTime.substring(0, startTime.length() - 3);
                String endTime = appointment.endTime.toString();
                endTime = endTime.substring(0, endTime.length() - 3);

                JLabel time = new JLabel(startTime+" - "+endTime);
                time.setFont(new Font("Arial", Font.PLAIN, 14));
                time.setBorder(new EmptyBorder(0,0,10,0));
                JLabel title = new JLabel("Name: "+appointment.title);
                title.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel location = new JLabel("Location: "+appointment.location);
                location.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel description = new JLabel("Notes: "+appointment.description);
                description.setFont(new Font("Arial", Font.PLAIN, 14));

                appointmentPanel.add(time);
                appointmentPanel.add(title);
                appointmentPanel.add(location);
                appointmentPanel.add(description);

                appointmentsPanel.add(appointmentPanel);
            }


        }
        else {
            JLabel noResultsLabel = new JLabel("No events found");
            noResultsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            noResultsLabel.setBorder(new EmptyBorder(12, 12, 12, 12));
            appointmentsPanel.add(noResultsLabel);
        }

        add(scrollPane);
    }
}
