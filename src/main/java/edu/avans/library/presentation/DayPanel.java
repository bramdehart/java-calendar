package edu.avans.library.presentation;

import edu.avans.library.businesslogic.CalendarManager;
import edu.avans.library.domain.Appointment;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * The <code>DayPanel</code> ensures the user-interface panel for <b>each</b> day.
 * It is placed within <code>MonthPanel</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see MonthPanel
 */
public class DayPanel extends JPanel {
    private CalendarPanel calendarPanel;
    public Integer day, month, year, index;
    private JButton viewAppointmentsButton, addAppointmentsButton;
    public ArrayList<Appointment> appointments;
    private Date date;
    private CalendarManager manager = new CalendarManager();

    /**
     * Constructor. Initializes a day-panel.
     * @param day the day number
     * @param month the month number
     * @param year the year number
     * @param calendarPanel the calendarPanel it is part of
     * @param index the panel's index inside the month-panel
     */
    public DayPanel(Integer day, Integer month, Integer year, CalendarPanel calendarPanel, Integer index) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.calendarPanel = calendarPanel;
        this.index = index;
        date = calendarPanel.mainPanel.mainFrame.calendar.getDate(month, day, year);
        appointments = manager.getAppointments(date);
        drawDayPanel();
    }

    /**
     * Draws the day panel.
     */
    private void drawDayPanel() {
        setLayout(new GridLayout(3,1));
        setBackgroundColor();
        setBorder();

        JPanel dayTopPanel = new JPanel();
        dayTopPanel.setBackground(new Color(0,0,0,0));
        dayTopPanel.setLayout(new GridLayout(1,3));

        // week number
        if (index % 7 == 1) {
            // first day of the week, show the weeknumber
            dayTopPanel.add(getStyledWeekNumber(getWeekNumber()));
        }
        else {
            dayTopPanel.add(new JLabel(""));
        }

        // appointments count
        Integer appointmentsCount = manager.getAppointments(date).size();

        if (appointmentsCount > 0) {
            JLabel appointmentsCountLabel = new JLabel(appointmentsCount.toString());
            appointmentsCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
            appointmentsCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
            appointmentsCountLabel.setForeground(Color.WHITE);
            appointmentsCountLabel.setBackground(Color.decode("#FFBE00"));
            appointmentsCountLabel.setOpaque(true);
            dayTopPanel.add(appointmentsCountLabel);
        }
        else {
            dayTopPanel.add(new JLabel(""));
        }

        // day number
        dayTopPanel.add(getStyledDayNumber(getDayNumber()));

        viewAppointmentsButton = new JButton("View");
        viewAppointmentsButton.addActionListener(new viewAppointmentsButtonHandler());
        addAppointmentsButton = new JButton("Add");
        addAppointmentsButton.addActionListener(new addAppointmentsButtonHandler());
        
        viewAppointmentsButton.setOpaque(false);
        addAppointmentsButton.setOpaque(false);

        add(dayTopPanel);
        add(addAppointmentsButton);
        add(viewAppointmentsButton);
    }

    /**
     * Sets the background color based on the week day.
     */
    public void setBackgroundColor() {
        setBackground(Color.WHITE);

        // styling on weekend days
        if (index % 7 == 0 || index % 7 == 1) {
            setBackground(Color.decode("#EEEEEE"));
        }

        // styling if has appointments
        if (!appointments.isEmpty()) {
            setBackground(Color.decode("#55C63C"));
        }

        // current day styling
        if (month == calendarPanel.mainPanel.mainFrame.calendar.month.getCurrentMonth() &&
            day == calendarPanel.mainPanel.mainFrame.calendar.day.getCurrentDay() &&
            year == calendarPanel.mainPanel.mainFrame.calendar.year.getCurrentYear()
        ) {
            setBackground(Color.decode("#EF4A4A"));
        }

        // active day styling
        if (month == calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth() &&
            day == calendarPanel.mainPanel.mainFrame.calendar.day.getActiveDay() &&
            year == calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear()
        ) {
            setBackground(Color.decode("#535F6E"));
        }

        setOpaque(true);
    }

    /**
     * Gets the day number.
     * @return the day number
     */
    public Integer getDayNumber() {
        return day;
    }

    /**
     * Styles the day number based on the month it is part of.
     * @param dayNumber the day number that needs to be styled
     * @return the styled day number as a label
     */
    private JLabel getStyledDayNumber(Integer dayNumber) {
        JLabel dayLabel = new JLabel(this.day.toString());
        dayLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dayLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // days that aren't in the active month
        if (month != calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth()) {
            dayLabel.setForeground(Color.decode("#BBBBBB"));
        }

        // styling if has appointments
        if (!appointments.isEmpty()) {
            //dayLabel.setBackground(Color.decode("#55C63C"));
            dayLabel.setForeground(Color.WHITE);
        }

        // current day styling
        if (month == calendarPanel.mainPanel.mainFrame.calendar.month.getCurrentMonth() &&
            day == calendarPanel.mainPanel.mainFrame.calendar.day.getCurrentDay() &&
            year == calendarPanel.mainPanel.mainFrame.calendar.year.getCurrentYear()
        ) {
            //dayLabel.setBackground(Color.decode("#EF4A4A"));
            dayLabel.setForeground(Color.WHITE);
        }

        // active day styling
        if (month == calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth() &&
                day == calendarPanel.mainPanel.mainFrame.calendar.day.getActiveDay() &&
                year == calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear()
        ) {
            //dayLabel.setBackground(Color.decode("#535F6E"));
            dayLabel.setForeground(Color.WHITE);

        }

        return dayLabel;
    }

    /**
     * Gets the week number the current day is part of.
     * @return the week number
     */
    public Integer getWeekNumber() {
        return calendarPanel.mainPanel.mainFrame.calendar.week.getWeekNumber(calendarPanel.mainPanel.mainFrame.calendar.getDate(month, day, year));
    }

    /**
     * Styles the week number based on the week it is part of.
     * @param weekNumber the week number that needs to be styled
     * @return the styles week number as a label
     */
    private JLabel getStyledWeekNumber(Integer weekNumber) {
        JLabel weekLabel = new JLabel(weekNumber.toString());
        weekLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        weekLabel.setFont(new Font("Arial", Font.BOLD, 16));
        weekLabel.setHorizontalAlignment(SwingConstants.LEFT);
        weekLabel.setForeground(Color.decode("#EF4A4A"));

        // styling if has appointments
        if (!appointments.isEmpty()) {
            //weekLabel.setBackground(Color.decode("#55C63C"));
            weekLabel.setForeground(Color.WHITE);
        }

        // current day styling
        if (month == calendarPanel.mainPanel.mainFrame.calendar.month.getCurrentMonth() &&
            day == calendarPanel.mainPanel.mainFrame.calendar.day.getCurrentDay() &&
            year == calendarPanel.mainPanel.mainFrame.calendar.year.getCurrentYear()
        ) {
            //weekLabel.setBackground(Color.decode("#EF4A4A"));
            weekLabel.setForeground(Color.WHITE);
        }

        // active day styling
        if (month == calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth() &&
            day == calendarPanel.mainPanel.mainFrame.calendar.day.getActiveDay() &&
            year == calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear()
        ) {
            //weekLabel.setBackground(Color.decode("#535F6E"));
            weekLabel.setForeground(Color.WHITE);
        }

        return weekLabel;
    }

    /**
     * Sets the border of a daypanel.
     */
    public void setBorder() {
        setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.decode("#E2E2E2")));
        if (index % 7 == 1) {
            setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#E2E2E2")));
        }
    }

    /**
     * Inner class. Triggers an actionlistener when the <code>viewAppointmentsButton</code> is clicked.
     */
    class viewAppointmentsButtonHandler implements ActionListener {
        /**
         * Updates the active date to the daypanel's one.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            // update active date
            calendarPanel.mainPanel.mainFrame.calendar.toDate(month+1, day, year);

            // redraw panels
            calendarPanel.mainPanel.setMonthYearLabelText();
            calendarPanel.mainPanel.setDateFieldText();
            calendarPanel.monthPanel.redrawMonthPanel();
            calendarPanel.mainPanel.dayDetailPanel.redrawDayDetailPanel();
        }
    }

    /**
     * Inner class. Triggers an actionlistener when the <code>addAppointmentButton</code> is clicked.
     */
    class addAppointmentsButtonHandler implements ActionListener {
        /**
         * Opens new frame where a new appointment can be added.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            new AppointmentFrame(month, day, year, calendarPanel);
        }
    }
}
