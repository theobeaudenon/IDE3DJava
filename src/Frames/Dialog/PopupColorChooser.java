package Frames.Dialog;

import buttons.CloseButton;
import buttons.ColorButton;
import sun.rmi.runtime.Log;
import utils.CustomTextField;
import utils.SwingUtils;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * Created by Boufle on 06/01/15.
 */
public class PopupColorChooser extends JDialog {

    private JPanel top = new JPanel();
    private JPanel body = new JPanel(new GridBagLayout());
    private CloseButton closeButton = new CloseButton("");

    public JColorChooser getColorChooser() {
        return colorChooser;
    }

    private JColorChooser colorChooser = new JColorChooser();
    private JButton ok = new JButton("OK");
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

                Field f = null;
                try {
                    f = accp.getClass().getDeclaredField("panel");
                } catch (NoSuchFieldException | SecurityException e) {

                }
                f.setAccessible(true);

                Object colorPanel = null;
                try {
                    colorPanel = f.get(accp);
                } catch (IllegalArgumentException | IllegalAccessException e) {

                }

                Field f2 = null;
                try {
                    f2 = colorPanel.getClass().getDeclaredField("spinners");
                } catch (NoSuchFieldException | SecurityException e4) {

                }
                f2.setAccessible(true);
                Object rows = null;
                try {
                    rows = f2.get(colorPanel);
                } catch (IllegalArgumentException | IllegalAccessException e3) {

                }

                final Object transpSlispinner = Array.get(rows, 3);
                Field f3 = null;
                try {
                    f3 = transpSlispinner.getClass().getDeclaredField("slider");
                } catch (NoSuchFieldException | SecurityException e) {

                }
                f3.setAccessible(true);
                JSlider slider = null;
                try {
                    slider = (JSlider) f3.get(transpSlispinner);
                } catch (IllegalArgumentException | IllegalAccessException e2) {

                }
                slider.setVisible(false);
                Field f4 = null;
                try {
                    f4 = transpSlispinner.getClass().getDeclaredField("spinner");
                } catch (NoSuchFieldException | SecurityException e1) {

                }
                f4.setAccessible(true);
                JSpinner spinner = null;
                try {
                    spinner = (JSpinner) f4.get(transpSlispinner);
                } catch (IllegalArgumentException | IllegalAccessException e) {

                }
                spinner.setVisible(false);
                Field f5 = null;
                try {
                    f5 = transpSlispinner.getClass().getDeclaredField("label");
                } catch (NoSuchFieldException | SecurityException e1) {

                }
                f5.setAccessible(true);
                JLabel label = null;
                try {
                    label = (JLabel) f5.get(transpSlispinner);
                } catch (IllegalArgumentException | IllegalAccessException e) {

                }
                label.setVisible(false);

                Field f6 = null;
                try {
                    f6 = transpSlispinner.getClass().getDeclaredField("value");
                } catch (NoSuchFieldException | SecurityException e1) {

                }
                f6.setAccessible(true);
                float value = 0;
                try {
                    value = (float) f6.get(transpSlispinner);
                } catch (IllegalArgumentException | IllegalAccessException e) {

                }


                body.add(accp, c);
            }
        }

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;

        ok.setFocusPainted(false);
        ok.setPreferredSize(new Dimension(100, 35));
        ok.setBorder(BorderFactory.createBevelBorder(1,Color.black,Color.cyan));
        ok.setForeground(Color.WHITE);
        ok.setBackground(new Color(46,46,46));
        ok.setSelected(false);
        body.add(ok,c);
        setVisible(true);




    }




}
