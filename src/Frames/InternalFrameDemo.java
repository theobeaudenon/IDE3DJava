package Frames;

import Shapes.*;
import classe.Forme;
import classe.Projet;
import utils.ColorRVB;
import utils.Menu.*;
import utils.ZipFileReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class InternalFrameDemo extends JFrame
        implements ActionListener {
    private final Projet projet;
    JDesktopPane desktop;
    private JMenuItem menuItemelog ;
    private JMenuBar menuBar;
    private TreeFrame frame;

    public InternalFrameDemo(Projet finalPro) {
        super("Editeur 3D SUPINFO");
        this.projet = finalPro;
        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            BufferedImage img = ImageIO.read(new File("res\\icon\\main.png"));
            this.setIconImage(img);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon/main.png")));

        int gameHeight = (int) (Math.round(screenSize.width * 0.95));
        int gameWidth = (int) (Math.round( screenSize.height * 0.85));

        setBounds(inset, inset,
                gameHeight ,
                gameWidth );

        //Set up the GUI.
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
         * OUVERTURE MENU
         */
        createTreeFrame();
       // creatFrameOPGL(obj.getUserObject());


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


    public void log(String log){
        System.out.println(log);
        //menuItemelog = new JMenuItem();
       // menuItemelog.setAutoscrolls(true);
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
       // menuItemelog.updateUI();
        menuBar.updateUI();
    }



    //React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) { //new
            welcomescreen();
        }
        else if ("Project".equals(e.getActionCommand())) { //new
            createTreeFrame();
        }
         else if ("file".equals(e.getActionCommand())) { //new
            welcomescreen();
        }else if ("quit".equals(e.getActionCommand())) { //new
            quit();

        }else if ("save".equals(e.getActionCommand())) { //new
            save();
        }
        else if ("sphere".equals(e.getActionCommand())) { //new

                       /* Mise en place de la forme sauvegardé pour exemple */
            Sphere ed = new Sphere(name(), 3d, 0, 0, 0, new ColorRVB(0.8f,0.8f,0.8f));
            addOBJ(ed);
            refreshTree();
            creatFrameOPGL(ed);

        }
        else if ("carre".equals(e.getActionCommand())) { //new

                       /* Mise en place de la forme sauvegardé pour exemple */
            Cube g = new Cube(name(), 1.0f, 0, 0, 0, new ColorRVB(1.0f,1.0f,1.0f), new ColorRVB(1.0f,1.0f,1.0f), new ColorRVB(1.0f,1.0f,1.0f), new ColorRVB(1.0f,1.0f,1.0f), new ColorRVB(1.0f,1.0f,1.0f), new ColorRVB(1.0f,1.0f,1.0f));
            addOBJ(g);
            refreshTree();
            creatFrameOPGL(g);

        }else if ("triangle".equals(e.getActionCommand())) { //new

                       /* Mise en place de la forme sauvegardé pour exemple */
            Triangle tri = new Triangle(name(), 2.0f, 0, 0, 0, new ColorRVB(1f,1f,0.9f), new ColorRVB(0.5f,0f,0.4f), new ColorRVB(0.9f,0.9f,0.1f),  new ColorRVB(0.9f,0f,1f),new ColorRVB(0.2f,0.5f,0.35f));
            addOBJ(tri);
            refreshTree();
            creatFrameOPGL(tri);

         }

        else { //quit

        }
    }
    public static String name(){
        JFrame frame = new JFrame("Nom");
        // prompt the user to enter their name
        return  JOptionPane.showInputDialog(frame, "Entrez le nom de l'objet");

    }
    private void welcomescreen() {
        WelcomeFrame frame = new WelcomeFrame();
        frame.setVisible(true); //necessary as of 1.3
        //desktop.add(frame);
        //quit();
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

    protected void creatFrameOPGL(Object userObject ){
        Forme f = (Forme)userObject;
        this.log("Ouverture d'une Frame OpenGL : " + f.getName());
        CWGOpenGLScreen frame = new CWGOpenGLScreen(f , this);

        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    protected void save(){

        JFileChooser chooser = new JFileChooser();

        chooser.setSelectedFile(new File("/home/me/Documents/" + projet.getNom() + ".eb"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fout = new FileOutputStream(chooser.getSelectedFile());
                ObjectOutputStream oos = new ObjectOutputStream(fout);
                oos.writeObject(projet);
                 oos.close();
                this.log("fichier enregistré sous : "+ chooser.getSelectedFile());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }

    public void removeOBJ(Object o){
        projet.getObj().remove(o);
    }
    public void addOBJ(Forme o){
        projet.getObj().add(o);
    }

    protected void createTreeFrame() {
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
    protected void quit() {
        System.exit(0);
    }




    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.

    }
}