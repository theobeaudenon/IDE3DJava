package Shapes;

import classe.ColorRVB;
import classe.DataConf;
import classe.Forme;

import javax.media.opengl.GL2;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.floor;
import static java.lang.Math.sin;

/**
 * Shapes
 * Created by Theo on 08/01/2015 for Ide3DProject.
 */
public class Cylindre extends Forme {

    public ArrayList<ColorRVB> couleurs = new ArrayList<ColorRVB>();
    public ArrayList<DataConf> data = new ArrayList<DataConf>();


    /*
           Function drw_polygon:
           Arguments:
               n - number of sides
               arg - starting angle (not so important at all)
               mult - multiplying sides to incrase their length
               v - cylinder height
       */
    public Cylindre(String s, float n, float arg, float mult,float rayon,float rayon2,  ColorRVB top, ColorRVB bot, ColorRVB side, ColorRVB side2) {
        super(s);

        couleurs.add(top);
        couleurs.add(bot);
        couleurs.add(side);
        couleurs.add(side2);

        data.add(new DataConf("SENSI", n));

        data.add(new DataConf("HAUTEUR", arg));

        data.add(new DataConf("PROFINDEUR", mult));

        data.add(new DataConf("largeur", rayon));
        data.add(new DataConf("longeur", rayon2));

    }


    @Override
    public void draw(GL2 gl) {
        float rayon2 = data.get(4).getValue();
        float DEF_D = data.get(0).getValue();
        float rayon = data.get(3).getValue();
        double arg = data.get(1).getValue();
        double arg2 = data.get(2).getValue();
        // DumbProof Double Check :)
        if (DEF_D <= 0) {
            DEF_D = 1;
            data.get(0).setValue(1f);
        }
        gl.glBegin(GL2.GL_QUAD_STRIP);
        double j;
        for (j = 0; j <= 360; j += DEF_D) {
            gl.glColor3fv(couleurs.get(0).buffer());
            gl.glVertex3d(rayon*cos(j), +arg, rayon2*sin(j));
            gl.glColor3fv(couleurs.get(1).buffer());
            gl.glVertex3d(rayon*cos(j), -arg2, rayon2*sin(j));
        }
        gl.glEnd();

    /* top and bottom circles */
    /* reuse the currentTexture on top and bottom) */


        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glColor3fv(couleurs.get(2).buffer());
        //gl.glVertex3d(0, i, 0);
        double k;
        for (k = 0; k <= 360; k += DEF_D) {
            gl.glVertex3d(rayon * cos(k), arg, rayon2*sin(k));
        }
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glColor3fv(couleurs.get(3).buffer());
        for (k = 0; k <= 360; k += DEF_D) {
            gl.glVertex3d(rayon * cos(k), -arg2, rayon2*sin(k));
        }
        gl.glEnd();



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
