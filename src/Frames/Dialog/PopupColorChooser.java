package Frames.Dialog;

import buttons.CloseButton;
import buttons.ColorButton;
import utils.CustomTextField;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Boufle on 06/01/15.
 */
public class PopupColorChooser extends JDialog {

    private JPanel top = new JPanel();
    private JPanel body = new JPanel(new GridBagLayout());
    private CloseButton closeButton = new CloseButton("");
    private JColorChooser colorChooser = new JColorChooser();
    private ColorButton ok = new ColorButton("OK");
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
            PopupColorChooser.this.dispose();
        }
    };

    AbstractAction s= new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            PopupColorChooser.this.dispose();

        }
    };
    ActionListener dsd = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            PopupColorChooser.this.dispose();
        }
    };
    public PopupColorChooser(){

        GridBagConstraints c = new GridBagConstraints();

        setModal(true);
        setSize(new Dimension(600, 300));
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

        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if (accp.getDisplayName().equals("RVB")) {
                c.gridx = 0;
                c.gridy = 0;
                c.gridwidth = GridBagConstraints.FIRST_LINE_START;
                accp.setForeground(Color.WHITE);
                accp.setBackground(new Color(60, 63, 65));
                accp.getComponents()[0].setBackground(new Color(60, 63, 65));

                accp.getComponents()[1].setForeground(Color.WHITE);
                accp.getComponents()[1].setFont(new Font("Courier", Font.ITALIC, 21));

                accp.getComponents()[2].setForeground(Color.WHITE);
                accp.getComponents()[2].setBackground(new Color(45, 48, 50));
                accp.getComponents()[2].setFont(new Font("Courier", Font.ITALIC, 21));

                body.add(accp, c);
            }
        }

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;

        ok.setBorder(BorderFactory.createLineBorder(Color.black));
        body.add(ok,c);
        setVisible(true);




    }




}
