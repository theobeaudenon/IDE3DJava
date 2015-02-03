package Shapes;

import classe.ColorRVB;
import classe.DataConf;
import classe.Forme;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Shapes
 * Created by Theo on 01/02/2015 for Ide3DProject.
 */
public class Terrain  extends Forme implements Serializable {

    public ArrayList<ColorRVB> couleurs = new ArrayList<ColorRVB>() ;
    public ArrayList<DataConf> data = new ArrayList<DataConf>() ;

    public Terrain(String s, float plusx, float moinx , float plusz, float moinsz, ColorRVB colordevant ){
        super(s);
        data.add(new DataConf("+x",plusx));
        data.add(new DataConf("-x",moinx));
        data.add(new DataConf("+z",plusz));
        data.add(new DataConf("-z",moinsz));
        couleurs.add(colordevant);

    }

    @Override
    public void draw(GL2 gl) {

        if (couleurs.get(0).isTexture()){

            //gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);

            gl.glBindTexture(GL2.GL_TEXTURE_2D, 1);
        }else {
            gl.glColor3fv(couleurs.get(0).buffer());
        }
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3f(-data.get(3).getValue(),-0.001f,-data.get(1).getValue());
        gl.glVertex3f( -data.get(3).getValue(),-0.001f,data.get(0).getValue());
        gl.glVertex3f(data.get(2).getValue(),-0.001f,data.get(0).getValue());
        gl.glVertex3f(data.get(2).getValue(),-0.001f, -data.get(1).getValue());

        gl.glEnd();
        if (couleurs.get(0).isTexture()) {

            gl.glDisable(GL2.GL_TEXTURE_2D);
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
