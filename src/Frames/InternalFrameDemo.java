package Frames;

import Frames.FixedFrame.InspecteurLeBlanco;
import Frames.FixedFrame.TreeFrame;
import Frames.OpenGl.OBJOpenGLScreen;
import Frames.OpenGl.SCENEOpenGLScreen;
import classe.Forme;
import classe.Projet;
import classe.Scene;
import utils.Menu.MenuAction;
import utils.ProjectExport.ProjectFileSaver;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class InternalFrameDemo extends JFrame implements ActionListener {
    private final Projet projet;
    JDesktopPane desktop;
    private JMenuItem menuItemelog ;
    private JMenuBar menuBar;
    private TreeFrame frame;
    private InspecteurLeBlanco inspeframe;

    public InternalFrameDemo(Projet finalPro) {
        super("Editeur 3D SUPINFO");
        this.projet = finalPro;
        //setUndecorated(true);
        //Make the big window be indented 50 pixels from each edge
        //of the screen.

        try {
            BufferedImage img = ImageIO.read(new File("res\\icon\\main.png"));
            this.setIconImage(img);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int gameHeight = (int) (Math.round(screenSize.width * 0.95));
        int gameWidth = (int) (Math.round( screenSize.height * 0.85));
        int inset = 50;
        setBounds(inset, inset, gameHeight ,gameWidth );

        /**
         *  Set up the GUI.
         */
        desktop = new JDesktopPane(); //a specialized layered pane
        setContentPane(desktop);

        /**
         * BARRE DU HAUT
         */
        desktop.setBackground(new Color(68, 68, 68));
        menuBar = utils.Menu.MenuBar.createMenuBar(menuBar, this);
        menuItemelog = new JMenuItem("                                                                                                                                         Informations  ");
        menuItemelog.setBackground(new Color(68, 68, 68));
        menuItemelog.setForeground(new Color(255, 163, 79));
        menuItemelog.setHorizontalAlignment(SwingConstants.CENTER);

        menuBar.add(menuItemelog);

        menuBar.add(Box.createGlue());
        setJMenuBar(menuBar);
        /**
         * DROP LISTNER
         */
        utils.Menu.DropListener.Drop(this);

        /**
         * OUVERTURE MENU & INSPECTEUR
         */
        createInspecteur();
        createTreeFrame();
       // creatFrameOPGL(obj.getUserObject());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            //    JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "Voulez vous sauvegarder le projet ? ", "Oui",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {
                    System.exit(10);
                } else if (response == JOptionPane.YES_OPTION) {
                    ProjectFileSaver.save(projet,null, 1);
                    System.exit(20);
                } else if (response == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }
                // Fermeture de l'application

            }
        });

        //Make dragging a little faster but perhaps uglier.
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }


    public void log(String log){
        System.out.println(log);
        menuItemelog.setText("                                                                                                Informations : "+log);
         //menuItemelog.setEnabled(false);
        menuItemelog.setBackground(new Color(68, 68, 68));
        menuItemelog.setForeground(new Color(255, 163, 79));
        menuItemelog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemelog.setText("                                                                                                                                         Informations  ");
            }
        });
        menuBar.updateUI();
    }



    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        MenuAction.actionPerformed(e, this);
    }

    public void welcomescreen() {
        WelcomeFrame frame = new WelcomeFrame();
        frame.setVisible(true);
    }


    public void creatFrameOPGL(Object userObject){
        try {
            Forme f = (Forme)userObject;
            this.log("Ouverture d'une Frame OpenGL : " + f.getName());
            OBJOpenGLScreen frame = new OBJOpenGLScreen(f , this);

            frame.setVisible(true); //necessary as of 1.3
            desktop.add(frame);
            try {
                frame.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {}

        }catch (ClassCastException e){
            Scene f = (Scene) userObject;
            this.log("Ouverture d'une Scene OpenGL : " + f.getName());
            SCENEOpenGLScreen frame = new SCENEOpenGLScreen(f , this);
            frame.setVisible(true); //necessary as of 1.3
            desktop.add(frame);
            try {
                frame.setSelected(true);
            } catch (java.beans.PropertyVetoException ce) {}
        }
    }



    public void removeOBJ(Object o){
       for( Scene scenee : projet.getScene()){
           for (int i=0;i<scenee.getFormes().size();i++){
               if(o.equals(scenee.getFormes().get(i).getForme())){
                   scenee.getFormes().remove(i);
               }
           }
       }
        projet.getObj().remove(o);
    }
    public void addOBJ(Forme o){
        projet.getObj().add(o);
    }

    public void createTreeFrame() {
        frame = new TreeFrame(this.projet, this);
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }


    public void refreshTree(){
        frame.refresh();
    }
    //Quit the application.
    public void quit() {
        System.exit(5);
    }


    public void addSCN(Scene sc) {
        projet.getScene().add(sc);
    }
    public Projet getProjet() {
        return projet;
    }

    public void createPropos() {
        ProposFrame propos = new ProposFrame();
        propos.setVisible(true); //necessary as of 1.3
        desktop.add(propos);
        try {
            propos.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    public void createInspecteur(){
        inspeframe = new InspecteurLeBlanco(this);
        inspeframe.setVisible(true); //necessary as of 1.3
        desktop.add(inspeframe);
        try {
            inspeframe.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    public void updateInspecteur(Forme forme){
        inspeframe.setModule(forme);
    }
    public void updateInspecteur(Scene forme){
        inspeframe.setModule(forme);
    }

}