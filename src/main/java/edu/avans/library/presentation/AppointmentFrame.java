package edu.avans.library.presentation;

import edu.avans.library.businesslogic.CalendarManager;
import javax.swing.*;

/**
 * The <code>AppointmentFrame</code> ensures the window of the popup used to add an appointment.
 * It is called by <code>DayPanel</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see DayPanel
 */
public class AppointmentFrame extends JFrame {
    public Integer frameWidth = 400, frameHeight = 600;
    private  AppointmentPanel appointmentPanel; // for now private
    private CalendarPanel calendarPanel;
    private Integer month, day, year;

    /**
     * Constructor. Calls the initialization of the frame.
     */
    public AppointmentFrame(Integer month, Integer day, Integer year, CalendarPanel calendarPanel) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.calendarPanel = calendarPanel;
        initFrame();
    }

    /**
     * Inits the frame.
     */
    private void initFrame(){
        new JFrame();
        setTitle("Add Event - "+(month+1)+"/"+day+"/"+year);
        setResizable(false);
        setSize(frameWidth,frameHeight);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // add content to frame
        appointmentPanel = new AppointmentPanel(month, day, year, calendarPanel);
        setContentPane(appointmentPanel);
        setVisible(true);
    }
}
