package Frames;

import Shapes.*;
import classe.ColorRVB;
import classe.Forme;
import classe.Projet;
import classe.Scene;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import utils.ProjectExport.ProjectFileReader;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
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

    private JLabel startup = new JLabel("Welcome to IDEA3D");

    private ImageIcon icone = new ImageIcon("res\\img\\close.gif");
    private JLabel icon = new JLabel(icone);

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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("IDEA3D");
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

        newProjectjButton.setBorderPainted(true);
        newProjectjButton.setFocusPainted(false);
        newProjectjButton.setForeground(Color.WHITE);
        newProjectjButton.setContentAreaFilled(false);
        newProjectjButton.setSelected(false);
        newProjectjButton.setHorizontalAlignment(SwingConstants.LEFT);
        newProjectjButton.addActionListener(this);
        start2.add(newProjectjButton);

        importProjectjButton.setBorderPainted(true);
        importProjectjButton.setFocusPainted(false);
        importProjectjButton.setForeground(Color.WHITE);
        importProjectjButton.setContentAreaFilled(false);
        importProjectjButton.setSelected(false);
        importProjectjButton.setHorizontalAlignment(SwingConstants.LEFT);
        importProjectjButton.addActionListener(this);
        start2.add(importProjectjButton);

        openProjectjButton.setBorderPainted(true);
        openProjectjButton.setFocusPainted(false);
        openProjectjButton.setForeground(Color.WHITE);
        openProjectjButton.setContentAreaFilled(false);
        openProjectjButton.setSelected(false);
        openProjectjButton.addActionListener(this);
        openProjectjButton.setHorizontalAlignment(SwingConstants.LEFT);
        start2.add(openProjectjButton);

        confjButton.setBorderPainted(true);
        confjButton.setFocusPainted(false);
        confjButton.setForeground(Color.WHITE);
        confjButton.setContentAreaFilled(false);
        confjButton.setSelected(false);
        confjButton.addActionListener(this);
        confjButton.setHorizontalAlignment(SwingConstants.LEFT);
        start2.add(confjButton);

        icon.setPreferredSize(new Dimension(100, 100));
        top.add(icon);

        startup.setForeground(Color.WHITE);
        startup.setFont(new Font("Courier", Font.ITALIC, 30));
        top.add(startup);

        JLabel sizerTest = new JLabel();
        sizerTest.setPreferredSize(new Dimension(400,100));
        top.add(sizerTest);
        setVisible(true);

        final DefaultListModel model = new DefaultListModel();

        try {
            String content = FileUtils.readFileToString(new File("Config/Recent.txt"));
            for (  String fileEntrystr : content.split(":::")) {
                if(!fileEntrystr.equals("")) {
                    File fileEntry = new File(fileEntrystr);
                    if (fileEntry.isDirectory()) {
                        // listFilesForFolder(fileEntry);
                    } else {
                        if (FilenameUtils.getExtension(fileEntry.getName()).equals("eb")) {
                            System.out.println(fileEntry.getName());
                            model.insertElementAt(fileEntry,0);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final JList list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //list.setAutoscrolls(true);
        list.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        list.setBackground(new Color(60, 63, 65));
        list.setForeground(new Color(205, 198, 183));
        list.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                JList l = (JList)e.getSource();
                ListModel m = l.getModel();
                int index = l.locationToIndex(e.getPoint());
                if( index>-1 ) {
                    l.setToolTipText(m.getElementAt(index).toString());
                }
            }
        });
        list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    int index = list.locationToIndex(e.getPoint());
                    File item = (File) model.getElementAt(index);
                    Projet pro = null;

                    pro = ProjectFileReader.read(item.getName(), item.getAbsolutePath());
                    if(pro != null){

                        startpro(pro);
                    }
                }
            }
        });

        list.setForeground(Color.WHITE);
        list.setFont(new Font("Courier", Font.ITALIC, 11));
        list.setFixedCellHeight(30);
        list.setFixedCellWidth(190);
        recentPan2.add(list);
    }



    private void startpro(Projet pro) {

        WelcomeFrame.this.dispose();

        final Projet finalPro = pro;
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               // JFrame.setDefaultLookAndFeelDecorated(true);

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
        if (e.getSource().equals(openProjectjButton) || e.getSource().equals(importProjectjButton) ) {

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
                    Cube e =  Cube.newCube();
                    o.add(e);

                       /* Mise en place de la forme sauvegardé pour exemple */
                    Triangle tri = Triangle.newTriangle();
                    o.add(tri);

                       /* Mise en place de la forme sauvegardé pour exemple */
                    Sphere ed = Sphere.newSphere();
                    o.add(ed);

                    Cylindre sy = Cylindre.newCylindre();
                    o.add(sy);

                    Cone cone = Cone.newCone();
                    o.add(cone);


                    Terrain terr = Terrain.newTerrain();
                    o.add(terr);

                    p.setObj(o);
                    p.setScene(s);
                    p.setPath(null);
                    //Create and set up the window.
                    startpro(p);



                }

            });

        }else{

            System.out.print(e.getSource());

        }
    }


}
