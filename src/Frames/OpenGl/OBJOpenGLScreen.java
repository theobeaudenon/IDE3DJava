package Frames.OpenGl;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

import Frames.InternalFrameDemo;
import buttons.CloseButton;
import classe.Forme;
import buttons.RevertPlaceButton;
import buttons.RotateButton;
import com.jogamp.opengl.util.FPSAnimator;

public class OBJOpenGLScreen extends JInternalFrame implements GLEventListener,KeyListener, InternalFrameListener {

    private boolean automoving= true;
    private boolean up = false;
    private boolean down= false;
    private boolean right= false;
    private boolean left= false;
    private final InternalFrameDemo parent;

    private float rotationcameraX = 0f;
    private float rotationcameraY = 0f;
    private float rotationcameraZ = 0f;
    private float cameraX = 4f;
    private float cameraY = 6f;
    private float cameraZ = 20f;
    private JPanel inspector = new JPanel(new GridLayout(2, 1));
    private JPanel top = new JPanel();
    private static final   String TAG = "CWGOpenGLScreen";
    private GLCanvas mCanvas = new GLCanvas();
    private long fpsLast = System.currentTimeMillis();
    private FPSAnimator animator;
    private RevertPlaceButton revertPlaceButton = new RevertPlaceButton("");
    private CloseButton closeButton = new CloseButton("");
    private RotateButton rotateButton = new RotateButton("");
    int posX ;
    int posY ;
    // Utilitaire donnant accès aux commandes bas niveau d'OpenGL
    private GLU glu;
    // Angle courant de rotation sur l'axe X
    public float alphaX=0f;
    // Angle courant de rotation sur l'axe Y
    public float alphaY=0f;
    // Angle courant de rotation sur l'axe Z
    public float alphaZ=0f;
    public Forme d ;
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

