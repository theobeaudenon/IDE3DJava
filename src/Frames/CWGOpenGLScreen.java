package Frames;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import javax.swing.border.LineBorder;

import Shapes.ColorRVB;
import Shapes.Cube;
import Shapes.Forme;
import buttons.PinButton;
import buttons.RevertPlaceButton;
import buttons.RotateButton;
import com.jogamp.opengl.util.FPSAnimator;

public class CWGOpenGLScreen extends JInternalFrame implements GLEventListener,KeyListener {

    private boolean automoving= true;

    public static void main(String[] args) {
        new CWGOpenGLScreen().setVisible(true);
    }

    private static final long serialVersionUID = 635066680731362587L;
    private PinButton pinButton = new PinButton("");
    private JPanel top = new JPanel();
    private static final   String TAG = "CWGOpenGLScreen";
    private GLCanvas mCanvas = new GLCanvas();
    private long fpsLast = System.currentTimeMillis();
    private FPSAnimator animator;
    private RevertPlaceButton revertPlaceButton = new RevertPlaceButton("");
    private RotateButton rotateButton = new RotateButton("");
    int posX ;
    int posY ;
    private int compteurClic = 1;
    private int test;
    // Utilitaire donnant accès aux commandes bas niveau d'OpenGL
    private GLU glu;
    // Angle courant de rotation sur l'axe X
    public float alphaX=0f;
    // Angle courant de rotation sur l'axe Y
    public float alphaY=0f;
    // Angle courant de rotation sur l'axe Z
    public float alphaZ=0f;
    public Forme d = new Forme();


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

          /* Mise en place de la forme sauvegardé pour exemple */
        Cube e = new Cube(1.0f, 0, 0, 0, new ColorRVB(0.9f,0.9f,0.9f), new ColorRVB(0.9f,0.42f,0.1f),  new ColorRVB(0.1f,0f,1f), new ColorRVB(0.4f,1f,0.7f), new ColorRVB(0f,0f,0.5f), new ColorRVB(0.6f,0.5f,0.1f));
        d.setClasse(e.getClass());
        d.setObj(e);



        setDragable(false);
        top.setBackground(new Color(45, 48, 50));
        top.setBorder(new LineBorder(Color.BLACK));
        top.setBorder(new LineBorder(Color.BLACK));
        pinButton.setPreferredSize(new Dimension(16, 16));
        top.setBorder(BorderFactory.createLineBorder(Color.black));
        top.add(pinButton);
        revertPlaceButton.setPreferredSize(new Dimension(16, 16));
        rotateButton.setPreferredSize(new Dimension(16, 16));
        top.add(revertPlaceButton);
        top.add(rotateButton);
        this.add(top, BorderLayout.NORTH);
        this.setTitle(TAG);
        this.setSize(1200, 930);
        this.setLocation(300, 0);
        this.addKeyListener(this);
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



        /* recuperation de lobjet et instantiation */
        try {
            d.getObj().getClass().getMethod("draw" , GL2.class)   .invoke(d.getObj(),gl);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        }


        // Tous les dessins utltérieurs subiront la transformation : Dessin d'un cube

        //gl.glColor3f(0.2f,0f,0f);
       // GLUT s = new GLUT();
       // s.glutSolidDodecahedron();


    }
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void CWGCalculateFPS(){
        try {
            this.setTitle(TAG + " [" + 1000 / (System.currentTimeMillis() - fpsLast) + "]");
        }catch (Exception e){};
        fpsLast = System.currentTimeMillis();
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
                alphaX -= 1.9;
                break;
            case KeyEvent.VK_DOWN:
                alphaX += 1.9;
                automoving = false;
                break;
            case KeyEvent.VK_RIGHT:
                alphaY += 1.9;
                automoving = false;
                break;
            case KeyEvent.VK_LEFT:
                alphaY -= 1.9;
                automoving = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}