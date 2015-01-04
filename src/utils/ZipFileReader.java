package utils;

/**
 * Created by Boufle on 18/12/14.
 */

import classe.Projet;

import java.io.*;

public class ZipFileReader {




    public  static Projet read(String name, String path ){
       /* JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "ZIP PD", "zip");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(cmp);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getAbsolutePath());*/
        Projet pro = new Projet();


        FileInputStream streamIn = null;
        try {
            streamIn = new FileInputStream(path);
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
            pro = (Projet) objectinputstream.readObject();

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