    public OBJOpenGLScreen(Forme userObject, InternalFrameDemo internalFrameDemo){

        super("Project",
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        parent = internalFrameDemo;

        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

        this.setLayout(new BorderLayout());
        d =  userObject;


        setDragable(true);
        top.setBackground(new Color(45, 48, 50));
        inspector.setBackground(new Color(45, 48, 50));
        inspector.setBorder(new LineBorder(Color.BLACK));
        top.setBorder(new LineBorder(Color.BLACK));
        top.setBorder(BorderFactory.createLineBorder(Color.black));
        inspector.setBorder(BorderFactory.createLineBorder(Color.black));
        revertPlaceButton.setPreferredSize(new Dimension(16, 16));
        rotateButton.setPreferredSize(new Dimension(16, 16));
        closeButton.setPreferredSize(new Dimension(16, 16));
        top.add(revertPlaceButton);
        top.add(rotateButton);
        top.add(closeButton);
        this.add(top, BorderLayout.NORTH);
        this.add(inspector, BorderLayout.SOUTH);
        this.setTitle(d.getName());
        this.setSize(600, 475);
        this.setLocation(310, 5);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBorder(BorderFactory.createLineBorder(Color.black));
        CWGSetupGL();
        this.setVisible(true);
        addInternalFrameListener(this);



        revertPlaceButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLocation(300,0);
                setDragable(false);
                setBorder(BorderFactory.createLineBorder(Color.black));
            }
        });
        rotateButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (automoving) {
                    automoving = false;
                } else {
                    automoving = true;
                }
            }
        });
        final OBJOpenGLScreen open = this;
        closeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    open.dispose();
                    animator.stop();
                    mCanvas.getAnimator().stop();
                }catch (Exception ee){}
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
        mCanvas.addKeyListener(this);

        this.add(mCanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(mCanvas,60,true);

        animator.start();

    }

    public void CWGDrawScene(GLAutoDrawable drawable)
    {
        // Récupération du contexte en GL2
        GL2 gl = drawable.getGL().getGL2();
        // Réinitialisation de la scène (effacement des tampons)
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        // Reinitialisation de la matrice courante
        gl.glLoadIdentity();

        /* (Alt aux translations) Placement de la caméra au point (4,0,12)
        Direction vers l'origine de la scène (0,0,0)
        Inclinaison nulle car la vue suit l'axe vertical (y) */
        glu.gluLookAt(4f, 0f, 12f,
                0f, 0f, 0f,
                0f, 1f, 0f
        );
        glu.gluLookAt(cameraX, cameraY, cameraZ,
                rotationcameraX, rotationcameraY, rotationcameraZ,
                0f, 1f, 0f
        );

        // Rotation de la matrice courante de l'angle alpha autour de l'axe x (1,0,0)
        gl.glRotatef(alphaX,
                1f, 0f, 0f
        );

        // Rotation de la matrice courante de l'angle alpha autour de l'axe x (0,1,0)
        gl.glRotatef(alphaY,
                0f, 1f, 0f
        );

        // Rotation de la matrice courante de l'angle alpha autour de l'axe x (0,0,1)
        gl.glRotatef(alphaZ,
                0f, 0f, 1f
        );

        if(automoving){
            alphaY += 0.5;
            alphaX += 0.2;
        }
        if(up){alphaX -= 1.9;}
        if(down){alphaX += 1.9;}
        if(right){alphaY += 1.9;}
        if(left){alphaY -= 1.9;}
        /**

            gl.glColor3f(.3f,.3f,.3f);
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3f( 0f,-0.001f, 0f);
            gl.glVertex3f( 0f,-0.001f,10f);
            gl.glVertex3f(10f,-0.001f,10f);
            gl.glVertex3f(10f,-0.001f, 0f);
            gl.glEnd();

        */



        //Affichage de la grille
        gl.glBegin(GL2.GL_LINES);
        for(int i=-0;i<=10;i++) {
            if (i==0) { gl.glColor3f(.6f,.3f,.3f); } else { gl.glColor3f(.25f,.25f,.25f); };
            gl.glVertex3f(i,0,0);
            gl.glVertex3f(i,0,10);
            if (i==0) { gl.glColor3f(.3f,.3f,.6f); } else { gl.glColor3f(.25f,.25f,.25f); };
            gl.glVertex3f(0,0,i);
            gl.glVertex3f(10,0,i);
        };
        gl.glColor3f(.3f,.6f,.3f);
        gl.glVertex3f(0,0,0);
        gl.glVertex3f(0,10,0);
        gl.glColor3f(.25f,.25f,.25f);
        gl.glEnd();

        /* recuperation de lobjet et instantiation */

        d.draw(gl);



        // Tous les dessins utltérieurs subiront la transformation : Dessin d'un cube


    }

    public void init(GLAutoDrawable drawable){
        // Récupération du contexte en GL2
        GL2 gl = drawable.getGL().getGL2();
        // Initialisation de l'utilitaire
        glu = new GLU();
        // Remplissage du contexte avec du NOIR
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        // Configuration la profondeur au maximum
        gl.glClearDepth(1.0f);
        // Autorisation de faire un rendu avec une perspective
        gl.glEnable(GL2.GL_DEPTH_TEST);
        // Restriction de l'affichage aux éléments(Z<=Max)
        gl.glDepthFunc(GL2.GL_LEQUAL);
        // Correction pour la meilleure perspective
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        // Joli mélange de couleur, et lissage des textures
        gl.glShadeModel(GL2.GL_SMOOTH);


        mCanvas.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {

                if (e.getPreciseWheelRotation() > 0){
                    cameraX += -Math.sin(Math.toRadians(rotationcameraY)) * Math.cos(Math.toRadians(rotationcameraX)) * 1;
                    cameraZ += Math.cos(Math.toRadians(rotationcameraY)) * Math.cos(Math.toRadians(rotationcameraX)) * 1;
                    cameraY -= -Math.sin(Math.toRadians(rotationcameraX)) * 5;


                }else {
                    cameraX -= -Math.sin(Math.toRadians(rotationcameraY)) * Math.cos(Math.toRadians(rotationcameraX)) * 1;
                    cameraZ -= Math.cos(Math.toRadians(rotationcameraY)) * Math.cos(Math.toRadians(rotationcameraX)) * 1;
                    cameraY += -Math.sin(Math.toRadians(rotationcameraX)) * 1;

                }
            }
        });
    }
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){

        GL2 gl = drawable.getGL().getGL2();

        // Alternative à la division par zéro
        if (height == 0) height = 1;

        // Configuration de la zone d'affichage OpenGL
        gl.glViewport(0, 0, width, height);

        // Spécification d'une matrice de projection en perspective
        // Passage en mode définition de la PROJECTION
        gl.glMatrixMode(GL2.GL_PROJECTION);
        // Reinitialisation de la matrice courante
        gl.glLoadIdentity();
        // Angle d'observation en degrés, echelle entre largeur et hauteur, intervalle de projection
        glu.gluPerspective(45.0, (float)width/height, 0.1, 100.0);

        // Selection de la transformation point de vue
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        // Reinitialisation de la matrice courante
        gl.glLoadIdentity();
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                automoving = false;
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                automoving = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                automoving = false;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                automoving = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                automoving = false;
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                automoving = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                automoving = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                automoving = false;
                break;
        }
    }


    @Override
    public void internalFrameOpened(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {


    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        parent.log("Focus sur "+d.getName());
        parent.updateInspecteur(d);
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
       // parent.remInspecteur();
    }
}