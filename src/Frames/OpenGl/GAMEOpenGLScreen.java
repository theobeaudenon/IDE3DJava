package Frames.OpenGl;

import Frames.Dialog.PopupObjectAdd;
import Frames.InternalFrameDemo;
import Shapes.Cone;
import buttons.CloseButton;
import buttons.RevertPlaceButton;
import buttons.RotateButton;
import classe.*;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.awt.TextRenderer;
import utils.RandomUtils;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Frames
 * Created by Theo on 05/01/2015 for Ide3DProject.
 */
public class GAMEOpenGLScreen extends JInternalFrame implements GLEventListener,KeyListener , InternalFrameListener {

    private final BoLASoupe player;
    private boolean up = false;
    private boolean down= false;
    private boolean right= false;
    private boolean left= false;
    Robot robot;
    public float distance = 3f;
    private final InternalFrameDemo parent;

    private float velocityY = 0.0f;
    private double gravity = 0.1;
    private boolean onGround = false;
    private boolean space = false;
    private FPSMouseControler fpsMouseController;
    private double rotationyx = 0;
    private double rotationy = 0;

    public static void main(String[] args) {
        //new CWGOpenGLScreen(userObject).setVisible(true);
    }

    private JPanel top = new JPanel();
    private static final   String TAG = "CWGOpenGLScreen";
    private GLCanvas mCanvas = new GLCanvas();
    private long fpsLast = System.currentTimeMillis();
    private FPSAnimator animator;
    private CloseButton closeButton = new CloseButton("");
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
    public Scene d ;

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

    public GAMEOpenGLScreen(Scene userObject, InternalFrameDemo internalFrameDemo, float spawnx, float spawny, float spawnz){

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
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        top.setBorder(new LineBorder(Color.BLACK));
        top.setBorder(BorderFactory.createLineBorder(Color.black));
        closeButton.setPreferredSize(new Dimension(16, 16));
        top.add(closeButton);
        this.add(top, BorderLayout.NORTH);
        this.setTitle(d.getName());
        this.setSize(800, 675);
        this.setLocation(310, 5);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBorder(BorderFactory.createLineBorder(Color.black));
        CWGSetupGL();
        this.setVisible(true);


        Cone forme = new Cone("player", new ColorRVB(0.2f, 1f, 0.9f), new ColorRVB(0.3f, 0.9f, 0.5f), 1.0f,1.0f, 1.0f);
        player = new BoLASoupe( forme,spawnx,spawny,spawnz, null,"player");




        final GAMEOpenGLScreen open = this;
        closeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    parent.removeframe(open);
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
        try {
            fpsMouseController = new FPSMouseControler();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        fpsMouseController.setCanvas(mCanvas);
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
           rotationy+=fpsMouseController.getDmy()*0.11 ;
            rotationyx+=fpsMouseController.getDmx()*0.11 ;
        fpsMouseController.setDmx(0);
        fpsMouseController.setDmy(0);

        float yRadians = player.getY() / 180f / 3.14159f;


        float camerax = (float) (-distance * cos(rotationyx)  + player.getX());
        float cameraz = (float) (-distance * sin(rotationyx) + player.getZ());


        glu.gluLookAt(cameraz ,
                camerax  ,
                -distance     ,
            player.getX(),
            player.getY(),
            player.getZ(),
                0    ,  0 , 1 );



        // Rotation de la matrice courante de l'angle alpha autour de l'axe x (0,0,1)


        /**
         *
         *
         glu.gluLookAt(cameraX, cameraY, cameraZ,
                player.getX(),player.getY(),player.getZ(),
                0f, 1f, 0f
        );



         gl.glColor3f(.3f,.3f,.3f);
         gl.glBegin(GL2.GL_QUADS);
         gl.glVertex3f( 0f,-0.001f, 0f);
         gl.glVertex3f( 0f,-0.001f,10f);
         gl.glVertex3f(10f,-0.001f,10f);
         gl.glVertex3f(10f,-0.001f, 0f);
         gl.glEnd();

        //Affichage de la grille
        gl.glBegin(GL2.GL_LINES);
        for(int i=-0;i<=10;i++) {
            if (i==0) { gl.glColor3f(.6f,.3f,.3f); } else { gl.glColor3f(.64f,.64f,.64f); };
            gl.glVertex3f(i,0,0);
            gl.glVertex3f(i,0,10);
            if (i==0) { gl.glColor3f(.3f,.3f,.6f); } else { gl.glColor3f(.64f,.64f,.64f); };
            gl.glVertex3f(0,0,i);
            gl.glVertex3f(10,0,i);
        };
        gl.glColor3f(.3f,.6f,.3f);
        gl.glVertex3f(0,0,0);
        gl.glVertex3f(0,10,0);
        gl.glColor3f(.64f,.64f,.64f);
        gl.glEnd();

         */

        //on ajoute lelement bougable
        velocityY -= gravity;
        player.setY(player.getY() + velocityY);
      //  player.setX(player.getX() + velocityX);

        if(space){
            if(onGround)
            {
                velocityY = 2.0f;
                onGround = false;
            }
            space = false;
        }else {

            if(player.getY() <= 0f)
            {
                player.setY(0f);
                velocityY = 0.0f;
                onGround = true;
            }
        }




        gl.glTranslatef(player.getX(),player.getY(),player.getZ());
        player.getForme().draw(gl);
        gl.glTranslatef(-player.getX(),-player.getY(),-player.getZ());

        if(right){player.setX(player.getX()+ 0.5f) ;}
        if(left){player.setX(player.getX() -0.5f); }

        if(up){player.setZ(player.getZ()- 0.5f); }
        if(down){player.setZ(player.getZ()+ 0.5f);}

        /* recuperation de lobjet et instantiation */
        for (BoLASoupe var : d.getFormes()){
            // System.out.println(d.getFormes());
            if(var.getSelected()){
                float x = RandomUtils.randFloat(-0.1f, 0.1f);
                float y = RandomUtils.randFloat(-0.1f, 0.1f);
                float z = RandomUtils.randFloat(-0.1f, 0.1f);
                gl.glTranslatef(var.getX()+x,var.getY()+y,var.getZ()+z);
                var.getForme().draw(gl);
                gl.glTranslatef(-var.getX()-x,-var.getY()-y,-var.getZ()-z);
            }else {
                gl.glTranslatef(var.getX(),var.getY(),var.getZ());
                var.getForme().draw(gl);
                gl.glTranslatef(-var.getX(),-var.getY(),-var.getZ());

            }

        }



        // Tous les dessins utltérieurs subiront la transformation : Dessin d'un cube
        TextRenderer renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 16));

