package edu.avans.library.presentation;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JFrame mainFrame;
    public Integer frameWidth, frameHeight;
    private MainPanel mainPanel; // for now private

    public MainFrame() {
        initFrame();
    }

    private void initFrame(){
        mainFrame = new JFrame("Calendar");
        setFrameDimension(false);

        mainFrame.setSize(frameWidth,frameHeight);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setMinimumSize(new Dimension(900, 500));

        // add content to frame
        mainPanel = new MainPanel(MainFrame.this);
        mainFrame.setContentPane(mainPanel);
        mainFrame.setVisible(true);
    }

    public Integer getMainFrameHeight() {
        return frameHeight;
    }

    public Integer getMainFrameWidth() {
        return frameWidth;
    }

    public void setFrameDimension(boolean resized) {
        if (resized) {
            // window is being resized
            Dimension windowSize = mainFrame.getBounds().getSize();
            frameWidth = (int) windowSize.getWidth();
            frameHeight = (int) windowSize.getHeight();
        }
        else {
            // first time startup
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frameWidth = (int) screenSize.getWidth();
            frameHeight = (int) screenSize.getHeight();
        }
    }
}