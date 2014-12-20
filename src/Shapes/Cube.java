package Shapes;

import javax.media.opengl.GL2;

public class Cube {

    // Moitié de la longueur d'un coté, simplifie les calculs
    private float tailleSur2;
    // Centre du Cube dans le repere global, simplifie la translation
    private int [] position;

    public Cube(float taille, int x, int y, int z){
        tailleSur2 = taille;
        position = new int [3];
        position[0] = x;
        position[1] = y;
        position[2] = z;
    }

    public void draw(GL2 gl){

        // Dessin de 6 quadrilateres : {4 vertex = 1 quadrilatère}
        gl.glBegin(GL2.GL_QUADS);

        // le quadrilatere de devant et de derriere, blanc
        gl.glColor3f(1f, 1f, 1f);
        gl.glVertex3f(position[0]-tailleSur2, position[1]-tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]-tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]+tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]+tailleSur2, position[2]+tailleSur2);

        gl.glVertex3f(position[0]-tailleSur2, position[1]-tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]-tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]+tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]+tailleSur2, position[2]-tailleSur2);

        // le quadrilatere de gauche et de droite, gris
        gl.glColor3f(0.5f, 0.5f, 0.5f);
        gl.glVertex3f(position[0]-tailleSur2, position[1]-tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]-tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]+tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]+tailleSur2, position[2]+tailleSur2);

        gl.glVertex3f(position[0]+tailleSur2, position[1]-tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]-tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]+tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]+tailleSur2, position[2]+tailleSur2);

        // le quadrilatere du haut et du bas, bleu
        gl.glColor3f(0f, 0f, 1f);
        gl.glVertex3f(position[0]-tailleSur2, position[1]+tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]+tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]+tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]+tailleSur2, position[2]+tailleSur2);

        gl.glVertex3f(position[0]-tailleSur2, position[1]-tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]-tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]-tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]-tailleSur2, position[2]+tailleSur2);

        gl.glEnd();

    }

}