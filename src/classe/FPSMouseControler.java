package classe;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * classe
 * Created by Theo on 12/02/2015 for Ide3DProject.
 */
public class FPSMouseControler implements MouseMotionListener
{
    int mx=0;
    int my=0;
    int screenX;
    int screenY;
    int screenXDiv2;
    int screenYDiv2;

    public int getDmx() {
        return dmx;
    }
    public void setDmx(int dmx) {
        this.dmx = dmx;
    }
    public int getDmy() {
        return dmy;
    }
    public void setDmy(int dmy) {
        this.dmy = dmy;
    }

    public int dmx=0;
    public int dmy=0;

    Canvas c;
    Robot robot;


    public FPSMouseControler() throws Exception
    {
        robot = new Robot();
        Toolkit t = Toolkit.getDefaultToolkit();
        screenX = t.getScreenSize().width;
        screenY = t.getScreenSize().height;
        screenXDiv2=screenX>>1;
        screenYDiv2=screenY>>1;
    }

    public void setCanvas(Canvas c)
    {
        if(this.c!=null)
            this.removeCanvas(this.c);

        this.c=c;
        c.addMouseMotionListener(this);


    }

    public void removeCanvas(Canvas c)
    {
        c.removeMouseMotionListener(this);
        this.c=null;

    }


    public void mouseDragged(MouseEvent e)
    {
        this.mouseMoved(e);
    }

    public void mouseMoved(MouseEvent e)
    {
        mx=e.getXOnScreen();
        my=e.getYOnScreen();
        if(mx!=screenXDiv2 || my!=screenYDiv2)
        {
            if(mx!=screenXDiv2)
            {
                dmx+=mx-screenXDiv2;
            }
            if(my!=screenYDiv2)
            {
                dmy+=my-screenYDiv2;
            }
            robot.mouseMove(screenXDiv2, screenYDiv2);
        }
    }


}