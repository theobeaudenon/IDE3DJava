package Frames.Dialog;

import Frames.InternalFrameDemo;
import buttons.CloseButton;
import buttons.ColorButton;
import classe.BoLASoupe;
import classe.Forme;
import classe.Scene;
import utils.CustomTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Boufle on 14/01/15.
 */
public class PopupObjectAdd extends JDialog {

    private JPanel top = new JPanel();
    private JPanel bot = new JPanel();
    private JPanel body = new JPanel(new GridBagLayout());
    private CloseButton closeButton = new CloseButton("");
    private CustomTextField name = new CustomTextField(20);
    private ColorButton ok = new ColorButton("OK");
    GridBagConstraints c = new GridBagConstraints();
    private JLabel object = new JLabel("Object : ");
    private JComboBox objectBox = new JComboBox();
    private CustomTextField xObject = new CustomTextField(0);
    private CustomTextField yObject = new CustomTextField(0);
    private CustomTextField zObject = new CustomTextField(0);
    private JLabel x = new JLabel("x");
    private JLabel y = new JLabel("y");
    private JLabel z = new JLabel("z");
    private JLabel spacer = new JLabel();
    private JLabel derouler = new JLabel(">");
    private JButton avanceJbutton = new JButton(">");
    private JLabel teleporteur = new JLabel("Teleporteur : ");
    private JComboBox teleporteurBox = new JComboBox();
    private JLabel nameObject = new JLabel("Nom : ");
    private CustomTextField nameField = new CustomTextField(0);
    int compteur = 0;
    private BoLASoupe bol = null;

    public PopupObjectAdd(InternalFrameDemo parent, BoLASoupe item) {

        statup(parent);

        xObject.setText(item.getX().toString());
        yObject.setText(item.getY().toString());
        zObject.setText(item.getZ().toString());
        nameField.setName(item.getName());
        teleporteurBox.setSelectedItem(item.getTeleportTo());
        objectBox.setSelectedItem(item.getForme());

        setVisible(true);

    }

    public BoLASoupe getBol() {
        return bol;
    }

