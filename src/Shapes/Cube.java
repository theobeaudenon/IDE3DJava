package Shapes;

import javax.media.opengl.GL2;

public class Cube {

    // Moitié de la longueur d'un coté, simplifie les calculs
    private float tailleSur2;
    private ColorRVB colorderiere;
    private ColorRVB colordevant;
    private ColorRVB colordroite;
    private ColorRVB colorgauche;
    private ColorRVB colorhaut;
    private ColorRVB colorbas;
    // Centre du Cube dans le repere global, simplifie la translation
    private int [] position;

    public Cube(float taille, int x, int y, int z, ColorRVB colorderiere, ColorRVB colordevant, ColorRVB colordroite, ColorRVB colorgauche, ColorRVB colorhaut, ColorRVB colorbas){
        tailleSur2 = taille;
        this.colorderiere = colorderiere;
        this.colordevant = colordevant;
        this.colordroite = colordroite;
        this.colorgauche = colorgauche;
        this.colorhaut = colorhaut;
        this.colorbas = colorbas;
        position = new int [3];
        position[0] = x;
        position[1] = y;
        position[2] = z;

    }



    public void draw(GL2 gl){

        // Dessin de 6 quadrilateres : {4 vertex = 1 quadrilatère}
        gl.glBegin(GL2.GL_QUADS);

        // le quadrilatere de devant et de derriere, blanc
        gl.glColor3fv(colordevant.buffer());
        gl.glVertex3f(position[0]-tailleSur2, position[1]-tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]-tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]+tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]+tailleSur2, position[2]+tailleSur2);

        gl.glColor3fv(colorderiere.buffer());
        gl.glVertex3f(position[0]-tailleSur2, position[1]-tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]-tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]+tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]+tailleSur2, position[2]-tailleSur2);

        // le quadrilatere de gauche et de droite, gris
        gl.glColor3fv(colordroite.buffer());
        gl.glVertex3f(position[0]-tailleSur2, position[1]-tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]-tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]+tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]+tailleSur2, position[2]+tailleSur2);

        gl.glColor3fv(colorgauche.buffer());
        gl.glVertex3f(position[0]+tailleSur2, position[1]-tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]-tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]+tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]+tailleSur2, position[2]+tailleSur2);

        // le quadrilatere du haut et du bas, bleu
        gl.glColor3fv(colorhaut.buffer());
        gl.glVertex3f(position[0]-tailleSur2, position[1]+tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]+tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]+tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]+tailleSur2, position[2]+tailleSur2);

        gl.glColor3fv(colorbas.buffer());
        gl.glVertex3f(position[0]-tailleSur2, position[1]-tailleSur2, position[2]+tailleSur2);
        gl.glVertex3f(position[0]-tailleSur2, position[1]-tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]-tailleSur2, position[2]-tailleSur2);
        gl.glVertex3f(position[0]+tailleSur2, position[1]-tailleSur2, position[2]+tailleSur2);

        gl.glEnd();

    }

}