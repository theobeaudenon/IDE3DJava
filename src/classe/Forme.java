package classe;

import javax.media.opengl.GL2;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Shapes
 * Created by Theo on 26/12/2014 for Ide3DProject.
 */
public abstract class Forme implements Serializable {

    private String name;

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

    abstract public void draw(GL2 gl) ;
    abstract public ArrayList<ColorRVB> params() ;
    abstract public ArrayList<DataConf> conf() ;


}
