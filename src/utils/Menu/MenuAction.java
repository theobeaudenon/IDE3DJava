package utils.Menu;

import Frames.Dialog.PopupColorChooser;
import Frames.Dialog.PopupObjectFrame;
import Frames.InternalFrameDemo;
import Shapes.*;
import classe.ColorRVB;
import classe.Scene;
import utils.ProjectExport.ProjectFileSaver;

import java.awt.event.ActionEvent;

/**
 * utils.Menu
 * Created by Theo on 04/01/2015 for Ide3DProject.
 */
public class MenuAction {
    //React to menu selections.
    public static void actionPerformed(ActionEvent e, InternalFrameDemo thiss) {
        if ("new".equals(e.getActionCommand())) { //new
            thiss.welcomescreen();
        } else if ("Project".equals(e.getActionCommand())) { //new
            thiss.createTreeFrame();
        } else if ("file".equals(e.getActionCommand())) { //new
            thiss.welcomescreen();
        } else if ("propos".equals(e.getActionCommand())) { //new
            thiss.createPropos();
        } else if ("quit".equals(e.getActionCommand())) { //new
            thiss.quit();
        } else if ("save".equals(e.getActionCommand())) { //new
            ProjectFileSaver.save(thiss.getProjet(), thiss, 1);
        } else if ("saveas".equals(e.getActionCommand())) { //new
            ProjectFileSaver.save(thiss.getProjet(), thiss, 2);
        } else if ("scene".equals(e.getActionCommand())) { //new
            Scene sc = new Scene();
            sc.setName(name());
            thiss.addSCN(sc);
            thiss.refreshTree();
            thiss.creatFrameOPGL(sc);
        } else if ("sceneg".equals(e.getActionCommand())) { //new
            Scene sc = new Scene("Generated");
            thiss.addSCN(sc);
            thiss.refreshTree();
            thiss.creatFrameOPGL(sc);
        } else if ("sphere".equals(e.getActionCommand())) { //new
             /* Mise en place de la forme sauvegardé pour exemple */
            Sphere ed = Sphere.newSphere();
            ed.setName(name());
            thiss.addOBJ(ed);
            thiss.refreshTree();
            thiss.creatFrameOPGL(ed);
        } else if ("carre".equals(e.getActionCommand())) { //new

                       /* Mise en place de la forme sauvegardé pour exemple */
            Cube g =  Cube.newCube();
            g.setName(name());
            thiss.addOBJ(g);
            thiss.refreshTree();
            thiss.creatFrameOPGL(g);

        } else if ("triangle".equals(e.getActionCommand())) { //new

                       /* Mise en place de la forme sauvegardé pour exemple */
            Triangle tri = Triangle.newTriangle();
            tri.setName(name());
            thiss.addOBJ(tri);
            thiss.refreshTree();
            thiss.creatFrameOPGL(tri);

        } else if ("cylindre".equals(e.getActionCommand())) { //new

            Cylindre sy =Cylindre.newCylindre();
            sy.setName(name());
            thiss.addOBJ(sy);
            thiss.refreshTree();
            thiss.creatFrameOPGL(sy);
        } else if ("cone".equals(e.getActionCommand())) { //new


            Cone cone = Cone.newCone();
            cone.setName(name());
            thiss.addOBJ(cone);
            thiss.refreshTree();
            thiss.creatFrameOPGL(cone);

        }  else if ("terrain".equals(e.getActionCommand())) { //new
            Terrain terr = Terrain.newTerrain();
            terr.setName(name());
            thiss.addOBJ(terr);
            thiss.refreshTree();
            thiss.creatFrameOPGL(terr);

        } else { //quit
            thiss.log("Action non prise en compte");
        }
    }

    public static String name() {

        PopupObjectFrame popupObjectFrame = new PopupObjectFrame("Nom de l'objet");

        return popupObjectFrame.getName(); //JOptionPane.showInputDialog(new JFrame(""), "Entrez le nom de l'objet");
    }
}
