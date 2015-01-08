package Shapes;

import classe.ColorRVB;
import classe.DataConf;
import classe.Forme;

import javax.media.opengl.GL2;
import java.util.ArrayList;

import static java.lang.Math.*;

/**
 * Shapes
 * Created by Theo on 08/01/2015 for Ide3DProject.
 */
public class Cylindre extends Forme {

    public ArrayList<ColorRVB> couleurs = new ArrayList<ColorRVB>() ;
    public ArrayList<DataConf> data = new ArrayList<DataConf>() ;


    /*
           Function drw_polygon:
           Arguments:
               n - number of sides
               arg - starting angle (not so important at all)
               mult - multiplying sides to incrase their length
               v - cylinder height
       */
    public Cylindre(String s,int n , int arg, float mult , float v) {
        super(s);
    }



    @Override
    public void draw(GL2 gl) {

        int n = 3; int arg = 0; float mult = 1; float v = 1.0f;
        // DumbProof Double Check :)
        if (arg < 0)
            arg = 0;

        // Cylinder Bottom
        gl.glBegin(GL2.GL_POLYGON);
        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        for(int i = arg; i <= (360 + arg); i += (360 / n)) {
            float a = (float) (i * Math.PI / 180); // degrees to radians
            gl.glVertex3f(mult * Float.parseFloat(String.valueOf(cos(a))), mult * Float.parseFloat(String.valueOf(sin(a))), 0.0f);
        }
        gl.glEnd();

        // Cylinder Top
        gl.glBegin(GL2.GL_POLYGON);
        gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
        for(int i = arg; i <= (360 + arg); i += (360 / n)) {
            float a = (float) (i * Math.PI / 180); // degrees to radians
            gl.glVertex3f(mult * Float.parseFloat(String.valueOf(cos(a))), mult * Float.parseFloat(String.valueOf(sin(a))), v);
        }
        gl.glEnd();

        // Cylinder "Cover"
        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glColor4f(1.0f, 1.0f, 0.0f, 1.0f);
        for(int i = arg; i < 480; i += (360 / n)) {
            float a = (float) (i *  Math.PI / 180); // degrees to radians
            gl.glVertex3f(mult * Float.parseFloat(String.valueOf(cos(a))), mult * Float.parseFloat(String.valueOf(sin(a))), 0.0f);
            gl.glVertex3f(mult * Float.parseFloat(String.valueOf(cos(a))), mult * Float.parseFloat(String.valueOf(sin(a))), v);
        }
        gl.glEnd();



    }

    @Override
    public ArrayList<ColorRVB> params() {
        return null;
    }

    @Override
    public ArrayList<DataConf> conf() {
        return null;
    }
}
