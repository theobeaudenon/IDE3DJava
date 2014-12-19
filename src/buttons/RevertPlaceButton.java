package buttons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class RevertPlaceButton extends JButton implements MouseListener {

    private String name;
    private Image img;

    public RevertPlaceButton(String str){
        super(str);
        this.name = str;
        setBorderPainted(false);
        this.setContentAreaFilled(false);
        try {
            img = ImageIO.read(new File("res\\img\\revertPlace.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addMouseListener(this);

    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        try {
            img = ImageIO.read(new File("res\\img\\revertPlace_hoover.jpg"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        try {
            img = ImageIO.read(new File("res\\img\\revertPlace.jpg"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
