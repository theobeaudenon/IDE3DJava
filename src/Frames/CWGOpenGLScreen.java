package Frames;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;

public class CWGOpenGLScreen extends JInternalFrame implements GLEventListener {

    public static void main(String[] args) {
        new CWGOpenGLScreen().setVisible(true);
    }

    private static final long serialVersionUID = 635066680731362587L;

    private static final   String TAG = "CWGOpenGLScreen";

    private GLCanvas mCanvas;
    private long fpsLast = System.currentTimeMillis();
    private FPSAnimator animator;
    public CWGOpenGLScreen(){
        super("Project",
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        this.setTitle(TAG);
        this.setSize(1200,930);
        this.setLayout(new BorderLayout());
        this.setLocation(300,0);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        CWGSetupGL();
        this.setVisible(true);

//    CWGDebug.info(TAG, "Window created!");
        animator = null;
    }
    private void CWGSetupGL(){
        GLCapabilities mCaps = new GLCapabilities(null);
        mCaps.setHardwareAccelerated(true);
        mCaps.setDoubleBuffered(true);

        mCanvas = new GLCanvas(mCaps);
        mCanvas.addGLEventListener(this);

        this.add(mCanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(mCanvas,60,true);

        animator.start();

    }

    public void CWGDrawScene(GLAutoDrawable drawable)
    {
        CWGCalculateFPS();

        GL2 gl = drawable.getGL().getGL2();
        // gl.glViewport(0, 0, 300, 300); //Possibly use to move around object
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        gl.glPushMatrix();
        //gl.glTranslatef(-1.5f,1.5f,0.0f);                  // Move left 1.5 units, up 1.5 units, and back 8 units

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3d(0, 2, 0);
// Begin drawing triangles
        gl.glVertex3f( 0.0f, 1.0f, 0.0f);                   // Top vertex
        gl.glVertex3f(-1.0f,-1.0f, 0.0f);                   // Bottom left vertex
        gl.glVertex3f( 1.0f,-1.0f, 0.0f);                   // Bottom right vertex
        gl.glEnd();                                         // Finish drawing triangles
        gl.glPopMatrix();

    }

    public void CWGCalculateFPS(){
      try {
          this.setTitle(TAG + " [" + 1000 / (System.currentTimeMillis() - fpsLast) + "]");
      }catch (Exception e){};
        fpsLast = System.currentTimeMillis();
    }

    public void init(GLAutoDrawable drawable){
        /*GL2 gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 1, 0, 1, -1, 1);
        */
//        CWGDebug.info(TAG, "Init called!");
    }
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){
       // GL2 gl = drawable.getGL().getGL2();
        //gl.glViewport(x, y, width, height);
    }
    //public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged){}
    public void display(GLAutoDrawable drawable){ CWGDrawScene(drawable); }
    public void dispose(GLAutoDrawable drawable){
        animator.stop();
        mCanvas.getAnimator().stop();
    }

}