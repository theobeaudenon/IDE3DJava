package classe;

import Shapes.Forme;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Boufle on 18/12/14.
 */
public class Projet implements Serializable {



    private String nom;
    private ArrayList<String> scene = new ArrayList<String>();
    private ArrayList<Forme> obj = new ArrayList<Forme>();



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

    public ArrayList<String> getScene() {
        return scene;
    }

    public void setScene(ArrayList<String> scene) {
        this.scene = scene;
    }

}
