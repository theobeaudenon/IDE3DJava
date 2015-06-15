package Frames.FixedFrame;

import Frames.Dialog.PopupColorChooser;
import Frames.Dialog.PopupObjectAdd;
import Frames.Dialog.PopupObjectFrame;
import Frames.InternalFrameDemo;
import buttons.ColorButton;
import classe.*;
import utils.CustomTextField;
import utils.Menu.RightClicMenu;

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
    private JLabel spawnLable = new JLabel("SpawnPoint : ");
    private CustomTextField xObject = new CustomTextField(0);
    private CustomTextField yObject = new CustomTextField(0);
    private CustomTextField zObject = new CustomTextField(0);
    private JLabel x = new JLabel("x");
    private JLabel y = new JLabel("y");
    private JLabel z = new JLabel("z");
    private JScrollPane jScrollPane = new JScrollPane();



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
        setSize(300, 400);
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

                    PopupColorChooser colors = new PopupColorChooser();
                    Color backgroundColor=  colors.getColorChooser().getColor();
                   /* Color backgroundColor = JColorChooser.showDialog(parent,
                            "Choisir votre couleur", color.color());*/
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
            chooseButton.setForeground(Color.WHITE);
            chooseButton.setFocusPainted(false);
            chooseButton.setBorder(BorderFactory.createBevelBorder(1,Color.black,Color.cyan));
            chooseButton.setForeground(Color.WHITE);
            chooseButton.setBackground(new Color(46,46,46));
            chooseButton.setSelected(false);
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
        top.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        top.removeAll();
        top.repaint();
        top.setPreferredSize(this.getSize());

        final JTextField name = new JTextField(module.getName());
        name.setPreferredSize(new Dimension(300, 35));
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

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,45,0);
        c.gridwidth = GridBagConstraints.REMAINDER;
        top.add(name,c);
        final DefaultListModel model = new DefaultListModel();
        for (final BoLASoupe forme : module.getFormes()) {
            model.addElement(forme);

        }
        final JList list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //list.setAutoscrolls(true);
        list.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        list.setBackground(new Color(60, 63, 65));
        list.setForeground(new Color(205, 198, 183));
        list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { try{
                int index = list.locationToIndex(e.getPoint());
                BoLASoupe item = (BoLASoupe) model.getElementAt(index);
                item.setSelected(true);  }catch (ArrayIndexOutOfBoundsException em){

                }
            }

            public void mouseReleased(MouseEvent e) {
                for (final BoLASoupe forme : module.getFormes()) {
                    forme.setSelected(false);
                }
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();

                    try{

                        int index = list.locationToIndex(e.getPoint());

                        BoLASoupe item = (BoLASoupe) model.getElementAt(index);
                        PopupObjectAdd ef = new PopupObjectAdd(parent, item);
                        if (ef.getBol() != null) {
                            module.addForme(ef.getBol());
                            module.getFormes().remove(item);
                            //model.setElementAt(ef.getBol(),index);
                            //parent.addForme(ef.getBol());
                            // parent.log("Ajouté : " + comboBox.getSelectedItem());
                            //parent.updateInspecteur(d);


                        }
                    }catch (ArrayIndexOutOfBoundsException em){

                    }


//                    parent.log("YOLOO");
                    //handle double click event.
                }
            }
        });
        jScrollPane =  new JScrollPane(list);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setPreferredSize(new Dimension(300,200));

        x.setForeground(Color.WHITE);
        x.setFont(new Font("Courier", Font.ITALIC, 21));
        y.setForeground(Color.WHITE);
        y.setFont(new Font("Courier", Font.ITALIC, 21));
        z.setForeground(Color.WHITE);
        z.setFont(new Font("Courier", Font.ITALIC, 21));
        spawnLable.setForeground(Color.WHITE);
        spawnLable.setFont(new Font("Courier", Font.ITALIC, 21));

        xObject.setPreferredSize(new Dimension(40, 20));
        xObject.setText(String.valueOf(module.getSpawnX()));
        xObject.setBackground(new Color(45, 48, 50));
        xObject.setForeground(new Color(145, 145, 145));
        xObject.setHorizontalAlignment(SwingConstants.CENTER);
        xObject.setCaretColor(Color.WHITE);
        xObject.setBorder(BorderFactory.createEmptyBorder());
        xObject.setFont(new Font("Courier", Font.ITALIC, 21));
        xObject.getDocument().addDocumentListener(new DocumentListener() {
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
                try{
                    module.setSpawnX(Float.parseFloat(xObject.getText()));
                }
                catch (NumberFormatException e){
                    module.setSpawnX(0);
                }
            }
        });

        yObject.setPreferredSize(new Dimension(40, 20));
        yObject.setText(String.valueOf(module.getSpawnY()));
        yObject.setBackground(new Color(45, 48, 50));
        yObject.setForeground(new Color(145, 145, 145));
        yObject.setHorizontalAlignment(SwingConstants.CENTER);
        yObject.setCaretColor(Color.WHITE);
        yObject.setBorder(BorderFactory.createEmptyBorder());
        yObject.setFont(new Font("Courier", Font.ITALIC, 21));
        yObject.getDocument().addDocumentListener(new DocumentListener() {
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
                try{
                    module.setSpawnY(Float.parseFloat(yObject.getText()));
                }
                catch (NumberFormatException e){
                    module.setSpawnY(0);
                }
            }
        });


        zObject.setPreferredSize(new Dimension(40,20));
        zObject.setText(String.valueOf(module.getSpawnZ()));
        zObject.setBackground(new Color(45, 48, 50));
        zObject.setForeground(new Color(145, 145, 145));
        zObject.setHorizontalAlignment(SwingConstants.CENTER);
        zObject.setCaretColor(Color.WHITE);
        zObject.setBorder(BorderFactory.createEmptyBorder());
        zObject.setFont(new Font("Courier", Font.ITALIC, 21));
        zObject.getDocument().addDocumentListener(new DocumentListener() {
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
                try{
                    module.setSpawnZ(Float.parseFloat(zObject.getText()));
                }
                catch (NumberFormatException e){
                    module.setSpawnZ(0);
                }
            }
        });


        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,0,0,0);
        c.gridwidth = GridBagConstraints.REMAINDER;
        top.add(jScrollPane, c);
        jScrollPane.repaint();
        top.repaint();

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(0,2,0,0);
        top.add(spawnLable,c);

        c.gridx = 1;
        c.gridy = 2;
        top.add(x,c);

        c.gridx = 2;
        c.gridy = 2;
        top.add(xObject,c);

        c.gridx = 3;
        c.gridy = 2;
        top.add(y,c);

        c.gridx = 4;
        c.gridy = 2;
        top.add(yObject,c);

        c.gridx = 5;
        c.gridy = 2;
        top.add(z,c);

        c.gridx = 6;
        c.gridy = 2;
        top.add(zObject,c);

        JButton bt = new JButton("PLAY");
        bt.setPreferredSize(new Dimension(100,35));
        bt.setFocusPainted(false);
        bt.setBorder(BorderFactory.createBevelBorder(1,Color.black,Color.cyan));
        bt.setForeground(Color.WHITE);
        bt.setBackground(new Color(46,46,46));
        bt.setSelected(false);
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                parent.playgame(module);
            }
        });

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = GridBagConstraints.REMAINDER;
        top.add(bt,c);

    }


    public void remModule() {
        top.removeAll();
        top.repaint();
        top.setPreferredSize(this.getSize());
    }
}
