package utils.ProjectExport;

import Frames.InternalFrameDemo;
import classe.Projet;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.*;

/**
 * utils.ProjectExport
 * Created by Theo on 04/01/2015 for Ide3DProject.
 */
public class ProjectFileSaver {
    public static void save(Projet projet, InternalFrameDemo thiss, int i) {
        if (projet.getPath() == null || i == 2) {
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File("/home/me/Documents/" + projet.getNom() + ".eb"));
            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                try {
                    FileOutputStream fout = new FileOutputStream(chooser.getSelectedFile());
                    ObjectOutputStream oos = new ObjectOutputStream(fout);
                    oos.writeObject(projet);
                    oos.close();
                    projet.setPath(chooser.getSelectedFile().getAbsolutePath());
                    if (thiss != null) {
                        thiss.log("fichier enregistré sous : " + chooser.getSelectedFile());
                        String content = FileUtils.readFileToString(new File("Config/Recent.txt"));
                        File f = new  File("Config/Recent.txt");
                        FileWriter fw = new FileWriter(f.getAbsoluteFile());
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(content+projet.getPath()+":::");
                        bw.close();
                        fw.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            try {
                FileOutputStream fout = new FileOutputStream(projet.getPath());
                ObjectOutputStream oos = new ObjectOutputStream(fout);
                oos.writeObject(projet);
                oos.close();
                if (thiss != null) {
                    thiss.log("fichier enregistré sous : " + projet.getPath());

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
