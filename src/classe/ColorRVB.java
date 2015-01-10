package classe;

import java.awt.*;
import java.io.Serializable;
import java.nio.FloatBuffer;

/**
 * Shapes
 * Created by Theo on 21/12/2014 for Ide3DProject.
 */
public class ColorRVB implements Serializable {
    public float R;
    public float V;
    public float B;


    public ColorRVB(float R, float V, float B) {
        this.R = R;
        this.V = V;
        this.B = B;
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
    public Color color(){ return new Color(R,V,B);}
    public FloatBuffer buffer(){
        return   FloatBuffer.wrap(new float[]{R,V,B});
    }
}
