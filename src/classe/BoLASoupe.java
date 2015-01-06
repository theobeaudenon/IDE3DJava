package classe;

import utils.RandomUtils;

import java.io.Serializable;

/**
 * classe
 * Created by Theo on 06/01/2015 for Ide3DProject.
 */
public class BoLASoupe  implements Serializable {

    Forme forme;
    Float x;
    Float y;
    Float z;

    public BoLASoupe(Forme forme) {

        this.forme = forme;
        x= Float.parseFloat(String.valueOf(RandomUtils.randInt(0, 20)));
        y=Float.parseFloat(String.valueOf(RandomUtils.randInt(0, 20)));
        z=Float.parseFloat(String.valueOf(RandomUtils.randInt(0, 20)));
    }

    public Forme getForme() {
        return forme;
    }

    public void setForme(Forme forme) {
        this.forme = forme;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getZ() {
        return z;
    }

    public void setZ(Float z) {
        this.z = z;
    }
}
