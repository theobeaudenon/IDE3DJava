package Shapes;

import classe.ColorRVB;
import classe.DataConf;
import classe.Forme;

import javax.media.opengl.GL2;
import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
/**
 * Shapes
 * Created by Theo on 14/01/2015 for Ide3DProject.
 */
public class Cone extends Forme implements Serializable {

    public ArrayList<ColorRVB> couleurs = new ArrayList<ColorRVB>();
    public ArrayList<DataConf> data = new ArrayList<DataConf>();

    public Cone(String s, ColorRVB top, ColorRVB bot, float n, float arg, float large) {
        super(s);


        couleurs.add(top);
        couleurs.add(bot);

        data.add(new DataConf("SENSI", n));
        data.add(new DataConf("HAUTEUR", arg));
        data.add(new DataConf("LARGEUR", large));

    }

    public static Cone newCone() {
        return  new Cone("Cone", new ColorRVB(1f, 1f, 0.9f), new ColorRVB(0.9f, 0.9f, 0.1f),  1f,5f,5f);

    }

    @Override
    public void draw(GL2 gl) {
        gl.glBegin(gl.GL_TRIANGLES);
        int k;
        float DEF_D = data.get(0).getValue();
        for (k=0;k<=360;k+=DEF_D){
            gl.glColor3fv(couleurs.get(0).buffer());
            gl.glVertex3f(0, 0, 1);
            gl.glColor3fv(couleurs.get(0).buffer());
            gl.glVertex3d(cos(k), sin(k), 0);
            gl.glColor3fv(couleurs.get(0).buffer());
            gl.glVertex3d(cos(k + DEF_D), sin(k + DEF_D), 0);
        }
        gl.glEnd();

    /* bottom circle */
    /* rotate back */
        gl.glRotated(90, 1, 0, 0);
        gl.glBegin(gl.GL_TRIANGLES);
        for (k=0;k<=360;k+=DEF_D) {
            gl.glColor3fv(couleurs.get(1).buffer());
            gl.glVertex3f(0, 0, 0);
            gl.glColor3fv(couleurs.get(1).buffer());
            gl.glVertex3d(cos(k), 0, sin(k));
            gl.glColor3fv(couleurs.get(1).buffer());
            gl.glVertex3d(cos(k + DEF_D), 0, sin(k + DEF_D));
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
