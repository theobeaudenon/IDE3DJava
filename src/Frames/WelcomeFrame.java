package Frames;

import Shapes.*;
import classe.Forme;
import classe.Projet;
import classe.ColorRVB;
import utils.ProjectExport.ProjectFileReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Boufle on 18/12/14.
 */
public class WelcomeFrame extends JFrame implements ActionListener{

    private JButton NewProject = new JButton("Nouveau projet");
    private JButton ImportProject = new JButton("Importer un projet");

    public  WelcomeFrame(){


        setSize(new Dimension(300, 100));
        setLocationRelativeTo(this);
        setVisible(true);
        setResizable(false);
        NewProject.setPreferredSize(new Dimension(147, 100));
        NewProject.addActionListener(this);
        add(NewProject, BorderLayout.WEST);
        ImportProject.setPreferredSize(new Dimension(147, 100));
        ImportProject.addActionListener(this);
        add(ImportProject, BorderLayout.EAST);
        try {
            BufferedImage img = ImageIO.read(new File("res\\icon\\main.png"));
            this.setIconImage(img);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
    public void startP(Projet p){



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ImportProject)){

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "sauvegarde txt", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " +
                        chooser.getSelectedFile().getAbsolutePath());
            Projet pro =null;
            do {
                pro = ProjectFileReader.read(chooser.getSelectedFile().getName(), chooser.getSelectedFile().getAbsolutePath());

            }while (pro == null);


            WelcomeFrame.this.dispose();

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
        else {


         }
        }  else {
            WelcomeFrame.this.dispose();

            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JFrame.setDefaultLookAndFeelDecorated(true);

                    Projet p = new Projet();
                    p.setNom("Nouveau Projet");
                    ArrayList<String> s =  new ArrayList<String>();
                    s.add("Scene1");
                    ArrayList<Forme> o =  new ArrayList<Forme>();


                       /* Mise en place de la forme sauvegardé pour exemple */
                    Cube e = new Cube("cube",1.0f, 0, 0, 0, new ColorRVB(0.9f,0.9f,0.9f), new ColorRVB(0.9f,0.42f,0.1f),  new ColorRVB(0.1f,0f,1f), new ColorRVB(0.4f,1f,0.7f), new ColorRVB(0f,0f,0.5f), new ColorRVB(0.6f,0.5f,0.1f));
                    o.add(e);

                       /* Mise en place de la forme sauvegardé pour exemple */
                    Cube g = new Cube("Cube 2", 2.0f, 0, 0, 0, new ColorRVB(1f,1f,0.9f), new ColorRVB(0.9f,0.9f,0.1f),  new ColorRVB(0.9f,0f,1f), new ColorRVB(0.4f,1f,0.2f), new ColorRVB(0.5f,0f,0.4f), new ColorRVB(0.2f,0.5f,0.35f));

                    o.add(g);

                       /* Mise en place de la forme sauvegardé pour exemple */
                    Triangle tri = new Triangle("Triangle",2.0f, 0, 0, 0, new ColorRVB(1f,1f,0.9f), new ColorRVB(0.9f,0.9f,0.1f),  new ColorRVB(0.9f,0f,1f),new ColorRVB(0.2f,0.5f,0.35f), new ColorRVB(0.5f,0f,0.4f));

                    o.add(tri);

                       /* Mise en place de la forme sauvegardé pour exemple */
                    Sphere ed = new Sphere("Sphere ",3.2, 0, 0, 0, new ColorRVB(0.2f,0.2f,0.9f));

                    o.add(ed);


                    p.setObj(o);
                    p.setScene(s);
                    //Create and set up the window.
                    InternalFrameDemo frame = new InternalFrameDemo(p);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    //Display the window.
                    frame.setVisible(true);

                }

            });

        }
    }
}
