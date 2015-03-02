package Frames;

import Frames.Dialog.PopupObjectAdd;
import Shapes.*;
import classe.*;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import utils.CustomTextField;
import utils.ProjectExport.ProjectFileReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by Boufle on 18/12/14.
 */
public class WelcomeFrame2 extends JFrame implements ActionListener {

    private JButton NewProject = new JButton("Nouveau projet");
    private JButton ImportProject = new JButton("Importer un projet");

    public WelcomeFrame2() {
        setSize(new Dimension(300, 390));
        setLocationRelativeTo(this);
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(20);


            }
        });


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
                            model.addElement(fileEntry);
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
        list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int index = list.locationToIndex(e.getPoint());
                File item = (File) model.getElementAt(index);

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

        add(list, BorderLayout.SOUTH);
        setVisible(true);
  }

    private void startpro(Projet pro) {

        WelcomeFrame2.this.dispose();

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
        if (e.getSource().equals(ImportProject)) {

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
        } else {
            WelcomeFrame2.this.dispose();

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
                    InternalFrameDemo frame = new InternalFrameDemo(p);
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                    //Display the window.
                    frame.setVisible(true);


                }

            });

        }
    }
}
