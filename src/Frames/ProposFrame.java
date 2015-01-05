package Frames;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Frames
 * Created by Theo on 05/01/2015 for Ide3DProject.
 */
public class ProposFrame extends JInternalFrame {


    static final int xOffset = 390, yOffset = 200;

    public ProposFrame() {
        super("A Propos" ,
                true, //resizable
                true, //closable
                false, //maximizable
                false);//iconifiable


        Runtime runtime = Runtime.getRuntime();

        NumberFormat format = NumberFormat.getInstance();

        StringBuilder sb = new StringBuilder();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        sb.append("<font color=\"white\"> free memory: " + format.format(freeMemory / 1024) + "<br/>");
        sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "<br/>");
        sb.append("max memory: " + format.format(maxMemory / 1024) + "<br/>");
        sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "<br/> </font>");


        System.out.println(sb);

        setSize(300,300);
        setLocation(xOffset, yOffset);
        JEditorPane text = new JEditorPane("text/html",sb.toString());
        text.setBackground(new Color(68, 68, 68));
        text.setForeground(new Color(255, 163, 79));

        add(text);
    }


}
