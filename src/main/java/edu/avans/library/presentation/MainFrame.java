package edu.avans.library.presentation;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static JFrame frame;
    private static Integer screenWidth;
    private static Integer screenHeight;
    private static MainPanel mainPanel;

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

        // add content to frame
        mainPanel = new MainPanel();
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
}