package utils.Menu;

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
            Sphere ed = new Sphere(name(), 1f, 0, 0, 0, new ColorRVB(0.8f, 0.8f, 0.8f), 30);
            thiss.addOBJ(ed);
            thiss.refreshTree();
            thiss.creatFrameOPGL(ed);

        } else if ("carre".equals(e.getActionCommand())) { //new

                       /* Mise en place de la forme sauvegardé pour exemple */
            Cube g = new Cube(name(), 1.0f, 0, 0, 0, new ColorRVB(1.0f, 1.0f, 1.0f), new ColorRVB(1.0f, 1.0f, 1.0f), new ColorRVB(1.0f, 1.0f, 1.0f), new ColorRVB(1.0f, 1.0f, 1.0f), new ColorRVB(1.0f, 1.0f, 1.0f), new ColorRVB(1.0f, 1.0f, 1.0f));
            thiss.addOBJ(g);
            thiss.refreshTree();
            thiss.creatFrameOPGL(g);

        } else if ("triangle".equals(e.getActionCommand())) { //new

                       /* Mise en place de la forme sauvegardé pour exemple */
            Triangle tri = new Triangle(name(), 2.0f, 0, 0, 0, new ColorRVB(1f, 1f, 0.9f), new ColorRVB(0.5f, 0f, 0.4f), new ColorRVB(0.9f, 0.9f, 0.1f), new ColorRVB(0.9f, 0f, 1f), new ColorRVB(0.2f, 0.5f, 0.35f));
            thiss.addOBJ(tri);
            thiss.refreshTree();
            thiss.creatFrameOPGL(tri);

        } else if ("cylindre".equals(e.getActionCommand())) { //new


            Cylindre sy = new Cylindre(name(), 1f, 1f, 1f, 1f, 1f, new ColorRVB(1f, 1f, 0.9f), new ColorRVB(1f, 1f, 0.9f), new ColorRVB(1f, 1f, 0.9f), new ColorRVB(1f, 1f, 0.9f));
            thiss.addOBJ(sy);
            thiss.refreshTree();
            thiss.creatFrameOPGL(sy);
        } else if ("cone".equals(e.getActionCommand())) { //new


            Cone cone = new Cone(name(), new ColorRVB(1f, 1f, 0.9f), new ColorRVB(0.9f, 0.9f, 0.1f), 1f, 5f, 5f);
            thiss.addOBJ(cone);
            thiss.refreshTree();
            thiss.creatFrameOPGL(cone);

        }  else if ("terrain".equals(e.getActionCommand())) { //new
            Terrain terr = new Terrain(name(), 10f,10f,10f,10f, new ColorRVB(0f, 0.153f, 0f));
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
