package edu.avans.library.presentation;

import edu.avans.library.businesslogic.CalendarManager;
import edu.avans.library.domain.Appointment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private MainFrame mainFrame;
    private MainPanel mainPanel;
    private CalendarManager manager = new CalendarManager();
    private ArrayList<Appointment> appointments;

    /**
     * Constructor. Creates an calendar object and inits the calendar-panel.
     * @param mainPanel is passed to have access to it's methods.
     */
    public DayDetailPanel(MainPanel mainPanel, Integer month, Integer day, Integer year) {
        mainFrame = mainPanel.mainFrame;
        appointments = manager.getAppointments(mainFrame.calendar.getDate(month,day,year));
        this.mainPanel = mainPanel;
        this.month = month;
        this.day = day;
        this.year = year;

        drawDayDetailPanel();
    }

    /**
     * Draws the day-detail panel.
     */
    private void drawDayDetailPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
        dayDetailPanelWidth = (int) (mainFrame.getContentPane().getWidth() * 0.25);
        dayDetailPanelHeight = mainFrame.getMainFrameHeight();
    }

    /**
     * Sets the day-detail panel's bounds with the known dimensions.
     */
    private void setDayDetailPanelBounds() {
        setBounds((int) (mainFrame.getContentPane().getWidth() * 0.75), 0, dayDetailPanelWidth, dayDetailPanelHeight);
    }

    /**
     * Draws the weekday-name and month day heading.
     */
    private void drawDayLabel() {
        String weekDayName = mainFrame.calendar.week.getWeekDayName(mainFrame.calendar.getDate(month, day, year));
        JLabel dayLabel = new JLabel(weekDayName+" "+day);
        dayLabel.setForeground(Color.decode("#333333"));
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        dayLabel.setBorder(new EmptyBorder(17, 17, 17, 17));
        add(dayLabel);
    }

    /**
     * Redraws the day detail-panel
     */
    public void redrawDayDetailPanel() {
        month = mainFrame.calendar.month.getActiveMonth();
        day = mainFrame.calendar.day.getActiveDay();
        year = mainFrame.calendar.year.getActiveYear();
        appointments = manager.getAppointments(mainFrame.calendar.getDate(month,day,year));

        removeAll();
        drawDayDetailPanel();
        validate();
        repaint();
    }

    private void drawAppointments() {
        System.out.println("komt hier in");

        JPanel appointmentsPanel = new JPanel(new FlowLayout());
        Integer appointmentsSize = appointments.size();

        System.out.println(appointmentsSize);

        if(appointmentsSize > 0) {
            for (Integer i = 0; i < appointmentsSize; i++) {
                Appointment appointment = appointments.get(i);
                JLabel time = new JLabel(appointment.startTime+" - "+appointment.endTime);
                JLabel title = new JLabel(appointment.title);
                JLabel location = new JLabel(appointment.location);
                JLabel description = new JLabel(appointment.description);

                appointmentsPanel.add(time);
                appointmentsPanel.add(time);
                appointmentsPanel.add(location);
                appointmentsPanel.add(description);
            }
        }
        else {
            JLabel testLabel = new JLabel("No events found");
        }
    }
}
