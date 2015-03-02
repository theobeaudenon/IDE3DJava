package Shapes;

import classe.DataConf;
import classe.Forme;
import classe.ColorRVB;

import javax.media.opengl.GL2;
import java.io.Serializable;
import java.util.ArrayList;

public class Sphere extends Forme implements Serializable {

    // Moitié de la longueur d'un coté, simplifie les calculs

    public ArrayList<ColorRVB> couleurs = new ArrayList<ColorRVB>() ;
    public ArrayList<DataConf> data = new ArrayList<DataConf>() ;

    // Centre du Cube dans le repere global, simplifie la translation
    private int [] position;

    public Sphere(String s, float taille, int x, int y, int z, ColorRVB colordevant, float resolution){
        super(s);
        data.add(new DataConf("rayon",taille));
        data.add(new DataConf("Resolution",resolution));
        couleurs.add(colordevant);
        position = new int [3];
        position[0] = x;
        position[1] = y;
        position[2] = z;
    }

    public static Sphere newSphere() {
        return new Sphere("Sphere ", 3.2f, 0, 0, 0, new ColorRVB(0.2f, 0.2f, 0.9f), 30);
    }

    public void draw(GL2 gl){
        gl.glColor3fv(couleurs.get(0).buffer());
        int i, j;
        double lats= data.get(1).getValue();
        for(i = 0; i <= lats; i++) {
            double lat0 = Math.PI * (-0.5 + (double) (i - 1) / lats);
            double z0  = Math.sin(lat0);
            double zr0 =  Math.cos(lat0);

            double lat1 = Math.PI * (-0.5 + (double) i / lats);
            double z1 = Math.sin(lat1);
            double zr1 = Math.cos(lat1);

            gl.glBegin(gl.GL_QUAD_STRIP);
            double longs = lats;
            for(j = 0; j <= longs; j++) {
                double lng = 2 * Math.PI * (double) (j - 1) / longs;
                double x = Math.cos(lng);
                double y = Math.sin(lng);

                //gl.glNormal3d(x * zr0, y * zr0, z0);
                gl.glVertex3d(data.get(0).getValue()*x * zr0,data.get(0).getValue()* y * zr0,data.get(0).getValue()* z0);
                //gl.glNormal3d(x * zr1, y * zr1, z1);
                gl.glVertex3d(data.get(0).getValue()*x * zr1,data.get(0).getValue()* y * zr1, data.get(0).getValue()*z1);
            }
            gl.glEnd();
        }



    }

    @Override
    public ArrayList<ColorRVB> params() {
        return couleurs;
    }

    @Override
    public ArrayList<DataConf> conf() {
        return data;
    }


}