package edu.avans.library.presentation;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

public class CalendarPanel extends JPanel {
    public static Integer dayBlockCount = 42;
    public static Integer calendarPanelWidth, calendarPanelHeight;
    private static DayPanel dayPanel;

    public CalendarPanel() {
        // initialise dimensions
        calendarPanelWidth = MainFrame.frameWidth - MainPanel.sidePanelWidth;
        calendarPanelHeight = MainFrame.frameHeight - MainPanel.topPanelHeight;
        initCalendarPanel();
    }

    public void initCalendarPanel() {
        // init calendar panel
        setLayout(null);
        setBackground(Color.GREEN);
        setBorder(BorderFactory.createLineBorder(Color.RED));
        setBounds(MainPanel.sidePanelWidth, MainPanel.topPanelHeight, calendarPanelWidth, calendarPanelHeight);

        for (int d=0; d<dayBlockCount; d++) {
            drawDayPanel(d);
        }
    }

    public void resizeCalendarPanel() {
        calendarPanelWidth = MainFrame.frameWidth - MainPanel.sidePanelWidth;
        calendarPanelHeight = MainFrame.frameHeight - MainPanel.topPanelHeight;
        setBounds(MainPanel.sidePanelWidth, MainPanel.topPanelHeight, calendarPanelWidth, calendarPanelHeight);
    }

    /**
     *
     * @param d (dag block)
     */
    public void drawDayPanel(Integer d) {
    }

    public void drawMonth(Date date) {
        // check the month of given date
    }
}


