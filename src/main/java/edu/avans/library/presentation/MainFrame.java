package edu.avans.library.presentation;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;


// sidepanel + buttons on top

public class MainFrame extends JFrame {

    // Gui variabelen knoppen public static
    private static JFrame frame;
    private static JPanel panel;
    private static JButton prevMonth;
    private static JButton nextMonth;
    private static JComboBox yearPicker;
    private static JComboBox monthPicker;
    private static JComboBox dayPicker;

    private static Integer screenWidth;
    private static Integer screenHeight;

    public MainFrame() {
        initFrame();
    }

    public static void initFrame(){
        //get current screendimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();

        frame = new JFrame("Calendar");
        frame.setSize(screenWidth,screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        //paintComponent();
        frame.setVisible(true);

        //JOptionPane.showMessageDialog(null,"hallo dit is een test");
    }

}
