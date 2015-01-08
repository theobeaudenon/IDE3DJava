package utils;

import Frames.WelcomeFrame;

/**
 * utils
 * Created by Theo on 08/01/2015 for Ide3DProject.
 */
public class test {

    public static void main(String[] args) {

        int l = 2;
        int b = "AOC".hashCode() % 3000;
        int z = "QDE".hashCode() % 3000;
        for (int t = 0; t <= z; t++)
            l = (l ^ t) % b;
        System.out.println(l);
        System.out.println(getGCD(1955 ,1955));

    }
    public static int getGCD(int a, int b)
    {
        int iResult = 0;
        if (a < 0)
        {
            a *= -1;
        }
        if (b < 0)
        {
            b *= -1;
        }

        if (a == 0 || b == 1 || a == b)
        {
            iResult = b;
        }
        else if (a == 1 || b == 0)
        {
            iResult = a;
        }
        else if (a > b)
        {
            iResult = getGCD(b, a % b);
        }
        else
        {
            iResult = getGCD(a, b % a);
        }
        return iResult;
    }
}
