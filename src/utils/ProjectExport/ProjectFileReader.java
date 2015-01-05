package utils.ProjectExport;

/**
 * Created by Boufle on 18/12/14.
 */

import classe.Projet;

import java.io.*;

public class ProjectFileReader {
    public  static Projet read(String name, String path ){

        Projet pro = new Projet();


        FileInputStream streamIn = null;
        try {
            streamIn = new FileInputStream(path);
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
            pro = (Projet) objectinputstream.readObject();
            pro.setPath(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pro;

    }
}
