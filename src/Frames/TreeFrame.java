package Frames;

import buttons.PinButton;
import buttons.ToolProjectButton;
import classe.Projet;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Boufle on 16/12/14.
 */
public class TreeFrame extends JInternalFrame {

    BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
    Component northPane = ui.getNorthPane();
    MouseMotionListener[] motionListeners = (MouseMotionListener[]) northPane.getListeners(MouseMotionListener.class);
    private JPanel top = new JPanel();
    private ToolProjectButton toolProjectButton = new ToolProjectButton("");
    private PinButton pinButton = new PinButton("");
    int posX ;
    int posY ;
    private int compteurClic = 1;


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

    public TreeFrame(Projet projet){


        super("Project",
                false, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        //create the root node
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

        setDragable(false);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(projet.getNom());


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



        for(String p : projet.getObj())
        {
            DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode(p);
            objets.add(vegetableNode);
        }


        //create the tree by passing in the root node

        setLayout(new BorderLayout());
        JTree tree = new JTree(root);
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
        else
        {
            System.err.println("Sorry, no special colors today.");
        }
        tree.setBackground(new Color(60,63,65));
        tree.setForeground(Color.WHITE);
        add(tree, BorderLayout.CENTER);

        pinButton.setPreferredSize(new Dimension(16,16));
        top.add(pinButton);
        pinButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compteurClic++;
                if (compteurClic%2 == 0){
                    setDragable(true);

                }
                else {
                    setDragable(false);
                }
            }
        });

        toolProjectButton.setPreferredSize(new Dimension(14,16));
        top.add(toolProjectButton);

        top.setBackground(new Color(45, 48, 50));
        top.setBorder(new LineBorder(Color.BLACK));

        add(top, BorderLayout.NORTH);

        setBorder(null);
        setOpaque(false);
        getContentPane().setBackground(new Color(60, 63, 65));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Project Explorer");
        this.pack();
        this.setVisible(true);
        setSize(300,930);
        setLocation(0, 0);


    }
    MouseAdapter ma = new MouseAdapter() {
        private void myPopupEvent(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            JTree tree = (JTree)e.getSource();
            TreePath path = tree.getPathForLocation(x, y);
            if (path == null)
                return;

            tree.setSelectionPath(path);

           // My_Obj obj = (My_Obj)path.getLastPathComponent();

            String label = "clicldroit";
            JPopupMenu popup = new JPopupMenu();
            popup.add(new JMenuItem(label));
            popup.show(tree, x, y);
        }
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) myPopupEvent(e);
        }
        public void mouseReleased(MouseEvent e) {
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


}
