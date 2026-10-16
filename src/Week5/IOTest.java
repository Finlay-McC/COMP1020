package Week5;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;
import java.io.*;

/**
 * IOTest
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-10-07
 */
public class IOTest {
    public static void main(String[] args) {
//        try{
//            BufferedReader br = new BufferedReader(new FileReader("src/Week5/data.txt"));
//            String line = br.readLine();
//            while (line != null) {
//                System.out.println(line);
//                line = br.readLine();
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading file");
//        }

//        try{
//            BufferedReader br = new BufferedReader(new FileReader("src/Week5/data.txt"));
//            String line = br.readLine();
//            String[] parts = line.split(" ");
//            System.out.println("Name: " + parts[0] + "\nAge: " + parts[1] + "");
//        } catch (IOException e) {
//            System.out.println("Error reading file");
//        }

        String data = "34.2 Fred false  -3";
        Scanner sc = new Scanner(data);
        double x = sc.nextDouble();
        String name = sc.next();
        boolean flag = sc.nextBoolean();
        int y = sc.nextInt();

        System.out.println("End of processing");
    }
}