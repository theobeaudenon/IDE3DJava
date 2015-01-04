package Shapes;

import classe.Forme;
import utils.ColorRVB;

import javax.media.opengl.GL2;
import java.io.Serializable;

/**
 * Shapes
 * Created by Theo on 30/12/2014 for Ide3DProject.
 */
public class Triangle extends Forme implements Serializable {

        // Moitié de la longueur d'un coté, simplifie les calculs
        private float tailleSur2;
        private ColorRVB colordevant;
        private ColorRVB colordroite;
        private ColorRVB colorgauche;
        private ColorRVB colorderriere;
        private ColorRVB collordessous;
        // Centre du Cube dans le repere global, simplifie la translation
        private int [] position;

        public Triangle(String triangle, float taille, int x, int y, int z, ColorRVB colordevant, ColorRVB colordroite, ColorRVB colorgauche, ColorRVB colorbas, ColorRVB collordessous){
            super(triangle);
            tailleSur2 = taille;
            this.colordevant = colordevant;
            this.colordroite = colordroite;
            this.colorgauche = colorgauche;
            this.colorderriere = colorbas;
            this.collordessous = collordessous;
            position = new int [3];
            position[0] = x;
            position[1] = y;
            position[2] = z;

        }



        public void draw(GL2 gl){

            gl.glBegin(GL2.GL_TRIANGLES);           // Begin drawing the pyramid with 4 triangles
            // Front
            gl.glColor3fv(colordevant.buffer());     // Red
            gl.glVertex3f( 0.0f, 1.0f, 0.0f);
            gl.glVertex3f(-1.0f, -1.0f, 1.0f);
            gl.glVertex3f(1.0f, -1.0f, 1.0f);

            // Right
            gl.glColor3fv(colordroite.buffer());     // Red
            gl.glVertex3f(0.0f, 1.0f, 0.0f);
            gl.glVertex3f(1.0f, -1.0f, 1.0f);
            gl.glVertex3f(1.0f, -1.0f, -1.0f);

            // Back
            gl.glColor3fv(colorderriere.buffer());     // Red
            gl.glVertex3f(0.0f, 1.0f, 0.0f);
            gl.glVertex3f(1.0f, -1.0f, -1.0f);
            gl.glVertex3f(-1.0f, -1.0f, -1.0f);

            // Left
            gl.glColor3fv(colorgauche.buffer());       // Red
            gl.glVertex3f(0.0f, 1.0f, 0.0f);
            gl.glVertex3f(-1.0f,-1.0f,-1.0f);
            gl.glVertex3f(-1.0f,-1.0f, 1.0f);
            gl.glEnd();   // Done drawing the pyramid

            gl.glBegin(GL2.GL_QUADS);
            gl.glColor3fv(collordessous.buffer());
            gl.glVertex3f(-1.0f, -1.0f, 1.0f);
            gl.glVertex3f(1.0f, -1.0f,1.0f);
            gl.glVertex3f(1.0f, -1.0f, -1.0f);
            gl.glVertex3f(-1.0f, -1.0f, -1.0f);
            gl.glEnd();   // Done drawing the pyramid

        }


}
