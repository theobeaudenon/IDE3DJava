package Frames;

import Frames.FixedFrame.InspecteurLeBlanco;
import Frames.FixedFrame.TreeFrame;
import Frames.OpenGl.GAMEOpenGLScreen;
import Frames.OpenGl.OBJOpenGLScreen;
import Frames.OpenGl.SCENEOpenGLScreen;
import Shapes.Terrain;
import classe.BoLASoupe;
import classe.Forme;
import classe.Projet;
import classe.Scene;
import utils.Menu.MenuAction;
import utils.ProjectExport.ProjectFileSaver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class InternalFrameDemo extends JFrame implements ActionListener {
    private final Projet projet;
    JDesktopPane desktop;
    private JMenuItem menuItemelog;
    private JMenuBar menuBar;
    private TreeFrame frame;
    private InspecteurLeBlanco inspeframe;
    private JInternalFrame task = new JInternalFrame("", false,false,false);
    private  JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private ArrayList<JInternalFrame> taskcurrent = new ArrayList<>();
    public InternalFrameDemo(Projet finalPro) {
        super("Editeur 3D SUPINFO");
        this.projet = finalPro;
        //this.setResizable(false);
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
        int gameWidth = (int) (Math.round(screenSize.height * 0.85));
        int inset = 50;
        setBounds(inset, inset, gameHeight, gameWidth);

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
        setResizable(false);
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
                    ProjectFileSaver.save(projet, null, 1);
                    System.exit(20);
                } else if (response == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }
                // Fermeture de l'application

            }
        });



        task.setVisible(true);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) task.getUI()).setNorthPane(null);

        task.setBorder(BorderFactory.createLineBorder(Color.black));
        task.setOpaque(false);
        task.getContentPane().setBackground(new Color(60, 63, 65));
        task.setSize(1520, 60);
        task.setLocation(300,805);
        this.add(task);
        refreshtask();



        //Make dragging a little faster but perhaps uglier.
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }
    public void  refreshtask(){

        top.setBackground(new Color(60, 63, 65));
        top.removeAll();
        top.setPreferredSize(this.getSize());
        for (final JInternalFrame frame : taskcurrent){
            JButton btn1 = new JButton(frame.getTitle());
            btn1.setBackground(new Color(75, 75, 75));
            btn1.setForeground(new Color(225, 39, 36));
            btn1.setPreferredSize(new Dimension(160, 40));
            btn1.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e) {

                    if (e.getButton() == MouseEvent.BUTTON3 || e.getButton() == MouseEvent.BUTTON2 ) {
                        removeframe(frame);
                        try {
                            frame.dispose();
                        }catch (Exception ess ){

                        }
                    } else {
                        frame.grabFocus();
                        frame.requestFocus();
                        try {
                            frame.setSelected(true);
                        } catch (PropertyVetoException e1) {
                            e1.printStackTrace();
                        }
                    }

                }
            });


            top.add(btn1);
        }
        task.getContentPane().add(top);
        top.updateUI();
    }

    public void log(String log) {
        System.out.println(log);
        menuItemelog.setText("                                                                                                Informations : " + log);
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

    public void removeframe(JInternalFrame frame){
        taskcurrent.remove(frame);
        refreshtask();
    }

    public void creatFrameOPGL(Object userObject) {
        try {
            Forme f = (Forme) userObject;
            boolean isopen = false;
            JInternalFrame js = null;
            for (JInternalFrame j : taskcurrent){

                if(j.getTitle().equals(f.getName())){
                    isopen = true;
                    js = j;
                }
            }
            if(!isopen){
                this.log("Ouverture d'une Frame OpenGL : " + f.getName());
                OBJOpenGLScreen frame = new OBJOpenGLScreen(f, this);
                Dimension desktopSize = this.getSize();
                Dimension jInternalFrameSize = frame.getSize();
                frame.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                        (desktopSize.height- jInternalFrameSize.height)/2);
                frame.setVisible(true); //necessary as of 1.3
                desktop.add(frame);
                taskcurrent.add(frame);
                refreshtask();
                try {
                    frame.setSelected(true);
                } catch (java.beans.PropertyVetoException e) {
                }
            }else {

                js.grabFocus();
                js.requestFocus();
                try {
                    js.setSelected(true);
                } catch (PropertyVetoException e1) {
                    e1.printStackTrace();
                }
            }

        } catch (ClassCastException e) {
            Scene f = (Scene) userObject;
            boolean isopen = false;
            JInternalFrame js = null;
            for (JInternalFrame j : taskcurrent){

                if(j.getTitle().equals(f.getName())){
                    isopen = true;
                    js = j;
                }
            }
            if(!isopen){
                this.log("Ouverture d'une Scene OpenGL : " + f.getName());
                SCENEOpenGLScreen frame = new SCENEOpenGLScreen(f, this);
                frame.setVisible(true); //necessary as of 1.3

                taskcurrent.add(frame);
                desktop.add(frame);
                refreshtask();
                try {
                    frame.setSelected(true);
                } catch (java.beans.PropertyVetoException ce) {
                }
            }else {

                js.grabFocus();
                js.requestFocus();
                try {
                    js.setSelected(true);
                } catch (PropertyVetoException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


    public void playgame(Scene userObject) {
        boolean ter = false;

        for (BoLASoupe bl : userObject.getFormes()) {
            try {
                Terrain t = (Terrain) bl.getForme();
                ter = true;

            } catch (ClassCastException e) {

            }
        }
        if (ter) {
            this.log("Ouverture du jeu : " + userObject.getName());
            GAMEOpenGLScreen frame = new GAMEOpenGLScreen(userObject, this, userObject.getSpawnX(), userObject.getSpawnY(), userObject.getSpawnZ());
            frame.setVisible(true); //necessary as of 1.3

            taskcurrent.add(frame);
            desktop.add(frame);
            refreshtask();
            try {
                frame.setSelected(true);
            } catch (java.beans.PropertyVetoException ce) {
            }
        }else {

            JOptionPane.showMessageDialog(desktop, "Aucun terrain dans cette scene");
        }
    }

    public void removeOBJ(Object o) {
        for (Scene scenee : projet.getScene()) {
            for (int i = 0; i < scenee.getFormes().size(); i++) {
                if (o.equals(scenee.getFormes().get(i).getForme())) {
                    scenee.getFormes().remove(i);
                }
            }
        }
        projet.getObj().remove(o);
    }

    public void addOBJ(Forme o) {
        projet.getObj().add(o);
    }

    public void createTreeFrame() {
        frame = new TreeFrame(this.projet, this);
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }


    public void refreshTree() {
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
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    public void createInspecteur() {
        inspeframe = new InspecteurLeBlanco(this);
        inspeframe.setVisible(true); //necessary as of 1.3
        desktop.add(inspeframe);
        try {
            inspeframe.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    public void updateInspecteur(Forme forme) {
        inspeframe.setModule(forme);
    }

    public void updateInspecteur(Scene forme) {
        inspeframe.setModule(forme);
    }

}