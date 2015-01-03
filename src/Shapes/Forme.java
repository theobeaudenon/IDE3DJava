package Shapes;

import javax.media.opengl.GL2;
import java.io.Serializable;

/**
 * Shapes
 * Created by Theo on 26/12/2014 for Ide3DProject.
 */
public class Forme implements Serializable {

    public String name;

    public Forme(String s) {

        name = s;
    }




    public String getName() {
        return name;
    }
    public void setName(String theName) {
        this.name = theName;
    }


    @Override
    public String toString() {
        return this.getName();
    }

    public void draw(GL2 gl){

    }


}
