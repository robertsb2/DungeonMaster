package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Utilities {
    public static void delay(int time){
        try{
            Thread.sleep(time);
        } catch (Exception e){
            System.out.println("delay error");
        }
    }


    public static String reader(String filename) {
        String text = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                text = text + line;
            }
        } catch (Exception e){
            System.out.println("Reader Error");
        }
        return text;
    }

    public static int getRandom(int n, int bound, int min){
        Random random = new Random();
        int result = 0;
        for (int i = 0; i < n ; i++) {
            result += random.nextInt(bound)+ min;
        }
        return result;
    }
}
