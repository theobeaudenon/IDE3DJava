package Shapes;

import classe.DataConf;
import classe.Forme;
import classe.ColorRVB;

import javax.media.opengl.GL2;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Shapes
 * Created by Theo on 30/12/2014 for Ide3DProject.
 */
public class Triangle extends Forme implements Serializable {

        // Moitié de la longueur d'un coté, simplifie les calculs
        public ArrayList<ColorRVB> couleurs = new ArrayList<ColorRVB>() ;
        public ArrayList<DataConf> data = new ArrayList<DataConf>() ;

    // Centre du Cube dans le repere global, simplifie la translation
        private int [] position;

        public Triangle(String triangle, float taille, int x, int y, int z, ColorRVB colordevant, ColorRVB colordroite, ColorRVB colorgauche, ColorRVB colorbas, ColorRVB collordessous){
            super(triangle);
            data.add(new DataConf("taille",taille));
            couleurs.add(colordevant);
            couleurs.add(colordroite);
            couleurs.add(colorgauche);
            couleurs.add(colorbas);
            couleurs.add(collordessous);
            position = new int [3];
            position[0] = x;
            position[1] = y;
            position[2] = z;

        }
    public static Triangle newTriangle() {
        return new Triangle("Triangle", 2.0f, 0, 0, 0, new ColorRVB(1f, 1f, 0.9f), new ColorRVB(0.9f, 0.9f, 0.1f), new ColorRVB(0.9f, 0f, 1f), new ColorRVB(0.2f, 0.5f, 0.35f), new ColorRVB(0.5f, 0f, 0.4f));

    }


        public void draw(GL2 gl){

            gl.glBegin(GL2.GL_TRIANGLES);           // Begin drawing the pyramid with 4 triangles

            gl.glColor3fv(couleurs.get(0).buffer());     // Red
            gl.glVertex3f( 0.0f, data.get(0).getValue(), 0.0f);
            gl.glVertex3f(-data.get(0).getValue(), -data.get(0).getValue(), data.get(0).getValue());
            gl.glVertex3f(data.get(0).getValue(), -data.get(0).getValue(), data.get(0).getValue());


            gl.glColor3fv(couleurs.get(1).buffer());     // Red
            gl.glVertex3f(0.0f, data.get(0).getValue(), 0.0f);
            gl.glVertex3f(data.get(0).getValue(), -data.get(0).getValue(), data.get(0).getValue());
            gl.glVertex3f(data.get(0).getValue(), -data.get(0).getValue(), -data.get(0).getValue());

            // Back
            gl.glColor3fv(couleurs.get(2).buffer());     // Red
            gl.glVertex3f(0.0f, data.get(0).getValue(), 0.0f);
            gl.glVertex3f(data.get(0).getValue(), -data.get(0).getValue(), -data.get(0).getValue());
            gl.glVertex3f(-data.get(0).getValue(), -data.get(0).getValue(), -data.get(0).getValue());


            gl.glColor3fv(couleurs.get(3).buffer());       // Red
            gl.glVertex3f(0.0f, data.get(0).getValue(), 0.0f);
            gl.glVertex3f(-data.get(0).getValue(),-data.get(0).getValue(),-data.get(0).getValue());
            gl.glVertex3f(-data.get(0).getValue(),-data.get(0).getValue(), data.get(0).getValue());
            gl.glEnd();   // Done drawing the pyramid

            gl.glBegin(GL2.GL_QUADS);
            gl.glColor3fv(couleurs.get(4).buffer());
            gl.glVertex3f(-data.get(0).getValue(), -data.get(0).getValue(), data.get(0).getValue());
            gl.glVertex3f(data.get(0).getValue(), -data.get(0).getValue(),data.get(0).getValue());
            gl.glVertex3f(data.get(0).getValue(), -data.get(0).getValue(), -data.get(0).getValue());
            gl.glVertex3f(-data.get(0).getValue(), -data.get(0).getValue(), -data.get(0).getValue());
            gl.glEnd();   // Done drawing the pyramid

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
