package utils;

import java.util.Random;

/**
 * utils
 * Created by Theo on 06/01/2015 for Ide3DProject.
 */
public class RandomUtils extends Random {

    /**
     * @param min generated value. Can't be > then max
     * @param max generated value
     * @return values in closed range [min, max].
     */
    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    public static float randFloat(float min, float max) {
        return min + (float)Math.random() * (max - min);
    }
}
