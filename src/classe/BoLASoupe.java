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
    String name;
    Scene teleportTo = null;
    private Boolean selected = false;

    public BoLASoupe(Forme forme) {

        this.forme = forme;
        this.name = forme.getName();
        x= Float.parseFloat(String.valueOf(RandomUtils.randInt(-10, 10)));
        y=Float.parseFloat(String.valueOf(RandomUtils.randInt(-10, 10)));
        z=Float.parseFloat(String.valueOf(RandomUtils.randInt(-10, 10)));
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
    public Forme getForme() {
        return forme;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Float getZ() {
        return z;
    }

    public Scene getTeleportTo() {
        return teleportTo;
    }

    public void setTeleportTo(Scene teleportTo) {
        this.teleportTo = teleportTo;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
