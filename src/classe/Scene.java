package classe;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe
 * Created by Theo on 05/01/2015 for Ide3DProject.
 */
public class Scene implements Serializable {

    String name;



    ArrayList<BoLASoupe> formes = new ArrayList<>();

    public ArrayList<BoLASoupe> getFormes() {
        return formes;
    }

    public void addForme(BoLASoupe formes) {
        this.formes.add(formes);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.getName();
    }
}
