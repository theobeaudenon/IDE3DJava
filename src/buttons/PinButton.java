package buttons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class PinButton extends JButton implements MouseListener {

    private Image img;

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
            img = ImageIO.read(new File("res\\img\\pin_hoover.jpg"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        try {
            img = ImageIO.read(new File("res\\img\\pin.jpg"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
