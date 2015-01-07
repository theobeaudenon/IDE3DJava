package Shapes;

import classe.DataConf;
import classe.Forme;
import classe.ColorRVB;
import classe.ParamsForms;

import javax.media.opengl.GL2;
import java.io.Serializable;
import java.util.ArrayList;

public class Cube extends Forme implements Serializable {

    // Moitié de la longueur d'un coté, simplifie les calculs
    public ArrayList<ColorRVB> couleurs = new ArrayList<ColorRVB>() ;
    public ArrayList<DataConf> data = new ArrayList<DataConf>() ;

    // Centre du Cube dans le repere global, simplifie la translation
    private int [] position;



    public Cube(String s, float taille, int x, int y, int z, ColorRVB colorderiere, ColorRVB colordevant, ColorRVB colordroite, ColorRVB colorgauche, ColorRVB colorhaut, ColorRVB colorbas){
        super(s);
        data.add(new DataConf("rayon",taille));
        couleurs.add(colorderiere);
        couleurs.add(colordevant);
        couleurs.add(colordroite);
        couleurs.add(colorgauche);
        couleurs.add(colorhaut);
        couleurs.add(colorbas);
        position = new int [3];
        position[0] = x;
        position[1] = y;
        position[2] = z;

    }


    @Override
    public void draw(GL2 gl){

        // Dessin de 6 quadrilateres : {4 vertex = 1 quadrilatère}
        gl.glBegin(GL2.GL_QUADS);

        // le quadrilatere de devant et de derriere, blanc
        gl.glColor3fv(couleurs.get(0).buffer());
        gl.glVertex3f(position[0]-data.get(0).getValue(), position[1]-data.get(0).getValue(), position[2]+data.get(0).getValue());
        gl.glVertex3f(position[0]+data.get(0).getValue(), position[1]-data.get(0).getValue(), position[2]+data.get(0).getValue());
        gl.glVertex3f(position[0]+data.get(0).getValue(), position[1]+data.get(0).getValue(), position[2]+data.get(0).getValue());
        gl.glVertex3f(position[0]-data.get(0).getValue(), position[1]+data.get(0).getValue(), position[2]+data.get(0).getValue());

        gl.glColor3fv(couleurs.get(1).buffer());
        gl.glVertex3f(position[0]-data.get(0).getValue(), position[1]-data.get(0).getValue(), position[2]-data.get(0).getValue());
        gl.glVertex3f(position[0]+data.get(0).getValue(), position[1]-data.get(0).getValue(), position[2]-data.get(0).getValue());
        gl.glVertex3f(position[0]+data.get(0).getValue(), position[1]+data.get(0).getValue(), position[2]-data.get(0).getValue());
        gl.glVertex3f(position[0]-data.get(0).getValue(), position[1]+data.get(0).getValue(), position[2]-data.get(0).getValue());

        // le quadrilatere de gauche et de droite, gris
        gl.glColor3fv(couleurs.get(2).buffer());
        gl.glVertex3f(position[0]-data.get(0).getValue(), position[1]-data.get(0).getValue(), position[2]+data.get(0).getValue());
        gl.glVertex3f(position[0]-data.get(0).getValue(), position[1]-data.get(0).getValue(), position[2]-data.get(0).getValue());
        gl.glVertex3f(position[0]-data.get(0).getValue(), position[1]+data.get(0).getValue(), position[2]-data.get(0).getValue());
        gl.glVertex3f(position[0]-data.get(0).getValue(), position[1]+data.get(0).getValue(), position[2]+data.get(0).getValue());

        gl.glColor3fv(couleurs.get(3).buffer());
        gl.glVertex3f(position[0]+data.get(0).getValue(), position[1]-data.get(0).getValue(), position[2]+data.get(0).getValue());
        gl.glVertex3f(position[0]+data.get(0).getValue(), position[1]-data.get(0).getValue(), position[2]-data.get(0).getValue());
        gl.glVertex3f(position[0]+data.get(0).getValue(), position[1]+data.get(0).getValue(), position[2]-data.get(0).getValue());
        gl.glVertex3f(position[0]+data.get(0).getValue(), position[1]+data.get(0).getValue(), position[2]+data.get(0).getValue());

        // le quadrilatere du haut et du bas, bleu
        gl.glColor3fv(couleurs.get(4).buffer());
        gl.glVertex3f(position[0]-data.get(0).getValue(), position[1]+data.get(0).getValue(), position[2]+data.get(0).getValue());
        gl.glVertex3f(position[0]-data.get(0).getValue(), position[1]+data.get(0).getValue(), position[2]-data.get(0).getValue());
        gl.glVertex3f(position[0]+data.get(0).getValue(), position[1]+data.get(0).getValue(), position[2]-data.get(0).getValue());
        gl.glVertex3f(position[0]+data.get(0).getValue(), position[1]+data.get(0).getValue(), position[2]+data.get(0).getValue());

        gl.glColor3fv(couleurs.get(5).buffer());
        gl.glVertex3f(position[0]-data.get(0).getValue(), position[1]-data.get(0).getValue(), position[2]+data.get(0).getValue());
        gl.glVertex3f(position[0]-data.get(0).getValue(), position[1]-data.get(0).getValue(), position[2]-data.get(0).getValue());
        gl.glVertex3f(position[0]+data.get(0).getValue(), position[1]-data.get(0).getValue(), position[2]-data.get(0).getValue());
        gl.glVertex3f(position[0]+data.get(0).getValue(), position[1]-data.get(0).getValue(), position[2]+data.get(0).getValue());

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