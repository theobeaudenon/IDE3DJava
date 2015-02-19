package classe;

import java.io.Serializable;

/**
 * classe
 * Created by Theo on 07/01/2015 for Ide3DProject.
 */
public class DataConf implements Serializable {
    String emplacement;
    float value;

    public DataConf(String name, float taille) {
        emplacement = name;
        value = taille;
    }


    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getEmplacement() {
        return emplacement;
    }



}
