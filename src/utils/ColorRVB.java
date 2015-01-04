package utils;

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


    public float getR() {
        return R;
    }

    public void setR(float r) {
        this.R = r;
    }

    public float getV() {
        return V;
    }

    public void setV(float v) {
        V = v;
    }

    public float getB() {
        return B;
    }

    public void setB(float b) {
        B = b;
    }


    public FloatBuffer buffer(){
        return   FloatBuffer.wrap(new float[]{R,V,B});
    }
}
