package edu.avans.library.presentation;
//import edu.avans.library.domain.CMonth;
import edu.avans.library.businesslogic.CalendarManager;

import javax.swing.*;
import java.awt.*;

/**
 * The <code>MonthPanel</code> ensures the month user-interface panel.
 * It is placed within <code>CalendarPanel</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see CalendarPanel
 */
public class MonthPanel extends JPanel {
    private Integer monthPanelWidth, monthPanelHeight;
    private CalendarPanel calendarPanel;
    private DayPanel dayPanel;
    private CalendarManager manager;

    /**
     * Constructor. Sets the dimensions and content of the month-panel.
     * @param calendarPanel is passed to have access to it's methods and variables.
     */
    public MonthPanel(CalendarPanel calendarPanel) {
        this.calendarPanel = calendarPanel;
        setMonthPanelDimensions();
        initMonthPanel();
    }

    /**
     * Inits the month-panel.
     */
    public void initMonthPanel() {
        setLayout(null);
        setBackground(Color.CYAN);
        setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        setMonthPanelBounds();
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
        monthPanelWidth = calendarPanel.getCalendarPanelWidth();
        monthPanelHeight = calendarPanel.getCalendarPanelHeight();
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

}


