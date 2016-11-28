package edu.avans.library.presentation;
import edu.avans.library.businesslogic.CalendarManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The <code>MainPanel</code> ensures the main panel of the calendar application.
 * It contains the toppanel and <code>CalendarPanel</code>.
 * It is placed within <code>MainFrame</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see CalendarPanel
 * @see MainFrame
 */
public class MainPanel extends JPanel {
    private static Integer TOP_PANEL_HEIGHT = 75;
    private Integer topPanelWidth;
    private JButton prevMonthButton, nextMonthButton, currentMonthButton;
    private JPanel topPanel, navigationButtonPanel; // for now private
    public CalendarPanel calendarPanel;
    public DayDetailPanel dayDetailPanel;
    public MainFrame mainFrame;
    private JLabel sundayLabel, mondayLabel, tuesdayLabel, wednesdayLabel, thursdayLabel, fridayLabel, saturdayLabel, monthYearLabel;
    public JTextField dateField;

    /**
     * Constructor. Sets the dimensions and content of the main-panel.
     * @param mainFrame is passed to have access to it's methods and variables.
     */
    public MainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        topPanelWidth = mainFrame.getMainFrameWidth();
        setLayout(null);
        addComponentListener(new resizeListener());
        drawPanels();
    }

    /**
     * Draws the panels that are part of the main-panel.
     */
    private void drawPanels() {
        drawTopPanel();
        drawDayDetailPanel();
        drawCalendarPanel();
    }

    /**
     * Draws the top-panel.
     */
    private void drawTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode("#E2E2E2")));

        // buttons
        prevMonthButton = new JButton("<");
        prevMonthButton.addActionListener(new prevMonthButtonHandler());
        currentMonthButton = new JButton("Today");
        currentMonthButton.addActionListener(new currentMonthButtonHandler());
        nextMonthButton = new JButton(">");
        nextMonthButton.addActionListener(new nextMonthButtonHandler());
        dateField = new JTextField();
        dateField.setHorizontalAlignment(JTextField.CENTER);
        setDateFieldText();
        dateField.addActionListener(new dateFieldHandler());

        navigationButtonPanel = new JPanel();
        navigationButtonPanel.setBackground(Color.WHITE);
        navigationButtonPanel.setLayout(new GridLayout());

        // weekday labels
        sundayLabel = new JLabel("Sun", SwingConstants.RIGHT);
        sundayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        mondayLabel = new JLabel("Mon", SwingConstants.RIGHT);
        mondayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        tuesdayLabel = new JLabel("Tue", SwingConstants.RIGHT);
        tuesdayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        wednesdayLabel = new JLabel("Wed", SwingConstants.RIGHT);
        wednesdayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thursdayLabel = new JLabel("Thu", SwingConstants.RIGHT);
        thursdayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        fridayLabel = new JLabel("Fri", SwingConstants.RIGHT);
        fridayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        saturdayLabel = new JLabel("Sat", SwingConstants.RIGHT);
        saturdayLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // draw the active month and year label
        monthYearLabel = new JLabel();
        monthYearLabel.setForeground(Color.decode("#333333"));
        monthYearLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        setMonthYearLabelText();

        topPanel.setBackground(Color.WHITE);
        setTopPanelBounds();

        // add components
        navigationButtonPanel.add(dateField);
        navigationButtonPanel.add(prevMonthButton);
        navigationButtonPanel.add(currentMonthButton);
        navigationButtonPanel.add(nextMonthButton);
        topPanel.add(navigationButtonPanel);

        topPanel.add(sundayLabel);
        topPanel.add(mondayLabel);
        topPanel.add(tuesdayLabel);
        topPanel.add(wednesdayLabel);
        topPanel.add(thursdayLabel);
        topPanel.add(fridayLabel);
        topPanel.add(saturdayLabel);

        topPanel.add(monthYearLabel);
        add(topPanel);
    }

    /**
     * Resizes the panels. Is called by an <code>resizeListener</code>.
     */
    private void resizePanels() {
        resizeTopPanel();
        calendarPanel.resizeCalendarPanel();
        dayDetailPanel.resizeDayDetailPanel();
    }

    /**
     * Updates the top-panel dimensions and sets it's new bounds.
     */
    private void resizeTopPanel() {
        topPanelWidth = (int) (mainFrame.getContentPane().getWidth() * 0.8);
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
     * Draws the day-detail panel.
     */
    private void drawDayDetailPanel() {
        dayDetailPanel = new DayDetailPanel(MainPanel.this);
        add(dayDetailPanel);
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
     * Sets the top-panel's bounds with the known dimensions.
     */
    private void setTopPanelBounds() {
        topPanel.setBounds(0, 0, topPanelWidth, TOP_PANEL_HEIGHT);
        Integer dayLabelWidth = topPanelWidth / 7;
        sundayLabel.setBounds(-5, 50, dayLabelWidth, 25);
        sundayLabel.setForeground(Color.decode("#BBBBBB"));
        mondayLabel.setBounds(dayLabelWidth-5, 50, dayLabelWidth, 25);
        tuesdayLabel.setBounds(2*dayLabelWidth-5, 50, dayLabelWidth, 25);
        wednesdayLabel.setBounds(3*dayLabelWidth-5, 50, dayLabelWidth, 25);
        thursdayLabel.setBounds(4*dayLabelWidth-5, 50, dayLabelWidth, 25);
        fridayLabel.setBounds(5*dayLabelWidth-5, 50, dayLabelWidth, 25);
        saturdayLabel.setBounds(6*dayLabelWidth-5, 50, dayLabelWidth, 25);
        saturdayLabel.setForeground(Color.decode("#BBBBBB"));
        monthYearLabel.setBounds(15,10,topPanelWidth/2,50);
        navigationButtonPanel.setBounds(topPanelWidth - 455, 12, 450, 30);
    }

    /**
     * Sets the text of <code>monthYearLabel</code> to the active month / year.
     */
    public void setMonthYearLabelText() {
        monthYearLabel.setText(mainFrame.calendar.month.getMonthName(mainFrame.calendar.month.getActiveMonth())+" "+mainFrame.calendar.year.getActiveYear());
    }

    /**
     * Sets the date field with new values.
     */
    public void setDateFieldText() {
        dateField.setText(String.format("%02d",mainFrame.calendar.month.getActiveMonth()+1)+"/"+String.format("%02d",mainFrame.calendar.day.getActiveDay())+"/"+mainFrame.calendar.year.getActiveYear());
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

    /**
     * Inner class. Triggers an actionlistener when previous button is clicked.
     */
    class prevMonthButtonHandler implements ActionListener {
        /**
         * Updates the month to the previous one.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            mainFrame.calendar.toPrevMonth();
            setMonthYearLabelText();
            setDateFieldText();
            calendarPanel.monthPanel.redrawMonthPanel();
            dayDetailPanel.redrawDayDetailPanel();
        }
    }

    /**
     * Inner class. Triggers an actionlistener when next button is clicked.
     */
    class nextMonthButtonHandler implements ActionListener {
        /**
         * Updates the month to the next one.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            mainFrame.calendar.toNextMonth();
            setMonthYearLabelText();
            setDateFieldText();
            calendarPanel.monthPanel.redrawMonthPanel();
            dayDetailPanel.redrawDayDetailPanel();
        }
    }

    /**
     * Inner class. Triggers an actionlistener when current button is clicked.
     */
    class currentMonthButtonHandler implements ActionListener {
        /**
         * Updates the month to the current one.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            mainFrame.calendar.toCurrentMonth();
            setMonthYearLabelText();
            setDateFieldText();
            calendarPanel.monthPanel.redrawMonthPanel();
            dayDetailPanel.redrawDayDetailPanel();
        }
    }

    /**
     * Inner class. Triggers an actionlistener when current datefield is entered.
     */
    class dateFieldHandler implements ActionListener {
        /**
         * Updates the year, month and day to the given one.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            String dateInput = dateField.getText();
            if (dateInput.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
                String[] dateInputs = dateInput.split("/");
                Integer month = Integer.parseInt(dateInputs[0]);
                Integer day = Integer.parseInt(dateInputs[1]);
                Integer year = Integer.parseInt(dateInputs[2]);

                mainFrame.calendar.toDate(month, day, year);
                setMonthYearLabelText();
                calendarPanel.monthPanel.redrawMonthPanel();
                dayDetailPanel.redrawDayDetailPanel();

                validate();
                repaint();
            }
            else {
                // show message dialog
                JOptionPane.showMessageDialog(null,
                    "The entered date invalid.\n" +
                    "Allowed format: mm/dd/yyyy.",
                "Invalid date", JOptionPane.ERROR_MESSAGE);
             }
        }
    }
}
