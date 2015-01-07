package Frames.Dialog;

import buttons.CloseButton;
import buttons.ColorButton;
import utils.CustomTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Boufle on 06/01/15.
 */
public class PopupObjectFrame extends JDialog {

    private JPanel top = new JPanel();
    private JPanel body = new JPanel(new GridLayout(2,1));
    private CloseButton closeButton = new CloseButton("");
    private CustomTextField name = new CustomTextField(20);
    private ColorButton ok = new ColorButton("OK");
    static String Name = null;
    boolean wait = false;
    int posX ;
    int posY ;


    MouseListener ml = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            posX = e.getX();
            posY = e.getY();
        }
    };

    MouseMotionListener m2 = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
            int depX = e.getX() - posX;
            int depY = e.getY() - posY;
            setLocation(getX() + depX, getY() + depY);
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

    };
    AbstractAction d = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            PopupObjectFrame.this.dispose();
        }
    };

    AbstractAction s= new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Name = name.getText();
            PopupObjectFrame.this.dispose();

        }
    };
    ActionListener dsd = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            Name = name.getText();
            PopupObjectFrame.this.dispose();
        }
    };
    public PopupObjectFrame(String nameask){

        name.setPlaceholder(nameask);
        name.setBackground(new Color(45, 48, 50));
        name.setForeground(Color.WHITE);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setCaretColor(Color.WHITE);
        name.setBorder(BorderFactory.createEmptyBorder());
        name.setFont(new Font("Courier", Font.BOLD, 21));
        name.setPreferredSize(this.getSize());
        name.addActionListener(dsd);
        setModal(true);
        setSize(new Dimension(300, 130));
        setUndecorated(true);
        setLocationRelativeTo(this);
        body.setBorder(BorderFactory.createLineBorder(Color.black));
        ok.setBorder(BorderFactory.createLineBorder(Color.black));
        // setAlwaysOnTop(true);
        top.setBackground(new Color(45, 48, 50));
        top.setPreferredSize(new Dimension(300, 40));
        top.setBorder(BorderFactory.createLineBorder(Color.black));
        body.setBackground(new Color(60, 63, 65));
        add(body, BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);
        closeButton.setPreferredSize(new Dimension(20, 20));
        closeButton.addActionListener(d);


        ok.addActionListener(s);
        top.add(closeButton);
        top.addMouseListener(ml);
        top.addMouseMotionListener(m2);
        body.add(name);
        body.add(ok);
        setVisible(true);




    }



    public String getName(){
        return name.getText();
    }
}
