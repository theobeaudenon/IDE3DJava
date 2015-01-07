package buttons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class ColorButton extends JButton   {

    private String name;
    private Image img;

    public ColorButton(String str){
        super(str);
        this.name = str;
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setContentAreaFilled(false);
        setSelected(false);

    }




}
