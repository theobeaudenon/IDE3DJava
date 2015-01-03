package Shapes;

/**
 * Shapes
 * Created by Theo on 26/12/2014 for Ide3DProject.
 */
public class Forme {


    public Class classe ;
    public String name;
    public Object obj;


    public Class getClasse() {
        return classe;
    }

    public void setClasse(Class classe) {
        this.classe = classe;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.getName();
    }
}
