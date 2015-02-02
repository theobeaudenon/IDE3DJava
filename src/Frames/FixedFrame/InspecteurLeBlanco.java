package Frames.FixedFrame;

import Frames.Dialog.PopupObjectAdd;
import Frames.Dialog.PopupObjectFrame;
import Frames.InternalFrameDemo;
import buttons.ColorButton;
import classe.*;
import utils.Menu.RightClicMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Boufle on 06/01/15.
 */
public class InspecteurLeBlanco extends JInternalFrame {

    private JPanel top = new JPanel();
    private RightClicMenu rightClicMenu = new RightClicMenu();
    private InternalFrameDemo parent;

    public InspecteurLeBlanco(final InternalFrameDemo parent) {
        super("Project",
                false, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        this.parent = parent;
        //create the root node
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);

        this.setContentPane(rightClicMenu);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setOpaque(false);
        getContentPane().setBackground(new Color(60, 63, 65));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        setSize(300, 380);
        setLocation(0, 465);
        top.setBackground(new Color(60, 63, 65));
        this.add(top);
    }


    public void setModule(final Forme module) {
        top.setLayout(new GridLayout(10, 1, 1, 3));
        top.removeAll();
        top.repaint();
        top.setPreferredSize(this.getSize());


        final JTextField name = new JTextField(module.getName());
        name.setBackground(new Color(45, 48, 50));
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

        for (final ColorRVB color : module.params()) {
            final ColorButton chooseButton = new ColorButton("Choix Couleur");
            chooseButton.setForeground(color.color());
            chooseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Color backgroundColor = JColorChooser.showDialog(parent,
                            "Choisir votre couleur", color.color());
                    if (backgroundColor != null) {
                        parent.log(backgroundColor.toString());
                        color.setColor(backgroundColor);
                        chooseButton.setForeground(backgroundColor);
                    }
                }
            });
            top.add(chooseButton);
        }


        for (final DataConf color : module.conf()) {
            JButton chooseButton = new JButton(color.getEmplacement());
            chooseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    PopupObjectFrame popupObjectFrame = new PopupObjectFrame(String.valueOf(color.getValue()));
                    try {
                        color.setValue(Float.valueOf(popupObjectFrame.getName()));
                        parent.log("Nouvelle Valeur : " + color.getValue());
                    } catch (Exception ee) {
                        parent.log("Valeur Invalide");
                    }
                }
            });
            top.add(chooseButton);
        }
    }

    public void setModule(final Scene module) {
        top.setLayout(new GridLayout(1, 2, 10, 10));
        top.removeAll();
        top.repaint();
        top.setPreferredSize(this.getSize());

        final JTextField name = new JTextField(module.getName());
        name.setBackground(new Color(45, 48, 50));
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
        final DefaultListModel model = new DefaultListModel();
        for (final BoLASoupe forme : module.getFormes()) {
            model.addElement(forme);

        }
        final JList list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setAutoscrolls(true);
        list.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        list.setBackground(new Color(60, 63, 65));
        list.setForeground(new Color(205, 198, 183));
        list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int index = list.locationToIndex(e.getPoint());
                BoLASoupe item = (BoLASoupe) model.getElementAt(index);
                item.setSelected(true);
            }

            public void mouseReleased(MouseEvent e) {
                for (final BoLASoupe forme : module.getFormes()) {
                    forme.setSelected(false);
                 }
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    int index = list.locationToIndex(e.getPoint());
                    BoLASoupe item = (BoLASoupe) model.getElementAt(index);

                    PopupObjectAdd ef = new PopupObjectAdd(parent , item);
                    if(ef.getBol() != null){
                        //d.addForme(ef.getBol());
                        // parent.log("Ajouté : " + comboBox.getSelectedItem());
                        //parent.updateInspecteur(d);
                    }
//                    parent.log("YOLOO");
                    //handle double click event.
                }
            }
        });
        top.add(list);

        JButton bt = new JButton("PLAY");
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.playgame(module);
            }
        });

        top.add(bt);

    }


    public void remModule() {
        top.removeAll();
        top.repaint();
        top.setPreferredSize(this.getSize());
    }
}
