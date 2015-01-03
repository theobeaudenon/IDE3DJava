package utils;

/**
 * Created by Boufle on 18/12/14.
 */

import Shapes.ColorRVB;
import Shapes.Cube;
import Shapes.Forme;
import classe.Projet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileReader {
    /**
     * Lister les fichiers contenus dans le zip
     * @param archive nom du fichier zip
     * @return liste sous forme ['nom, 'taille', 'date']
     */
    public static ArrayList getFiles(String archive) {
        ArrayList fileList = new ArrayList<String>();
        ZipInputStream zipInputStream = null;
        ZipEntry zipEntry = null;
        Long size;

        try {
            zipInputStream = new ZipInputStream(new FileInputStream(archive));
            zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                String[] file = new String[3];
                file[0] = zipEntry.getName();
                size = zipEntry.getSize()/1024;
                file[1] = size.toString()+ " ko";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                file[2] = simpleDateFormat.format(new Date(zipEntry.getTime()));

                fileList.add(zipEntry.getName());
                zipEntry = zipInputStream.getNextEntry();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ZipFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                zipInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ZipFileReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            return fileList;
        }
    }

    /**
     * Extraire un fichier
     * @param archive fichier zip
     * @param file nom du fichier à extraire
     * @param destPath répertoir de destination du fichier
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void extractTo(String archive, String file, String destPath) throws FileNotFoundException, IOException {
        ZipInputStream zipInputStream = null;
        ZipEntry zipEntry = null;
        byte[] buffer = new byte[2048];

        zipInputStream = new ZipInputStream(new FileInputStream(archive));
        zipEntry = zipInputStream.getNextEntry();
        while (zipEntry != null) {
            if (zipEntry.getName().equalsIgnoreCase(file)) {
                FileOutputStream fileoutputstream = new FileOutputStream(destPath + file);
                int n;

                while ((n = zipInputStream.read(buffer, 0, 2048)) > -1) {
                    fileoutputstream.write(buffer, 0, n);
                }

                fileoutputstream.close();
                zipInputStream.closeEntry();

            }
            zipEntry = zipInputStream.getNextEntry();
        }
    }

    public  static Projet read(String name, String path ){
       /* JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "ZIP PD", "zip");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(cmp);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getAbsolutePath());*/

            Projet pro =   new Projet();
            pro.setNom(name);
            ArrayList<String> scene = new ZipFileReader().getFiles(path);

            ArrayList<String> scene1 = new ArrayList<String>();
            ArrayList<Forme> obj1 = new ArrayList<Forme>();
            for (String s : scene){
                try {
                    String[] obj = s.split("/");
                    if(obj[0].equals("scenes")){
                        scene1.add(obj[1]);
                    }else  if(obj[0].equals("obj")){

                       /* Mise en place de la forme sauvegardé pour exemple */
                        Cube e = new Cube("", 1.0f, 0, 0, 0, new ColorRVB(0.9f,0.9f,0.9f), new ColorRVB(0.9f,0.42f,0.1f),  new ColorRVB(0.1f,0f,1f), new ColorRVB(0.4f,1f,0.7f), new ColorRVB(0f,0f,0.5f), new ColorRVB(0.6f,0.5f,0.1f));

                        obj1.add(e);
                    }
                }catch (Exception e){

                    System.out.println(s);
                }

            }
            pro.setScene(scene1);

            pro.setObj(obj1);

            return pro;

    }
}
