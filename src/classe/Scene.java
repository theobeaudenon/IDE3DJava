package classe;

import Generator.Perlin;
import Shapes.Cube;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe
 * Created by Theo on 05/01/2015 for Ide3DProject.
 */
public class Scene implements Serializable {

    String name;
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int BIG_NUMBER = (int) Math.pow(2, 18);
    ArrayList<BoLASoupe> formes = new ArrayList<>();
    public Scene() {}
    public Scene(String name) {
        this.name = name;


        for (int i = -10; i < WIDTH; i++) {
            for (int j = -10; j < WIDTH; j++) {
                float x = (float) 0 * 16 + i;
                float z = (float) 0 * 16 + j;
                float y = (int) 20 * ((Perlin.perlin2D(x * 0.2f+ BIG_NUMBER, z * 0.2f + BIG_NUMBER) + 1) / 2) + 1;

                for (int k = 0; k < HEIGHT; k++) {
                    if (k <= y) {
                        formes.add(new BoLASoupe(new Cube("yoolo",0.5f,0,0,0, new ColorRVB(0.9f, 0.9f, 0.9f), new ColorRVB(0.9f, 0.42f, 0.1f), new ColorRVB(0.1f, 0f, 1f), new ColorRVB(0.4f, 1f, 0.7f), new ColorRVB(0f, 0f, 0.5f), new ColorRVB(0.6f, 0.5f, 0.1f)),j,k,i,null,"cube"));
                       // this.blocks[i][j][k] = new Solid(new Vector3f(x, (float) k, z), 1.0f, this.colorist);
                    } else {
                        //this.blocks[i][j][k] = new Air(new Vector3f(x, (float) k, z), 1.0f);
                    }
                }
            }
        }

    }


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
