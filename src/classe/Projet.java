package classe;

import java.util.ArrayList;

/**
 * Created by Boufle on 18/12/14.
 */
public class Projet {



    private String nom;
    private ArrayList<String> scene = new ArrayList<String>();
    private ArrayList<String> obj = new ArrayList<String>();



    public ArrayList<String> getObj() {
        return obj;
    }

    public void setObj(ArrayList<String> obj) {
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
