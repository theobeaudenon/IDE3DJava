package Frames;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Boufle on 13/02/15.
 */
public class WelcomeFrame extends JFrame {

    private JPanel body = new JPanel(new GridBagLayout());
    private JLabel recentProject = new JLabel("Projets Recent");
    private JLabel startProject = new JLabel("Quick Start");
    private JPanel top = new JPanel();
    private JPanel recentPan = new JPanel();
    private JPanel recentPan2 = new JPanel();
    private JPanel start = new JPanel();
    private JPanel start2 = new JPanel();

    public WelcomeFrame(){

        GridBagConstraints c = new GridBagConstraints();

        setResizable(false);
        setSize(new Dimension(800, 600));
        body.setBackground(new Color(60, 63, 65));
        setLocationRelativeTo(this);
        setContentPane(body);

        top.setPreferredSize(new Dimension(792, 100));
        top.setBackground(new Color(100,100,100));

        recentPan.setPreferredSize(new Dimension(200,30));
        recentPan.setBackground(new Color(100,100,100));

        recentProject.setFont(new Font("Courier", Font.ITALIC, 19));
        recentProject.setForeground(Color.WHITE);
        recentPan.add(recentProject);

        start.setPreferredSize(new Dimension(500,30));
        start.setBackground(new Color(100, 100, 100));

        startProject.setFont(new Font("Courier", Font.ITALIC, 19));
        startProject.setForeground(Color.WHITE);
        start.add(startProject);

        recentPan2.setPreferredSize(new Dimension(200,300));
        recentPan2.setBackground(new Color(60, 63, 65));
        recentPan2.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));

        start2.setPreferredSize(new Dimension(500, 300));
        start2.setBackground(new Color(60, 63, 65));
        start2.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));

        c.insets = new Insets(0, 0, 20, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weighty = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        body.add(top, c);

        c.insets = new Insets(0, 30, 0, 30);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.weighty = 1;
        body.add(recentPan, c);

        c.insets = new Insets(0, 0, 0, 30);
        c.gridx = 1;
        c.gridy = 1;
        c.weighty = 1;
        body.add(start, c);

        c.insets = new Insets(0, 30, 0, 0);
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 1;
        body.add(recentPan2, c);

        c.insets = new Insets(0, 0, 0, 30);
        c.gridx = 1;
        c.gridy = 2;
        c.weighty = 1;
        body.add(start2, c);

        setVisible(true);

    }
}
