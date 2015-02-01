package Shapes;

import classe.Vector3f;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Shapes
 * Created by Theo on 01/02/2015 for Ide3DProject.
 */
public class OBJLoader {

    public static  void loadObj(String name , Loader loader){

        FileReader fr = null;
        try {
            fr = new FileReader(new File("res/"+name+".obj"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fr);
        String line;
        List<Vector3f> vertices = new ArrayList<Vector3f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();

        List<Integer> indices = new ArrayList<Integer>();


        float[] verticesarray = null;
        float[] normalsarray = null;
        int[] indicesarray = null;
        try{

            while (true){
                line  = reader.readLine();
                String[] currentline = line.split(" ");
                if(line.startsWith("v ")){
                    Vector3f vertex = new Vector3f(Float.parseFloat(currentline[1]),Float.parseFloat(currentline[2]),Float.parseFloat(currentline[3]));
                    vertices.add(vertex);
                }else if(line.startsWith("vt ")){

                }else if(line.startsWith("vn ")){
                    Vector3f normal = new Vector3f(Float.parseFloat(currentline[1]),Float.parseFloat(currentline[2]),Float.parseFloat(currentline[3]));
                    normals.add(normal);
                }else if(line.startsWith("f ")){
                    normalsarray = new float[vertices.size()*3];
                    break;
                }
            }
            while (line != null){
                if(!line.startsWith("f ")){
                    line = reader.readLine();
                    continue;
                }
                String[] currentline = line.split(" ");
                String[] vertex1  = currentline[1].split("/");
                String[] vertex2  = currentline[2].split("/");
                String[] vertex3  = currentline[2].split("/");
                vertextaf(vertex1,indices,normals,normalsarray);
                line = reader.readLine();
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        verticesarray = new float[vertices.size()*3];
        indicesarray = new int[indices.size()];
        int vectest = 0;
        for (Vector3f vertex:vertices){
            verticesarray[vectest++] = vertex.x;
            verticesarray[vectest++] = vertex.y;
            verticesarray[vectest++] = vertex.z;
        }
        for ( int i=0; i<indices.size();i++){
            indicesarray[i] = indices.get(i);
        }


    }

    private static void vertextaf(String[] vetexData, List<Integer> indices , List<Vector3f> normals , float[] normal){

        int current = Integer.parseInt(vetexData[0]) -1 ;
        indices.add(current);
        Vector3f curnormal = normals.get(Integer.parseInt(vetexData[2])-1);
        normal[current*3] = curnormal.x;
        normal[current*3+1] = curnormal.y;
        normal[current*3+2] = curnormal.z;


    }
}
