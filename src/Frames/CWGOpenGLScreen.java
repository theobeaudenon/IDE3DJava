package Frames;

import java.awt.*;
import java.awt.event.*;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import buttons.PinButton;
import buttons.RevertPlaceButton;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;

public class CWGOpenGLScreen extends JInternalFrame implements GLEventListener {

    public static void main(String[] args) {
        new CWGOpenGLScreen().setVisible(true);
    }

    private static final long serialVersionUID = 635066680731362587L;
    private PinButton pinButton = new PinButton("");
    private JPanel top = new JPanel();
    private static final   String TAG = "CWGOpenGLScreen";
    private GLCanvas mCanvas;
    private long fpsLast = System.currentTimeMillis();
    private FPSAnimator animator;
    private RevertPlaceButton revertPlaceButton = new RevertPlaceButton("");
    int posX ;
    int posY ;
    private int compteurClic = 1;
    private int test;



    // Listener pin not pin

    MouseListener ml = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            posX = e.getX();
            posY = e.getY();
        }
    };

    MouseMotionListener m2 = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
            int depX = e.getX() - posX;
            int depY = e.getY() - posY;
            setLocation(getX() + depX, getY() + depY);
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    };

    //

    public CWGOpenGLScreen(){
        super("Project",
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable

        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

        this.setLayout(new BorderLayout());

        setDragable(false);
        top.setBackground(new Color(45, 48, 50));
        top.setBorder(new LineBorder(Color.BLACK));
        top.setBorder(new LineBorder(Color.BLACK));
        pinButton.setPreferredSize(new Dimension(16, 16));
        top.setBorder(BorderFactory.createLineBorder(Color.black));
        top.add(pinButton);
        revertPlaceButton.setPreferredSize(new Dimension(16,16));
        top.add(revertPlaceButton);
        this.add(top, BorderLayout.NORTH);
        this.setTitle(TAG);
        this.setSize(1200, 930);
        this.setLocation(300,0);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBorder(BorderFactory.createLineBorder(Color.black));
        CWGSetupGL();
        this.setVisible(true);


        pinButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compteurClic++;

                if (compteurClic%2 == 0){
                    setDragable(true);
                    setBorder(BorderFactory.createRaisedBevelBorder());

                }
                else {
                    setDragable(false);
                    setBorder(BorderFactory.createLineBorder(Color.black));
                }
            }
        });

        revertPlaceButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLocation(300,0);
                setDragable(false);
                setBorder(BorderFactory.createLineBorder(Color.black));
            }
        });

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
        gl.glVertex3f(0.0f, 1.0f, 0.0f);                   // Top vertex
        gl.glVertex3f(-1.0f,-1.0f, 0.0f);                   // Bottom left vertex
        gl.glVertex3f(1.0f, -1.0f, 0.0f);                   // Bottom right vertex
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

    public void setDragable(boolean test){



        if (test){
            top.addMouseListener(ml);
            top.addMouseMotionListener(m2);
        }
        else {
            top.removeMouseListener(ml);
            top.removeMouseMotionListener(m2);

        }
    }

}