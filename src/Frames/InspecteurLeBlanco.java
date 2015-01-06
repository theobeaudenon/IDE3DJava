package Frames;

import buttons.ColorButton;
import buttons.RotateButton;
import buttons.ToolProjectButton;
import classe.ColorRVB;
import classe.Forme;
import classe.Scene;
import utils.RightClicMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Boufle on 06/01/15.
 */
public class InspecteurLeBlanco extends JInternalFrame implements MouseListener{

        private JPanel top = new JPanel(new GridLayout(10,1,1,3));
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
            this.pack();
            this.setVisible(true);
            setSize(300, 465);
            setLocation(0, 465);
            top.setBackground(new Color(60, 63, 65));
            this.add(top);


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

    public void setModule(final Forme module) {
        top.removeAll();
        top.repaint();
        top.setPreferredSize(this.getSize());


        final JTextField name = new JTextField(module.getName());
        name.setBackground(new Color(45,48,50));
        name.setForeground(Color.WHITE);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setCaretColor(Color.WHITE);
        name.setBorder(BorderFactory.createEmptyBorder());
        name.setFont(new Font("Courier", Font.BOLD, 21));
        name.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            public void insertUpdate(DocumentEvent e) {
                warn();
            }


            public void warn() {

                parent.log("Renomage en : " + name.getText());
                module.setName(name.getText());
                parent.refreshTree();
            }
        });
        top.add(name);



        for (final ColorRVB color : module.params()){
            final ColorButton chooseButton = new ColorButton("Choix Couleur" );
            chooseButton.setForeground(color.color());
            chooseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Color backgroundColor = JColorChooser.showDialog(parent,
                            "Choisir votre couleur",color.color());
                    if(backgroundColor != null){
                        parent.log(backgroundColor.toString());
                        color.setColor(backgroundColor);
                        chooseButton.setForeground(backgroundColor);
                    }
                }
            });
            top.add(chooseButton);
        }



    }

    public void setModule(Scene module) {

    }


    public void remModule() {
        top.removeAll();
        top.repaint();
        top.setPreferredSize(this.getSize());
    }
}
