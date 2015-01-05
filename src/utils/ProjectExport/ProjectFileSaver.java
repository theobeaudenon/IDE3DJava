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
    public static void save(Projet projet, InternalFrameDemo thiss){
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("/home/me/Documents/" + projet.getNom() + ".eb"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fout = new FileOutputStream(chooser.getSelectedFile());
                ObjectOutputStream oos = new ObjectOutputStream(fout);
                oos.writeObject(projet);
                oos.close();
                thiss.log("fichier enregistré sous : " + chooser.getSelectedFile());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void save(Projet projet) {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("/home/me/Documents/" + projet.getNom() + ".eb"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fout = new FileOutputStream(chooser.getSelectedFile());
                ObjectOutputStream oos = new ObjectOutputStream(fout);
                oos.writeObject(projet);
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
