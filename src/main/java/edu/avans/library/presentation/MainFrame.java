package edu.avans.library.presentation;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static JFrame frame;
    public static Integer screenWidth, screenHeight, frameWidth, frameHeight;
    public static MainPanel mainPanel;

    public MainFrame() {
        initFrame();
    }

    public static void initFrame(){
        frame = new JFrame("Calendar");
        setScreenDimension();
        frame.setSize(screenWidth,screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(900, 500));

        // add content to frame
        mainPanel = new MainPanel();
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public static void setScreenDimension() {
        //get current screendimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
        frameWidth = screenWidth;
        frameHeight = screenHeight;
    }

    public static void setFrameDimension() {
        // get current frame dimensions
        frameWidth = frame.getWidth();
        frameHeight = frame.getHeight();
    }
}