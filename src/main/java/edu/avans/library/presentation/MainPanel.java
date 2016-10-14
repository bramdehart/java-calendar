package edu.avans.library.presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

public class MainPanel extends JPanel {
    private Integer sidebarWidth = 175;
    private Integer sidebarHeight = MainFrame.frameHeight;
    private Integer topbarWidth = MainFrame.frameWidth - sidebarWidth;
    private Integer topbarHeight = 75;
    private static Button prevMonth, nextMonth, currentDay;

    public MainPanel() {
        setLayout(null);
        // Listens on resize
        addComponentListener(new resizeListener());
        // Draw UI bars
        drawSidebar();
        drawTopbar();
    }

    public void drawSidebar() {
    }

    public void drawTopbar() {
        prevMonth = new Button("<");
        currentDay = new Button("Today");
        nextMonth = new Button(">");


        placeTopbarButtons();

        add(prevMonth);
        add(currentDay);
        add(nextMonth);
    }

    public void placeTopbarButtons() {
        prevMonth.setBounds(MainFrame.frameWidth -250, 20, 30, 30);
        currentDay.setBounds(MainFrame.frameWidth -200, 20, 130, 30);
        nextMonth.setBounds(MainFrame.frameWidth -50, 20, 30, 30);
    }

    public void drawCalendar() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, sidebarWidth, sidebarHeight);
        g.setColor(Color.GREEN);
        g.fillRect(sidebarWidth, 0, topbarWidth, topbarHeight);
    }

    class resizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
            MainFrame.setFrameDimension();
            placeTopbarButtons();
            //repaint();
        }
    }
}
