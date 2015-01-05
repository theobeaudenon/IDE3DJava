package utils.ProjectExport;

import Frames.InternalFrameDemo;
import classe.Projet;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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
