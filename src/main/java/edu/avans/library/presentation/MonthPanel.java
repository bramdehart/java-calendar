package edu.avans.library.presentation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The <code>MonthPanel</code> ensures the month user-interface panel.
 * It is placed within <code>CalendarPanel</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see CalendarPanel
 */
public class MonthPanel extends JPanel {
    private static Integer DAYS_IN_MONTH_PANEL = 42;
    private Integer monthPanelWidth, monthPanelHeight;
    private CalendarPanel calendarPanel;
    private ArrayList<DayPanel> dayPanelList = new ArrayList<DayPanel>();

    /**
     * Constructor. Sets the dimensions and content of the month-panel.
     * @param calendarPanel is passed to have access to it's methods and variables.
     */
    public MonthPanel(CalendarPanel calendarPanel) {
        this.calendarPanel = calendarPanel;
        setMonthPanelDimensions();
        setDayPanelList();
        initMonthPanel();
    }

    /**
     * Redraws the month-panel
     */
    public void redrawMonthPanel() {
        emptyDayPanelList();
        setDayPanelList();
        removeAll();
        initDayPanels();

        validate();
        repaint();
    }

    /**
     * Emptys the day panel list
     */
    public void emptyDayPanelList() {
        dayPanelList.clear();
    }

    /**
     * Inits the month-panel.
     */
    public void initMonthPanel() {
        setLayout(new GridLayout(6,7));
        setBackground(Color.decode("#EEEEEE"));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode("#E2E2E2")));
        setMonthPanelBounds();
        initDayPanels();
    }

    /**
     * Resizes the mont-panel. Is called by an <code>resizeListener</code> inside <code>MainPanel</code>.
     */
    public void resizeMonthPanel() {
        setMonthPanelDimensions();
        setMonthPanelBounds();
    }

    /**
     * Sets the month-panel's dimensions.
     */
    private void setMonthPanelDimensions() {
        monthPanelWidth = (int) (calendarPanel.getCalendarPanelWidth() * 0.8);
        monthPanelHeight = calendarPanel.mainPanel.mainFrame.getContentPane().getHeight() - calendarPanel.mainPanel.getTopPanelHeight();
    }

    /**
     * Sets the month-panel's bounds with the known dimensions.
     */
    private void setMonthPanelBounds() {
        setBounds(0, 0, monthPanelWidth, monthPanelHeight);
    }

    /**
     * Gets the width of the month-panel.
     * @return the width of the month-panel
     */
    public Integer getMonthPanelWidth() {
        return monthPanelWidth;
    }

    /**
     * Gets the height of the month-panel.
     * @return the height of the month-panel
     */
    public Integer getMonthPanelHeight() {
        return monthPanelHeight;
    }

    /**
     * Generates a list of days that needs to be displayed in the mont-panel.
     * May include some days of the previous and the next month.
     */
    public void setDayPanelList() {

        // get first weekday of current month
        Integer firstWeekDay = calendarPanel.mainPanel.mainFrame.calendar.month.getFirstWeekDay(
            calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth(),
            calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear()
        );

        // get the number of days to be pre-added, based on the first weekday of the current month
        Integer previousCount = getDaysBefore(firstWeekDay);
        // get the number of days in the active month
        Integer mainCount = calendarPanel.mainPanel.mainFrame.calendar.month.getDayCount(
            calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth(),
            calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear()
        );
        // get the rest number of days to be added
        Integer restCount = getDaysAfter(previousCount, mainCount);

        // get the number of days in the previous month
        if (previousCount > 0) {
            Integer year = calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear();
            Integer month = calendarPanel.mainPanel.mainFrame.calendar.month.getPreviousMonth();
            Integer daysInPreviousMonth = 0;

            if (calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth() == 0) {
                // active month is januari, previous month has also a previous year
                year = calendarPanel.mainPanel.mainFrame.calendar.year.getPreviousYear();
                daysInPreviousMonth = calendarPanel.mainPanel.mainFrame.calendar.month.getDayCount(month, year);
            }
            else {
                daysInPreviousMonth = calendarPanel.mainPanel.mainFrame.calendar.month.getDayCount(month, year);
            }

            // add days of previous month in arraylist
            Integer index = 1;
            for (int day = daysInPreviousMonth - previousCount + 1; day <= daysInPreviousMonth; day++) {
                dayPanelList.add(new DayPanel(day, month, year, calendarPanel, index));
                index ++;
            }
        }

        // add days of active month in arraylist
        for (int day = 1; day <= mainCount; day++) {
            dayPanelList.add(new DayPanel(
                    day,
                    calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth(),
                    calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear(),
                    calendarPanel,
                    previousCount+day
            ));
        }

        // add days of rest month in arraylist
        if (restCount > 0) {
            Integer year = calendarPanel.mainPanel.mainFrame.calendar.year.getActiveYear();
            Integer month = calendarPanel.mainPanel.mainFrame.calendar.month.getNextMonth();
            if (calendarPanel.mainPanel.mainFrame.calendar.month.getActiveMonth() == 11) {
                // active month is december, next month has also a next year
                year = calendarPanel.mainPanel.mainFrame.calendar.year.getNextYear();
            }
            for (int day = 1; day <= restCount; day++) {
                dayPanelList.add(new DayPanel(day, month, year, calendarPanel, previousCount+mainCount+day));
            }
        }
    }

    /**
     * Returns the daypanels of the month-panel view
     * @return list of daypanels
     */
    private ArrayList<DayPanel> getDayPanels() {
        return dayPanelList;
    }


    /**
     * Places every daypanel listed in the daypanel arralist in the monthpanel
     */
    private void initDayPanels() {
        Integer listSize = dayPanelList.size();
        for (int i = 0; i < listSize; i++) {
           add(dayPanelList.get(i));
        }
    }

    /**
     * Calculates the number of days in the previous month that need to be pre-added in the <code>dayPanelList</code>
     * @oaram the start weekday of the current month
     * @return the number of days to be pre-added
     */
    private Integer getDaysBefore(Integer firstWeekDay) {
        return firstWeekDay - 1;
    }

    /**
     * Calculates the number of days in the next month that need to be added in the <code>dayPanelList</code>
     * @param previousMonthDays the number of days of the previous month
     * @param currentMonthDays the number of days of the current month
     * @return the number of days to be added
     */
    private Integer getDaysAfter(Integer previousMonthDays, Integer currentMonthDays) {
        return DAYS_IN_MONTH_PANEL - previousMonthDays - currentMonthDays;
    }

}


