package Frames;

import classe.Projet;
import utils.ZipFileReader;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.util.*;


public class InternalFrameDemo extends JFrame
        implements ActionListener {
    private final Projet projet;
    JDesktopPane desktop;

    public InternalFrameDemo(Projet finalPro) {
        super("InternalFrameDemo");
        this.projet = finalPro;
        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width  - inset*2,
                screenSize.height - inset*2);

        //Set up the GUI.
        desktop = new JDesktopPane(); //a specialized layered pane
        setContentPane(desktop);
        setJMenuBar(createMenuBar());
        this.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    java.util.List<File> droppedFiles = (java.util.List<File>) evt
                            .getTransferable().getTransferData(
                                    DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {

                         System.out.println(file.getAbsolutePath());
                        Projet pro =null;
                        do {
                            pro = ZipFileReader.read(file.getName(), file.getAbsolutePath());

                        }while (pro == null);

                        final Projet finalPro = pro;
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                JFrame.setDefaultLookAndFeelDecorated(true);

                                //Create and set up the window.
                                InternalFrameDemo frame = new InternalFrameDemo(finalPro);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                                //Display the window.
                                frame.setVisible(true);

                            }

                        });
                    }

                    InternalFrameDemo.this.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        createTreeFrame();
        creatFrameOPGL();


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Fermeture de l'application
                System.exit(0);
            }
        });

        //Make dragging a little faster but perhaps uglier.
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        menuBar.setForeground(new Color(178, 178, 178));
        menuBar.setBackground(new Color(45,48,50));
        menuBar.setBorder(new LineBorder(Color.BLACK));

        //Set up the lone menu.


        JMenu menue = new JMenu("Fichier");
        menue.setBackground(new Color(45,48,50));
        menue.setForeground(new Color(178, 178, 178));
        menue.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menue);

        JMenu menu = new JMenu("Fenetres");
        menu.setBackground(new Color(45,48,50));
        menu.setForeground(new Color(178, 178, 178));
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);


        //Set up the first menu item.
        JMenuItem menuIteme = new JMenuItem("Ouvrir");
        menuIteme.setBackground(new Color(45,48,50));
        menuIteme.setForeground(new Color(178, 178, 178));
        menuIteme.setMnemonic(KeyEvent.VK_N);
        menuIteme.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuIteme.setActionCommand("file");
        menuIteme.addActionListener(this);
        menue.add(menuIteme);

        //Set up the first menu item.
        JMenuItem menuItem = new JMenuItem("Nouveau");
        menuIteme.setBackground(new Color(45,48,50));
        menuIteme.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("new");
        menuItem.addActionListener(this);
        menue.add(menuItem);

        //Set up the second menu item.
        menuItem = new JMenuItem("Quit");
        menuIteme.setBackground(new Color(45,48,50));
        menuIteme.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menue.add(menuItem);

        menuItem = new JMenuItem("Project");
        menuIteme.setBackground(new Color(45,48,50));
        menuIteme.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("Project");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("OPGL");
        menuIteme.setBackground(new Color(45,48,50));
        menuIteme.setForeground(new Color(178, 178, 178));
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("OPGL");
        menuItem.addActionListener(this);
        menu.add(menuItem);



        return menuBar;
    }

    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) { //new
            createFrame();
        }
        else if ("Project".equals(e.getActionCommand())) { //new
            createTreeFrame();
        }
        else if ("OPGL".equals(e.getActionCommand())) { //new
            creatFrameOPGL();
        } /*else if ("file".equals(e.getActionCommand())) { //new
            ZipFileReader.read(this);
        }*/


        else { //quit
            quit();
        }
    }

    //Create a new internal frame.
    protected void createFrame() {
        MyInternalFrame frame = new MyInternalFrame();
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    protected void creatFrameOPGL(){
        CWGOpenGLScreen frame = new CWGOpenGLScreen();
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    protected void createTreeFrame() {
        TreeFrame frame = new TreeFrame(this.projet);
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    //Quit the application.
    protected void quit() {
        System.exit(0);
    }




    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.

    }
}