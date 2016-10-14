package edu.avans.library.presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

public class DayPanel extends JPanel {
    private static Integer dayPanelWidth, dayPanelHeight;

    public DayPanel() {
        // initialise dimensions
        dayPanelWidth = CalendarPanel.calendarPanelWidth / 7;
        dayPanelHeight = CalendarPanel.calendarPanelHeight / 6;;
        setLayout(null);
    }
}
