package edu.avans.library.presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

public class MainPanel extends JPanel {
    private static Integer SIDE_PANEL_WIDTH = 175;
    private static Integer TOP_PANEL_HEIGHT = 75;
    private Integer sidePanelHeight, topPanelWidth;
    private JButton prevMonthButton, nextMonthButton, currentDayButton;
    private JPanel sidePanel, topPanel; // for now private
    private CalendarPanel calendarPanel; // for now private
    public MainFrame mainFrame;

    // TODO: adding month + weekday headings to toppanel

    public MainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        sidePanelHeight = mainFrame.getMainFrameHeight();
        topPanelWidth = mainFrame.getMainFrameWidth() - SIDE_PANEL_WIDTH;
        setLayout(null);
        addComponentListener(new resizeListener());
        drawPanels();
    }

    private void drawPanels() {
        drawSidePanel();
        drawTopPanel();
        drawCalendarPanel();
    }

    private void drawSidePanel() {
        sidePanel = new JPanel();
        // add components
        sidePanel.setBackground(Color.BLUE);
        setSidePanelBounds();
        add(sidePanel);
    }

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
        // TODO: top panel add heading + days
        topPanel.setBackground(Color.PINK);
        setTopPanelBounds();
        add(topPanel);
    }

    private void resizePanels() {
        resizeSidePanel();
        resizeTopPanel();
        calendarPanel.resizeCalendarPanel();
        // TODO: resize month + daypanels
    }

    private void resizeSidePanel() {
        sidePanelHeight = mainFrame.getMainFrameHeight();
        setSidePanelBounds();
    }

    private void resizeTopPanel() {
        topPanelWidth = mainFrame.getMainFrameWidth() - SIDE_PANEL_WIDTH;
        setTopPanelBounds();
    }

    private void drawCalendarPanel() {
        calendarPanel = new CalendarPanel(MainPanel.this);
        add(calendarPanel);
    }

    public Integer getTopPanelWidth() {
        return topPanelWidth;
    }

    public Integer getTopPanelHeight() {
        return TOP_PANEL_HEIGHT;
    }

    public Integer getSidePanelWidth() {
        return SIDE_PANEL_WIDTH;
    }

    public Integer getSidePanelHeight() {
        return sidePanelHeight;
    }

    private void setSidePanelBounds() {
        sidePanel.setBounds(0, 0, SIDE_PANEL_WIDTH, sidePanelHeight);
    }

    private void setTopPanelBounds() {
        topPanel.setBounds(SIDE_PANEL_WIDTH, 0, topPanelWidth, TOP_PANEL_HEIGHT);
    }

    class resizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
            mainFrame.setFrameDimension(true);
            resizePanels();
        }
    }
}
