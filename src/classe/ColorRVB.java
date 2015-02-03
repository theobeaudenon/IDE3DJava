package classe;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.nio.FloatBuffer;

/**
 * Shapes
 * Created by Theo on 21/12/2014 for Ide3DProject.
 */
public class ColorRVB implements Serializable {
    public float R;
    public float V;
    public float B;
    public Integer texture = null;


    public ColorRVB(float R, float V, float B) {
        this.R = R;
        this.V = V;
        this.B = B;
    }


    public ColorRVB(Integer name) {
        texture = name;

    }

    public Integer getTexture() {
        return texture;
    }

    public boolean isTexture(){
        if(texture != null) {return true; }else{ return false;}
    }

    public void setR(float r) {
        this.R = r;
    }

    public void setV(float v) {
        V = v;
    }

    public void setB(float b) {
        B = b;
    }
    public void setColor(Color d){
        setR((1.0f/255)*d.getRed());
        setV((1.0f/255)*d.getGreen());
        setB((1.0f/255)*d.getBlue());
    }

    public void setTexture(Integer d){
         texture = d;
    }
    public Color color(){
        return new Color(R,V,B);

    }
    public FloatBuffer buffer(){


        return   FloatBuffer.wrap(new float[]{R,V,B});
    }
}
