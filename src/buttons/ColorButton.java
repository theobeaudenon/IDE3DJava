package buttons;

import javax.swing.*;
import java.awt.*;

public class ColorButton extends JButton   {

    public ColorButton(String str){
        super(str);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setContentAreaFilled(false);
        setSelected(false);
    }
}