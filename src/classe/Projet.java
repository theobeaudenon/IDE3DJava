package classe;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Boufle on 18/12/14.
 */
public class Projet implements Serializable {



    private String nom;
    private String path;
    private ArrayList<Scene> scene = new ArrayList<Scene>();
    private ArrayList<Forme> obj = new ArrayList<Forme>();





    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<Forme> getObj() {
        return obj;
    }

    public void setObj(ArrayList<Forme> obj) {
        this.obj = obj;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Scene> getScene() {
        return scene;
    }

    public void setScene(ArrayList<Scene> scene) {
        this.scene = scene;
    }

}
