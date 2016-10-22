package edu.avans.library.presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

/**
 * The <code>MainPanel</code> ensures the main panel of the calendar application.
 * It contains the sidepanel, toppanel and <code>CalendarPanel</code>.
 * It is placed within <code>MainFrame</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see CalendarPanel
 * @see MainFrame
 */
public class MainPanel extends JPanel {
    private static Integer SIDE_PANEL_WIDTH = 175;
    private static Integer TOP_PANEL_HEIGHT = 75;
    private Integer sidePanelHeight, topPanelWidth;
    private JButton prevMonthButton, nextMonthButton, currentDayButton;
    private JPanel sidePanel, topPanel; // for now private
    private CalendarPanel calendarPanel; // for now private
    public MainFrame mainFrame;

    // TODO: adding month + weekday headings to toppanel

    /**
     * Constructor. Sets the dimensions and content of the main-panel.
     * @param mainFrame is passed to have access to it's methods and variables.
     */
    public MainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        sidePanelHeight = mainFrame.getMainFrameHeight();
        topPanelWidth = mainFrame.getMainFrameWidth() - SIDE_PANEL_WIDTH;
        setLayout(null);
        addComponentListener(new resizeListener());
        drawPanels();
    }

    /**
     * Draws the panels that are part of the main-panel.
     */
    private void drawPanels() {
        drawSidePanel();
        drawTopPanel();
        drawCalendarPanel();
    }

    /**
     * Draws the side-panel.
     */
    private void drawSidePanel() {
        sidePanel = new JPanel();
        // add components
        sidePanel.setBackground(Color.BLUE);
        setSidePanelBounds();
        add(sidePanel);

        // TODO: add date selector
    }

    /**
     * Draws the top-panel.
     */
    private void drawTopPanel() {
        topPanel = new JPanel();

        // buttons
        prevMonthButton = new JButton("<");
        currentDayButton = new JButton("Today");
        nextMonthButton = new JButton(">");

        // add components
        topPanel.add(prevMonthButton);
        topPanel.add(currentDayButton);
        topPanel.add(nextMonthButton);
        topPanel.setBackground(Color.PINK);
        setTopPanelBounds();

        // draw week days headings


        add(topPanel);
    }

    /**
     * Resizes the panels. Is called by an <code>resizeListener</code>.
     */
    private void resizePanels() {
        resizeSidePanel();
        resizeTopPanel();
        calendarPanel.resizeCalendarPanel();
        // TODO: resize month + daypanels
    }

    /**
     * Updates the side-panel dimensions and sets it's new bounds.
     */
    private void resizeSidePanel() {
        sidePanelHeight = mainFrame.getMainFrameHeight();
        setSidePanelBounds();
    }

    /**
     * Updates the top-panel dimensions and sets it's new bounds.
     */
    private void resizeTopPanel() {
        topPanelWidth = mainFrame.getMainFrameWidth() - SIDE_PANEL_WIDTH;
        setTopPanelBounds();
    }

    /**
     * Draws the calendar-panel.
     */
    private void drawCalendarPanel() {
        calendarPanel = new CalendarPanel(MainPanel.this);
        add(calendarPanel);
    }

    /**
     * Gets the width of the top-panel.
     * @return the width of the top-panel
     */
    public Integer getTopPanelWidth() {
        return topPanelWidth;
    }

    /**
     * Gets the height of the top-panel.
     * @return the height of the top-panel
     */
    public Integer getTopPanelHeight() {
        return TOP_PANEL_HEIGHT;
    }

    /**
     * Gets the width of the side-panel.
     * @return the width of the side-panel
     */
    public Integer getSidePanelWidth() {
        return SIDE_PANEL_WIDTH;
    }

    /**
     * Gets the height of the side-panel.
     * @return the height of the side-panel
     */
    public Integer getSidePanelHeight() {
        return sidePanelHeight;
    }

    /**
     * Sets the side-panel's bounds with the known dimensions.
     */
    private void setSidePanelBounds() {
        sidePanel.setBounds(0, 0, SIDE_PANEL_WIDTH, sidePanelHeight);
    }

    /**
     * Sets the top-panel's bounds with the known dimensions.
     */
    private void setTopPanelBounds() {
        topPanel.setBounds(SIDE_PANEL_WIDTH, 0, topPanelWidth, TOP_PANEL_HEIGHT);
    }

    /**
     * Inner class. Triggers an resizeListener when the window is resized.
     */
    class resizeListener extends ComponentAdapter {
        /**
         * Sets new frame dimensions when the window is resized.
         * @param e
         */
        public void componentResized(ComponentEvent e) {
            mainFrame.setFrameDimension(true);
            resizePanels();
        }
    }
}