    int posX ;
    int posY ;


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
    AbstractAction d = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            PopupObjectAdd.this.dispose();
        }
    };

    AbstractAction s= new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Forme selectedItem = (Forme) objectBox.getSelectedItem();
                Scene selectedItem1 = (Scene) teleporteurBox.getSelectedItem();
                bol = new BoLASoupe(selectedItem ,Float.parseFloat(xObject.getText()),Float.parseFloat(yObject.getText()),Float.parseFloat(zObject.getText()),selectedItem1 , selectedItem.getName());
                PopupObjectAdd.this.dispose();
            }catch (Exception ef){

            }

        }
    };
    ActionListener dsd = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            PopupObjectAdd.this.dispose();
        }
    };
    public void statup(InternalFrameDemo parent){

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(Forme f : parent.getProjet().getObj()){

            model.addElement(f);
        }
        objectBox.setModel(model);



        DefaultComboBoxModel modele = new DefaultComboBoxModel();
        modele.addElement(null);
        for(Scene f : parent.getProjet().getScene()){

            modele.addElement(f);
        }
        teleporteurBox.setModel(modele);


        setModal(true);
        setSize(new Dimension(300, 200));
        setUndecorated(true);
        setLocationRelativeTo(this);
        body.setBorder(BorderFactory.createLineBorder(Color.black));
        ok.setBorder(BorderFactory.createLineBorder(Color.black));
        // setAlwaysOnTop(true);
        top.setBackground(new Color(45, 48, 50));
        top.setPreferredSize(new Dimension(300, 40));
        top.setBorder(BorderFactory.createLineBorder(Color.black));
        bot.setBackground(new Color(45, 48, 50));
        bot.setPreferredSize(new Dimension(300, 40));
        bot.setBorder(BorderFactory.createLineBorder(Color.black));
        body.setBackground(new Color(60, 63, 65));
        add(body, BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);
        add(bot, BorderLayout.SOUTH);
        bot.add(ok);
        closeButton.setPreferredSize(new Dimension(20, 20));
        closeButton.addActionListener(d);


        ok.addActionListener(s);
        spacer.setPreferredSize(new Dimension(250,20));
        top.add(spacer);
        top.add(closeButton);
        top.addMouseListener(ml);
        top.addMouseMotionListener(m2);

        objectBox.setBackground(new Color(45, 48, 50));
        objectBox.setFont(new Font("Courier", Font.ITALIC, 21));
        objectBox.setBorder(BorderFactory.createEmptyBorder());
        objectBox.setForeground(new Color(160,160,160));

        object.setForeground(Color.WHITE);
        object.setFont(new Font("Courier", Font.ITALIC, 21));

        teleporteur.setForeground(Color.WHITE);
        teleporteur.setFont(new Font("Courier", Font.ITALIC, 21));

        x.setForeground(Color.WHITE);
        x.setFont(new Font("Courier", Font.ITALIC, 21));
        y.setForeground(Color.WHITE);
        y.setFont(new Font("Courier", Font.ITALIC, 21));
        z.setForeground(Color.WHITE);
        z.setFont(new Font("Courier", Font.ITALIC, 21));

        nameObject.setForeground(Color.WHITE);
        nameObject.setFont(new Font("Courier", Font.ITALIC, 21));

        xObject.setPreferredSize(new Dimension(40, 20));
        xObject.setPlaceholder("0");
        xObject.setBackground(new Color(45, 48, 50));
        xObject.setHorizontalAlignment(SwingConstants.CENTER);
        xObject.setCaretColor(Color.WHITE);
        xObject.setBorder(BorderFactory.createEmptyBorder());
        xObject.setFont(new Font("Courier", Font.ITALIC, 21));
        xObject.addActionListener(dsd);

        yObject.setPreferredSize(new Dimension(40, 20));
        yObject.setPlaceholder("0");
        yObject.setBackground(new Color(45, 48, 50));
        yObject.setHorizontalAlignment(SwingConstants.CENTER);
        yObject.setCaretColor(Color.WHITE);
        yObject.setBorder(BorderFactory.createEmptyBorder());
        yObject.setFont(new Font("Courier", Font.ITALIC, 21));
        yObject.addActionListener(dsd);

        zObject.setPreferredSize(new Dimension(40,20));
        zObject.setPlaceholder("0");
        zObject.setBackground(new Color(45, 48, 50));
        zObject.setHorizontalAlignment(SwingConstants.CENTER);
        zObject.setCaretColor(Color.WHITE);
        zObject.setBorder(BorderFactory.createEmptyBorder());
        zObject.setFont(new Font("Courier", Font.ITALIC, 21));
        zObject.addActionListener(dsd);

        nameField.setPreferredSize(new Dimension(100,20));
        nameField.setPlaceholder("objet");
        nameField.setBackground(new Color(45, 48, 50));
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setCaretColor(Color.WHITE);
        nameField.setBorder(BorderFactory.createEmptyBorder());
        nameField.setFont(new Font("Courier", Font.ITALIC, 21));
        nameField.addActionListener(dsd);

        avanceJbutton.setBorderPainted(false);
        avanceJbutton.setSelected(false);
        avanceJbutton.setText(">     afficher les options");
        avanceJbutton.setFocusPainted(false);
        avanceJbutton.setBackground(new Color(40, 40, 40));
        avanceJbutton.setPreferredSize(new Dimension(300, 10));
        avanceJbutton.setFont(new Font("Courier", Font.BOLD, 12));
        avanceJbutton.setForeground(Color.WHITE);
        teleporteur.setVisible(false);
        teleporteurBox.setVisible(false);
        nameObject.setVisible(false);
        nameField.setVisible(false);
        avanceJbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compteur++;
                if (compteur % 2 == 0){
                    avanceJbutton.setText(">     afficher les options");
                    setSize(new Dimension(300, 200));
                    teleporteur.setVisible(false);
                    teleporteurBox.setVisible(false);
                    nameObject.setVisible(false);
                    nameField.setVisible(false);
                }
                else {
                    avanceJbutton.setText("v     masquer les options");
                    setSize(new Dimension(300, 300));
                    teleporteur.setVisible(true);
                    teleporteurBox.setVisible(true);
                    nameObject.setVisible(true);
                    nameField.setVisible(true);
                }
            }
        });

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        body.add(object, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,30,0,0);
        c.weightx = 0.5;
        c.gridwidth = 5;
        c.gridx = 1;
        c.gridy = 0;
        body.add(objectBox, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        body.add(x, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,0,0,0);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        body.add(xObject, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,0,0);
        c.gridx = 2;
        c.gridy = 1;
        body.add(y, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,0,0,0);
        c.gridx = 3;
        c.gridy = 1;
        body.add(yObject, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,0,0);
        c.gridx = 4;
        c.gridy = 1;
        body.add(z, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,0,0,0);
        c.gridx = 5;
        c.gridy = 1;
        body.add(zObject, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20,0,0,0);
        c.gridwidth = 6;
        c.gridx = 0;
        c.gridy = 2;
        body.add(avanceJbutton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        body.add(teleporteur, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,0,0,0);
        c.gridwidth = 5;
        c.gridx = 1;
        c.gridy = 3;
        body.add(teleporteurBox, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,0,0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        body.add(nameObject, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,0,0,0);
        c.gridwidth = 5;
        c.gridx = 1;
        c.gridy = 4;
        body.add(nameField, c);

        // body.add(name);
        // body.add(ok);



    }

    public PopupObjectAdd(InternalFrameDemo parent){

        statup(parent);

        setVisible(true);
    }


}
