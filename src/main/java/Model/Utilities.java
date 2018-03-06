package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Utilities {

    private static final double XP_NEEDED = 9;
    private static final double XP_AWARDED = 4;



    public String reader(String filename) {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
        } catch (Exception e){
            System.out.println("Reader Error");
            e.printStackTrace();
        }
        return text.toString();
    }

    public static int getRandom(int n, int bound, int min){
        Random random = new Random();
        int result = 0;
        for (int i = 0; i < n ; i++) {
            result += random.nextInt(bound)+ min;
        }
        return result;
    }

    public static double getXpNeeded(int level) {
        return (level * level) * XP_NEEDED;
    }

    public static double getXpRewarded(int floor){
        return (floor * floor) * XP_AWARDED;
    }

}
