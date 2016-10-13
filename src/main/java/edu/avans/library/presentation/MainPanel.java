package edu.avans.library.presentation;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;


// sidepanel + buttons on top

public class MainPanel extends JPanel{

    // Gui variabelen knoppen public static
    private static JFrame frame;
    private static JButton prevMonth;
    private static JButton nextMonth;
    private static JComboBox yearPicker;
    private static JComboBox monthPicker;
    private static JComboBox dayPicker;

    // current date
    //private static Calendar currentDate;
    //private static String currentMonth;
    //private static String currentYear();

    private static Integer screenWidth;
    private static Integer screenHeight;

    public MainPanel() {
        initFrame();
    }

    public static void initFrame(){
        //get current screendimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();

        frame = new JFrame("Dit is een test");
        frame.setSize(screenWidth,screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);

        String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox petList = new JComboBox(petStrings);
        petList.setSelectedIndex(4);

        // get current month
        // draw month
        drawMonth();
        //frame.setContentPane();

        //petList.addActionListener(this);

        frame.setVisible(true);

        //JOptionPane.showMessageDialog(null,"hallo dit is een test");
    }



    public static void drawMonth(){
        // draw month interface
    }

}
