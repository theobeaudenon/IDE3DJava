package Frames;

import buttons.ColorButton;
import classe.BoLASoupe;
import classe.ColorRVB;
import classe.Forme;
import classe.Scene;
import utils.RightClicMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    }

    public void setModule(final Scene module) {
        top.setLayout(new GridLayout(2, 2,10,10));
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
                if (e.getClickCount() == 2) {

                }
            }

            public void mouseReleased(MouseEvent e) {
                for (final BoLASoupe forme : module.getFormes()) {
                    forme.setSelected(false);
                }
            }
        });
        top.add(list);

        top.add(Box.createHorizontalStrut(10));


    }


    public void remModule() {
        top.removeAll();
        top.repaint();
        top.setPreferredSize(this.getSize());
    }
}
