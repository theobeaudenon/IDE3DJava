package utils.Menu;

import Frames.InternalFrameDemo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * utils.Menu
 * Created by Theo on 04/01/2015 for Ide3DProject.
 */
public class MenuBar {
    public static JMenuBar createMenuBar(JMenuBar menuBar,  InternalFrameDemo internalFrameDemo) {
        menuBar = new JMenuBar();

        menuBar.setForeground(new Color(178, 178, 178));
        menuBar.setBackground(new Color(45,48,50));
        menuBar.setBorder(new LineBorder(Color.BLACK));

        //Set up the lone menu.


        JMenu menue = new JMenu("Fichier");
        menue.setBackground(new Color(45,48,50));
        menue.getPopupMenu().setBorder(BorderFactory.createLineBorder(Color.black));
        menue.setForeground(new Color(178, 178, 178));
        menue.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menue);

        JMenu menu = new JMenu("Fenetres");
        menu.setBackground(new Color(45,48,50));
        menu.getPopupMenu().setBorder(BorderFactory.createLineBorder(Color.black));
        menu.setForeground(new Color(178, 178, 178));
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        JMenu creer = new JMenu("Creer");
        creer.setBackground(new Color(45,48,50));
        creer.getPopupMenu().setBorder(BorderFactory.createLineBorder(Color.black));
        creer.setForeground(new Color(178, 178, 178));
        creer.setMnemonic(KeyEvent.VK_D);

        JMenu aide = new JMenu("Aide");
        aide.setBackground(new Color(45,48,50));
        aide.getPopupMenu().setBorder(BorderFactory.createLineBorder(Color.black));
        aide.setForeground(new Color(178, 178, 178));
        aide.setMnemonic(KeyEvent.VK_D);

        JMenu Objet = new JMenu("Objet");
        Objet.setBackground(new Color(45,48,50));
        Objet.setBorder(BorderFactory.createEmptyBorder());
        Objet.getPopupMenu().setBorder(BorderFactory.createLineBorder(Color.black));
        Objet.setForeground(new Color(178, 178, 178));

        creer.setMnemonic(KeyEvent.VK_D);
        creer.add(Objet);

        menuBar.add(creer);
        menuBar.add(aide);
        menuBar.add(Box.createGlue());



        //Set up the first menu item.
        JMenuItem menuIteme = new JMenuItem("Nouveau / Ouvrir");
        menuIteme.setBackground(new Color(45,48,50));
        menuIteme.setForeground(new Color(178, 178, 178));
        menuIteme.setBorder(BorderFactory.createEmptyBorder());
        menuIteme.setMnemonic(KeyEvent.VK_N);
        menuIteme.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuIteme.setActionCommand("file");
        menuIteme.addActionListener(internalFrameDemo);
        menue.add(menuIteme);
       // menue.add(new JSeparator());

        //Set up the second menu item.
        JMenuItem menuItemes = new JMenuItem("Enregistrer");
        menuItemes.setBackground(new Color(45,48,50));
        menuItemes.setForeground(new Color(178, 178, 178));
        menuItemes.setBorder(BorderFactory.createEmptyBorder());
        menuItemes.setMnemonic(KeyEvent.VK_Q);
        menuItemes.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItemes.setActionCommand("save");
        menuItemes.addActionListener(internalFrameDemo);
        menue.add(menuItemes);

        JMenuItem saveas = new JMenuItem("Enregistrer Sous");
        saveas.setBackground(new Color(45,48,50));
        saveas.setForeground(new Color(178, 178, 178));
        saveas.setBorder(BorderFactory.createEmptyBorder());
        saveas.setMnemonic(KeyEvent.VK_Q);
        saveas.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.ALT_MASK));
        saveas.setActionCommand("saveas");
        saveas.addActionListener(internalFrameDemo);
        menue.add(saveas);
        JSeparator jSeparator = new JSeparator();
        jSeparator.setBorder(BorderFactory.createLineBorder(Color.black));
        menue.add(jSeparator);

        //Set up the second menu item.
        JMenuItem menuItem = new JMenuItem("Quit");
        menuItem.setBackground(new Color(45,48,50));
        menuItem.setForeground(new Color(178, 178, 178));
        menuItem.setBorder(BorderFactory.createEmptyBorder());
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(internalFrameDemo);
        menue.add(menuItem);

        menuItem = new JMenuItem("Project");
        menuItem.setBackground(new Color(45,48,50));
        menuItem.setBorder(BorderFactory.createEmptyBorder());
        menuItem.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("Project");
        menuItem.addActionListener(internalFrameDemo);
        menu.add(menuItem);

        menuItem = new JMenuItem("OPGL");
        menuItem.setBackground(new Color(45,48,50));
        menuItem.setBorder(BorderFactory.createEmptyBorder());
        menuItem.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("OPGL");
        menuItem.addActionListener(internalFrameDemo);
        menu.add(menuItem);

        menuItem = new JMenuItem("Scene");
        menuItem.setBackground(new Color(45,48,50));
        menuItem.setBorder(BorderFactory.createEmptyBorder());
        menuItem.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.CTRL_MASK));
        menuItem.setActionCommand("scene");
        menuItem.addActionListener(internalFrameDemo);
        creer.add(menuItem);


        menuItem = new JMenuItem("Triangle");
        menuItem.setBackground(new Color(45,48,50));
        menuItem.setBorder(BorderFactory.createEmptyBorder());
        menuItem.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("triangle");
        menuItem.addActionListener(internalFrameDemo);
        Objet.add(menuItem);


        menuItem = new JMenuItem("Carré");
        menuItem.setBackground(new Color(45,48,50));
        menuItem.setBorder(BorderFactory.createEmptyBorder());
        menuItem.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("carre");
        menuItem.addActionListener(internalFrameDemo);
        Objet.add(menuItem);

        menuItem = new JMenuItem("Sphere");
        menuItem.setBackground(new Color(45,48,50));
        menuItem.setBorder(BorderFactory.createEmptyBorder());
        menuItem.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("sphere");
        menuItem.addActionListener(internalFrameDemo);
        Objet.add(menuItem);

        menuItem = new JMenuItem("Cylindre");
        menuItem.setBackground(new Color(45,48,50));
        menuItem.setBorder(BorderFactory.createEmptyBorder());
        menuItem.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("cylindre");
        menuItem.addActionListener(internalFrameDemo);
        Objet.add(menuItem);

        menuItem = new JMenuItem("Cone");
        menuItem.setBackground(new Color(45,48,50));
        menuItem.setBorder(BorderFactory.createEmptyBorder());
        menuItem.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("cone");
        menuItem.addActionListener(internalFrameDemo);
        Objet.add(menuItem);






        menuItem = new JMenuItem("A Propos");
        menuItem.setBackground(new Color(45,48,50));
        menuItem.setBorder(BorderFactory.createEmptyBorder());
        menuItem.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("propos");
        menuItem.addActionListener(internalFrameDemo);
        aide.add(menuItem);


        return menuBar;
    }
}
