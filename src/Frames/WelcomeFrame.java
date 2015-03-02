package Frames;

import Shapes.*;
import classe.ColorRVB;
import classe.Forme;
import classe.Projet;
import classe.Scene;
import utils.ProjectExport.ProjectFileReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Boufle on 13/02/15.
 */
public class WelcomeFrame extends JFrame implements ActionListener {

    private JPanel body = new JPanel(new GridBagLayout());
    private JLabel recentProject = new JLabel("Projets Recent");
    private JLabel startProject = new JLabel("Quick Start");
    private JPanel top = new JPanel();
    private JPanel recentPan = new JPanel();
    private JPanel recentPan2 = new JPanel();
    private JPanel start = new JPanel();
    private JPanel start2 = new JPanel(new GridLayout(4,1));

    private ImageIcon projectjButtonIcone = new ImageIcon("res\\img\\close.gif");
    private JButton newProjectjButton = new JButton("Create New Project",projectjButtonIcone);

    private ImageIcon projectImportjButtonIcone = new ImageIcon("res\\img\\close.gif");
    private JButton importProjectjButton = new JButton("Import Project",projectImportjButtonIcone);

    private ImageIcon projectOpenjButtonIcone = new ImageIcon("res\\img\\close.gif");
    private JButton openProjectjButton = new JButton("Open Project",projectOpenjButtonIcone);

    private ImageIcon conf = new ImageIcon("res\\img\\close.gif");
    private JButton confjButton = new JButton("Configure",conf);

