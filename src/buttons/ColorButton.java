package buttons;

import javax.swing.*;
import java.awt.*;

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