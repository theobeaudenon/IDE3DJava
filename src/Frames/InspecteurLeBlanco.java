package Frames;

import buttons.PinButton;
import buttons.RotateButton;
import buttons.ToolProjectButton;
import classe.Forme;
import classe.Projet;
import classe.Scene;
import utils.RightClicMenu;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Boufle on 06/01/15.
 */
public class InspecteurLeBlanco extends JInternalFrame implements MouseListener{

        private JPanel top = new JPanel();
        private ToolProjectButton toolProjectButton = new ToolProjectButton("");
        private RotateButton refresh = new RotateButton("");
        int posX ;
        int posY ;
        private int compteurClic = 1;
        private RightClicMenu rightClicMenu = new RightClicMenu();


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
        private InternalFrameDemo parent;

        //

        public InspecteurLeBlanco( final InternalFrameDemo parent){


            super("Project",
                    false, //resizable
                    true, //closable
                    true, //maximizable
                    true);//iconifiable
            this.parent = parent;
            //create the root node
            ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

            this.setContentPane(rightClicMenu);
            setDragable(false);
            setBorder(BorderFactory.createLineBorder(Color.black));
            setOpaque(false);
            getContentPane().setBackground(new Color(60, 63, 65));
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Project Explorer");
            this.pack();
            this.setVisible(true);
            setSize(300,465);
            setLocation(0, 465);


        }


    public void refresh(){
        refresh.doClick();
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
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
