package edu.avans.library.presentation;
//import edu.avans.library.domain.CMonth;
import javax.swing.*;
import java.awt.*;

public class MonthPanel extends JPanel {
    private Integer monthPanelWidth, monthPanelHeight;
    private CalendarPanel calendarPanel;
    private DayPanel dayPanel;
    //private CMonth month;

    public MonthPanel(CalendarPanel calendarPanel) {
        this.calendarPanel = calendarPanel;
        calendarPanel.calendar.month.getCurrentMonth();
        setMonthPanelDimensions();
        initMonthPanel();
    }

    public void initMonthPanel() {
        setLayout(null);
        setBackground(Color.CYAN);
        setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        setMonthPanelBounds();
    }

    public void resizeMonthPanel() {
        setMonthPanelDimensions();
        setMonthPanelBounds();
    }

    private void setMonthPanelDimensions() {
        monthPanelWidth = calendarPanel.getCalendarPanelWidth();
        monthPanelHeight = calendarPanel.getCalendarPanelHeight();
    }

    private void setMonthPanelBounds() {
        setBounds(0, 0, monthPanelWidth, monthPanelHeight);
    }

    public Integer getMonthPanelWidth() {
        return monthPanelWidth;
    }

    public Integer getMonthPanelHeight() {
        return monthPanelHeight;
    }

    private void drawMonthName(Boolean prevMonth, Boolean nextMonth) {
    }
}


