package Frames;

import buttons.CloseButton;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Boufle on 06/01/15.
 */
public class PopupObjectFrame extends JDialog {

    private JPanel top = new JPanel();
    private CloseButton closeButton = new CloseButton("");

    public PopupObjectFrame(){

        setSize(new Dimension(300,130));
        setUndecorated(true);
        setLocationRelativeTo(this);
        setAlwaysOnTop(true);
        setBackground(new Color(60, 63, 65));
        top.setBackground(new Color(45, 48, 50));
        top.setSize(new Dimension(300,60));
        add(top, BorderLayout.NORTH);
        top.add(closeButton);
        setVisible(true);

    }
}
