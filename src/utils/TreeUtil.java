package utils;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.util.StringTokenizer;

/**
 * utils
 * Created by Theo on 03/01/2015 for Ide3DProject.
 */
public class TreeUtil{

    // is path1 descendant of path2
    public static boolean isDescendant(TreePath path1, TreePath path2){
        int count1 = path1.getPathCount();
        int count2 = path2.getPathCount();
        if(count1<=count2)
            return false;
        while(count1!=count2){
            path1 = path1.getParentPath();
            count1--;
        }
        return path1.equals(path2);
    }

}