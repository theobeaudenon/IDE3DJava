package Frames;

import Shapes.Forme;
import buttons.PinButton;
import buttons.RevertPlaceButton;
import buttons.RotateButton;
import buttons.ToolProjectButton;
import classe.Projet;
import utils.RightClicMenu;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Boufle on 16/12/14.
 */
public class TreeFrame extends JInternalFrame implements MouseListener {

   //BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
   // Component northPane = ui.getNorthPane();
   // MouseMotionListener[] motionListeners = (MouseMotionListener[]) northPane.getListeners(MouseMotionListener.class);
    private JPanel top = new JPanel();
    private ToolProjectButton toolProjectButton = new ToolProjectButton("");
    private RotateButton refresh = new RotateButton("");
    private PinButton pinButton = new PinButton("");
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
    private JTree tree;
    private DefaultMutableTreeNode root;

    //

    public TreeFrame(final Projet projet, final InternalFrameDemo parent){


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
        startup( projet);

        pinButton.setPreferredSize(new Dimension(16,16));
        top.add(pinButton);
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

        toolProjectButton.setPreferredSize(new Dimension(14,16));
        top.add(toolProjectButton);

        refresh.setPreferredSize(new Dimension(14,16));
        top.add(refresh);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                parent.log("Rafraîchissement du menu");
                root = new DefaultMutableTreeNode(projet.getNom());


                DefaultMutableTreeNode scene = new DefaultMutableTreeNode("scenes");
                root.add(scene);
                for(String p : projet.getScene())
                {
                    DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode(p);

                    scene.add(vegetableNode);
                }




                DefaultMutableTreeNode objets = new DefaultMutableTreeNode("Objets");
                root.add(objets);
                //create the child nodes



                for(Forme p : projet.getObj())
                {
                    DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode(p);

                    objets.add(vegetableNode);
                }

                    final DefaultTreeCellRenderer renderer =
                            (DefaultTreeCellRenderer)(tree.getCellRenderer());
                    renderer.setBackgroundNonSelectionColor(new Color(60,63,65));
                    renderer.setBackgroundSelectionColor(new Color(1, 126, 178));
                    renderer.setTextNonSelectionColor(Color.WHITE);
                    renderer.setTextSelectionColor(Color.WHITE);




                //tree.setForeground(Color.WHITE);
                tree.updateUI();
                tree.setCellRenderer(renderer);
            }
        });
        top.setBackground(new Color(45, 48, 50));
        top.setBorder(BorderFactory.createLineBorder(Color.black));

        add(top, BorderLayout.NORTH);
        setSize(new Dimension(0,1));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setOpaque(false);
        getContentPane().setBackground(new Color(60, 63, 65));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Project Explorer");
        this.pack();
        this.setVisible(true);
        setSize(300,930);
        setLocation(0, 0);


    }


    public void startup(Projet projet){

         root = new DefaultMutableTreeNode(projet.getNom());


        DefaultMutableTreeNode scene = new DefaultMutableTreeNode("scenes");
        root.add(scene);
        for(String p : projet.getScene())
        {
            DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode(p);

            scene.add(vegetableNode);
        }

        //create the child nodes

        // DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("sc2");
        //add the child nodes to the root node

        // roott.add(fruitNode);


        DefaultMutableTreeNode objets = new DefaultMutableTreeNode("Objets");
        root.add(objets);
        //create the child nodes



        for(Forme p : projet.getObj())
        {
            DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode(p);

            objets.add(vegetableNode);
        }


        //create the tree by passing in the root node

        this.addMouseListener(this);
        setLayout(new BorderLayout());
         tree = new JTree(root);
        tree.addMouseListener(ma);


        if (tree.getCellRenderer() instanceof DefaultTreeCellRenderer)
        {
            final DefaultTreeCellRenderer renderer =
                    (DefaultTreeCellRenderer)(tree.getCellRenderer());
            renderer.setBackgroundNonSelectionColor(new Color(60,63,65));
            renderer.setBackgroundSelectionColor(new Color(1, 126, 178));
            renderer.setTextNonSelectionColor(Color.WHITE);
            renderer.setTextSelectionColor(Color.WHITE);
        }


        tree.setBackground(new Color(60,63,65));
        tree.setForeground(Color.WHITE);
        add(tree, BorderLayout.CENTER);


    }



    MouseAdapter ma = new MouseAdapter() {
        private void myPopupEvent(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            JTree tree = (JTree)e.getSource();
            final TreePath path = tree.getPathForLocation(x, y);
            if (path == null)
                return;

            tree.setSelectionPath(path);

             final DefaultMutableTreeNode obj = (DefaultMutableTreeNode)path.getLastPathComponent();

            Forme label = (Forme) obj.getUserObject();
            JMenuItem itemname = new JMenuItem(label.getName());
            itemname.setEnabled(false);
            JPopupMenu popup = new JPopupMenu();
            popup.add(itemname);
            JMenuItem item = new JMenuItem("Ouvrir/Editer");
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //ouverture de l'inspecteur avec les données de la vue
                    parent.creatFrameOPGL(obj.getUserObject());

                }
            });

            popup.add(item);
            popup.show(tree, x, y);
        }
        private void openopengj(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            JTree tree = (JTree)e.getSource();
            final TreePath path = tree.getPathForLocation(x, y);
            if (path == null)
                return;


            final DefaultMutableTreeNode obj = (DefaultMutableTreeNode)path.getLastPathComponent();

            //Forme label = (Forme) obj.getUserObject();
            parent.creatFrameOPGL(obj.getUserObject());
        }


        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 2 && !e.isConsumed()) {
                e.consume();
                if (e.isPopupTrigger()) myPopupEvent(e);

                //handle double click event.
            }
        }
        public void mouseReleased(MouseEvent e) {

            if (e.getClickCount() == 2 && !e.isConsumed()) {
                e.consume();

                openopengj(e);
                //handle double click event.
            }
            if (e.isPopupTrigger()) myPopupEvent(e);
        }
    };


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
        System.out.println("salut");
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
