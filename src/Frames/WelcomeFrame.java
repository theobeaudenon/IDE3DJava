package Frames;

import classe.Projet;
import utils.ZipFileReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    }
    public void startP(Projet p){



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ImportProject)){

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "ZIP PD", "zip");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " +
                        chooser.getSelectedFile().getAbsolutePath());
            Projet pro =null;
            do {
                pro = ZipFileReader.read(chooser.getSelectedFile().getName(), chooser.getSelectedFile().getAbsolutePath());

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

            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JFrame.setDefaultLookAndFeelDecorated(true);




                    Projet p = new Projet();
                    p.setNom("Nouveau Projet");
                    p.setObj(new ArrayList<String>());
                    p.setScene(new ArrayList<String>());
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
