package edu.avans.library.presentation;

import java.awt.*;

/**
 * Created by Bram on 13/10/2016.
 */
public class PanelUI {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Integer sidePanelWidth = (int) (screenWidth * 0.25); // 25%
        Integer sidePanelHeight = screenHeight;
        g.setColor(Color.BLUE);
        g.fillRect(0,0,0,0);
        g.drawRect(0, 0, sidePanelWidth, sidePanelHeight); // Sidepanel box
    }

    public void drawTopBar() {

    }
}
