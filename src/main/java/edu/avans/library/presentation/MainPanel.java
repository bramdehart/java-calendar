package edu.avans.library.presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

public class MainPanel extends JPanel {
    public static Integer sidePanelWidth, topPanelHeight;
    private static Integer sidePanelHeight, topPanelWidth;
    private static Button prevMonthButton, nextMonthButton, currentDayButton;
    public static JPanel sidePanel, topPanel;
    public static CalendarPanel calendarPanel;

    public MainPanel() {
        // initialise dimensions
        sidePanelWidth = 175;
        sidePanelHeight = MainFrame.frameHeight;
        topPanelWidth = MainFrame.frameWidth - sidePanelWidth;
        topPanelHeight = 75;

        setLayout(null);
        // listen on resize
        addComponentListener(new resizeListener());
        // draw panels
        drawPanels();
    }

    public void drawPanels() {
        drawSidePanel();
        drawTopPanel();
        drawCalendarPanel();
    }

    public void drawSidePanel() {
        sidePanel = new JPanel();

        // add components
        sidePanel.setBackground(Color.BLUE);
        sidePanel.setBounds(0, 0, sidePanelWidth, sidePanelHeight);
        add(sidePanel);
    }

    public void drawTopPanel() {
        topPanel = new JPanel();

        // buttons
        prevMonthButton = new Button("<");
        currentDayButton = new Button("Today");
        nextMonthButton = new Button(">");

        // add components
        topPanel.add(prevMonthButton);
        topPanel.add(currentDayButton);
        topPanel.add(nextMonthButton);
        topPanel.setBackground(Color.PINK);
        topPanel.setBounds(sidePanelWidth, 0, topPanelWidth, topPanelHeight);
        add(topPanel);
    }

    private void resizePanels() {
        resizeSidePanel();
        resizeTopPanel();
        calendarPanel.resizeCalendarPanel();
    }

    private void resizeSidePanel() {
        sidePanelHeight = MainFrame.frameHeight;
        sidePanel.setBounds(0, 0, sidePanelWidth, sidePanelHeight);
    }

    private void resizeTopPanel() {
        topPanelWidth = MainFrame.frameWidth - sidePanelWidth;
        topPanel.setBounds(sidePanelWidth, 0, topPanelWidth, topPanelHeight);
    }

    public void drawCalendarPanel() {
        calendarPanel = new CalendarPanel();
        add(calendarPanel);
    }

    class resizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
            MainFrame.setFrameDimension();
            resizePanels();
        }
    }
}
