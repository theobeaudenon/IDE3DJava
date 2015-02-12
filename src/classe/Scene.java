package classe;

import Generator.Perlin;
import Shapes.Cube;
import Shapes.Terrain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe
 * Created by Theo on 05/01/2015 for Ide3DProject.
 */
public class Scene implements Serializable {

    String name;
    public static final int WIDTH = 32;
    public static final int HEIGHT = 64;
    public float spawnX = 0;
    public float spawnY = 0;
    public float spawnZ = 0;

    public static final int BIG_NUMBER = (int) Math.pow(2, 8);
    ArrayList<BoLASoupe> formes = new ArrayList<>();
    public Scene() {}


    public Scene(String name) {
        this.name = name;
        formes.add(new BoLASoupe(new Terrain("Terrain", 10f,10f,10f,10f, new ColorRVB(0)),0,0,0,null,"cube"));


        for (int i = -WIDTH; i < WIDTH; i++) {
            for (int j = -WIDTH; j < WIDTH; j++) {
                float x = (float) 0 * 16 + i;
                float z = (float) 0 * 16 + j;
                float y = (int) 20 * ((Perlin.perlin2D(x * 0.2f+ BIG_NUMBER, z * 0.2f + BIG_NUMBER) + 1) / 2) + 1;
                formes.add(new BoLASoupe(new Cube("yoolo",0.5f, new ColorRVB(0.9f, 0.9f, 0.9f), new ColorRVB(0.9f, 0.42f, 0.1f), new ColorRVB(0.1f, 0f, 1f), new ColorRVB(0.4f, 1f, 0.7f), new ColorRVB(0f, 0f, 0.5f), new ColorRVB(0.6f, 0.5f, 0.1f)),j,y,i,null,"cube"));

                /*for (int k = 0; k < HEIGHT; k++) {
                    if (k <= y) {
                        formes.add(new BoLASoupe(new Cube("yoolo",0.5f, new ColorRVB(0.9f, 0.9f, 0.9f), new ColorRVB(0.9f, 0.42f, 0.1f), new ColorRVB(0.1f, 0f, 1f), new ColorRVB(0.4f, 1f, 0.7f), new ColorRVB(0f, 0f, 0.5f), new ColorRVB(0.6f, 0.5f, 0.1f)),j,k,i,null,"cube"));
                    } else {
                       // formes.add(new BoLASoupe(new Cube("yoolo",0.5f,0,0,0, new ColorRVB(0.9f, 0.9f, 0.9f), new ColorRVB(0.9f, 0.42f, 0.1f), new ColorRVB(0.1f, 0f, 1f), new ColorRVB(0.4f, 1f, 0.7f), new ColorRVB(0f, 0f, 0.5f), new ColorRVB(0.6f, 0.5f, 0.1f)),j,k,i,null,"cube"));
                        //this.blocks[i][j][k] = new Air(new Vector3f(x, (float) k, z), 1.0f);
                    }
                }**/
            }
        }

    }


    public ArrayList<BoLASoupe> getFormes() {
        return formes;
    }

    public float getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(float spawnX) {
        this.spawnX = spawnX;
    }

    public float getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(float spawnY) {
        this.spawnY = spawnY;
    }

    public float getSpawnZ() {
        return spawnZ;
    }

    public void setSpawnZ(float spawnZ) {
        this.spawnZ = spawnZ;
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
