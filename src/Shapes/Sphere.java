package Shapes;

import javax.media.opengl.GL2;
import java.io.Serializable;

public class Sphere extends Forme implements Serializable {

    // Moitié de la longueur d'un coté, simplifie les calculs


    private ColorRVB colordevant;

    // Centre du Cube dans le repere global, simplifie la translation
    private int [] position;
    private double r = 1;

    public Sphere(String s, Double taille, int x, int y, int z, ColorRVB colordevant){
        super(s);
        r = taille;

        this.colordevant = colordevant;

        position = new int [3];
        position[0] = x;
        position[1] = y;
        position[2] = z;

    }



    public void draw(GL2 gl){
        gl.glColor3fv(colordevant.buffer());
        int i, j;
        double lats= 10;
        for(i = 0; i <= lats; i++) {
            double lat0 = Math.PI * (-0.5 + (double) (i - 1) / lats);
            double z0  = Math.sin(lat0);
            double zr0 =  Math.cos(lat0);

            double lat1 = Math.PI * (-0.5 + (double) i / lats);
            double z1 = Math.sin(lat1);
            double zr1 = Math.cos(lat1);

            gl.glBegin(gl.GL_QUAD_STRIP);
            double longs = 10;
            for(j = 0; j <= longs; j++) {
                double lng = 2 * Math.PI * (double) (j - 1) / longs;
                double x = Math.cos(lng);
                double y = Math.sin(lng);

                //gl.glNormal3d(x * zr0, y * zr0, z0);
                gl.glVertex3d(r*x * zr0,r* y * zr0,r* z0);
                //gl.glNormal3d(x * zr1, y * zr1, z1);
                gl.glVertex3d(r*x * zr1,r* y * zr1, r*z1);
            }
            gl.glEnd();
        }



    }


    public void getSetting(){

    }

}