        renderer.beginRendering(mCanvas.getWidth(), mCanvas.getHeight());
        // optionally set the color
        renderer.setColor(1.0f, 1.0f, 1.0f, 0.9f);

        renderer.draw(String.valueOf(drawable.getAnimator().getLastFPS()), 10, 10);
        renderer.draw(String.valueOf("X : "+player.getX() +"   Y : " + player.getY() +"   Z : " +player.getZ()), 10, 26);
        renderer.draw(String.valueOf("Pitch : "+ distance + "  Entry : "+ d.getFormes().size()+"   RotateX : " +camerax+"   ratateZ : " +cameraz), 10, 42);
        // ... more draw commands, color changes, etc.
        renderer.endRendering();



        // Tous les dessins utltérieurs subiront la transformation : Dessin d'un cube


    }

    public void init(GLAutoDrawable drawable){
        // Récupération du contexte en GL2
        GL2 gl = drawable.getGL().getGL2();
        // Initialisation de l'utilitaire
        glu = new GLU();
        // Remplissage du contexte avec du NOIR
        gl.glClearColor(0.29f, 0.29f, 0.29f, 0.0f);
        // Configuration la profondeur au maximum
        gl.glClearDepth(100.0f);
        // Autorisation de faire un rendu avec une perspective
        gl.glEnable(GL2.GL_DEPTH_TEST);
        drawable.getAnimator().setUpdateFPSFrames(3, null);
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
                    distance +=0.2f;
                }else {
                    distance -=0.2f;
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
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_SPACE:
                space = true;
                break;
            case KeyEvent.VK_ESCAPE:
                fpsMouseController.removeCanvas(mCanvas);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_SPACE:
                space = false;
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
        GAMEOpenGLScreen.this.setBorder(new LineBorder(new Color(69, 111, 175), 1, true));
        fpsMouseController.setCanvas(mCanvas);
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        GAMEOpenGLScreen.this.setBorder(null);
    }
}