    public WelcomeFrame(){

        GridBagConstraints c = new GridBagConstraints();

        setResizable(false);
        setSize(new Dimension(800, 600));
        body.setBackground(new Color(60, 63, 65));
        setLocationRelativeTo(this);
        setContentPane(body);

        top.setPreferredSize(new Dimension(792, 100));
        top.setBackground(new Color(100,100,100));

        recentPan.setPreferredSize(new Dimension(200,30));
        recentPan.setBackground(new Color(100,100,100));

        recentProject.setFont(new Font("Courier", Font.ITALIC, 19));
        recentProject.setForeground(Color.WHITE);
        recentPan.add(recentProject);

        start.setPreferredSize(new Dimension(500,30));
        start.setBackground(new Color(100, 100, 100));

        startProject.setFont(new Font("Courier", Font.ITALIC, 19));
        startProject.setForeground(Color.WHITE);
        start.add(startProject);

        recentPan2.setPreferredSize(new Dimension(200,300));
        recentPan2.setBackground(new Color(60, 63, 65));
        recentPan2.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));

        start2.setPreferredSize(new Dimension(500, 300));
        start2.setBackground(new Color(60, 63, 65));
        start2.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));

        c.insets = new Insets(0, 0, 20, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weighty = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        body.add(top, c);

        c.insets = new Insets(0, 30, 0, 30);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.weighty = 1;
        body.add(recentPan, c);

        c.insets = new Insets(0, 0, 0, 30);
        c.gridx = 1;
        c.gridy = 1;
        c.weighty = 1;
        body.add(start, c);

        c.insets = new Insets(0, 30, 0, 0);
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 1;
        body.add(recentPan2, c);

        c.insets = new Insets(0, 0, 0, 30);
        c.gridx = 1;
        c.gridy = 2;
        c.weighty = 1;
        body.add(start2, c);

        newProjectjButton.setBorderPainted(false);
        newProjectjButton.setFocusPainted(false);
        newProjectjButton.setForeground(Color.WHITE);
        newProjectjButton.setContentAreaFilled(false);
        newProjectjButton.setSelected(false);
        newProjectjButton.setHorizontalAlignment(SwingConstants.LEFT);
        newProjectjButton.addActionListener(this);
        start2.add(newProjectjButton);

        importProjectjButton.setBorderPainted(false);
        importProjectjButton.setFocusPainted(false);
        importProjectjButton.setForeground(Color.WHITE);
        importProjectjButton.setContentAreaFilled(false);
        importProjectjButton.setSelected(false);
        importProjectjButton.setHorizontalAlignment(SwingConstants.LEFT);
        importProjectjButton.addActionListener(this);
        start2.add(importProjectjButton);

        openProjectjButton.setBorderPainted(false);
        openProjectjButton.setFocusPainted(false);
        openProjectjButton.setForeground(Color.WHITE);
        openProjectjButton.setContentAreaFilled(false);
        openProjectjButton.setSelected(false);
        openProjectjButton.addActionListener(this);
        openProjectjButton.setHorizontalAlignment(SwingConstants.LEFT);
        start2.add(openProjectjButton);

        confjButton.setBorderPainted(false);
        confjButton.setFocusPainted(false);
        confjButton.setForeground(Color.WHITE);
        confjButton.setContentAreaFilled(false);
        confjButton.setSelected(false);
        confjButton.addActionListener(this);
        confjButton.setHorizontalAlignment(SwingConstants.LEFT);
        start2.add(confjButton);

        setVisible(true);

    }

    private void startpro(Projet pro) {

        WelcomeFrame.this.dispose();

        final Projet finalPro = pro;
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);

                //Create and set up the window.
                InternalFrameDemo frame = new InternalFrameDemo(finalPro);
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                //Display the window.
                frame.setVisible(true);


            }

        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(projectImportjButtonIcone)) {

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "sauvegarde .eb", "eb");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " +
                        chooser.getSelectedFile().getAbsolutePath());
                Projet pro = null;
                do {
                    pro = ProjectFileReader.read(chooser.getSelectedFile().getName(), chooser.getSelectedFile().getAbsolutePath());

                } while (pro == null);

                startpro(pro);
            } else {


            }
        }
        else if (e.getSource().equals(newProjectjButton))
        {
            WelcomeFrame.this.dispose();

            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    Projet p = new Projet();
                    p.setNom("Nouveau Projet");
                    ArrayList<Scene> s = new ArrayList<Scene>();

                    Scene sc = new Scene();
                    sc.setName("Scene 1");
                    s.add(sc);


                    ArrayList<Forme> o = new ArrayList<Forme>();


                       /* Mise en place de la forme sauvegardé pour exemple */
                    Cube e = new Cube("cube", 1.0f, new ColorRVB(0.9f, 0.9f, 0.9f), new ColorRVB(0.9f, 0.42f, 0.1f), new ColorRVB(0.1f, 0f, 1f), new ColorRVB(0.4f, 1f, 0.7f), new ColorRVB(0f, 0f, 0.5f), new ColorRVB(0.6f, 0.5f, 0.1f));
                    o.add(e);

                       /* Mise en place de la forme sauvegardé pour exemple */
                    Cube g = new Cube("Cube 2", 2.0f, new ColorRVB(1f, 1f, 0.9f), new ColorRVB(0.9f, 0.9f, 0.1f), new ColorRVB(0.9f, 0f, 1f), new ColorRVB(0.4f, 1f, 0.2f), new ColorRVB(0.5f, 0f, 0.4f), new ColorRVB(0.2f, 0.5f, 0.35f));

                    o.add(g);

                       /* Mise en place de la forme sauvegardé pour exemple */
                    Triangle tri = new Triangle("Triangle", 2.0f, 0, 0, 0, new ColorRVB(1f, 1f, 0.9f), new ColorRVB(0.9f, 0.9f, 0.1f), new ColorRVB(0.9f, 0f, 1f), new ColorRVB(0.2f, 0.5f, 0.35f), new ColorRVB(0.5f, 0f, 0.4f));

                    o.add(tri);

                       /* Mise en place de la forme sauvegardé pour exemple */
                    Sphere ed = new Sphere("Sphere ", 3.2f, 0, 0, 0, new ColorRVB(0.2f, 0.2f, 0.9f), 30);

                    o.add(ed);

                    Cylindre sy = new Cylindre("Cylindre",1f,1f,1f,1f,1f,new ColorRVB(1f, 1f, 0.9f),new ColorRVB(1f, 1f, 0.9f),new ColorRVB(1f, 1f, 0.9f),new ColorRVB(1f, 1f, 0.9f));
                    o.add(sy);

                    Cone cone = new Cone("Cone", new ColorRVB(1f, 1f, 0.9f), new ColorRVB(0.9f, 0.9f, 0.1f),  1f,5f,5f);
                    o.add(cone);

                    Terrain terr = new Terrain("Terrain", 10f,10f,10f,10f, new ColorRVB(0));
                    o.add(terr);

                    p.setObj(o);
                    p.setScene(s);
                    p.setPath(null);
                    //Create and set up the window.
                    InternalFrameDemo frame = new InternalFrameDemo(p);
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                    //Display the window.
                    frame.setVisible(true);


                }

            });

        }
    }
}
