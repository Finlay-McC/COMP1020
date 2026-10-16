package Lab4;

import java.io.*;
import java.util.Scanner;

/**
 * Lab4Ex1
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-10-08
 */
public class Lab4Ex1 {
    public static final int MAX_INTEGERS = 20;
    public static String IN_FILE_NAME = "Labs/src/Lab4/data.txt";
    public static String OUT_FILE_NAME = "Labs/src/Lab4/output.txt";

    public static void main(String[] args) {
        int[] numbers = new int[MAX_INTEGERS];
        int numbersSize = 0;

        numbersSize = readNumbersToArray(IN_FILE_NAME, numbers);
        writeNumbersToFile((OUT_FILE_NAME), numbers, numbersSize);

        System.out.println("End of processing");
    }

    public static int readNumbersToArray(String fileName, int[] numbers) {
        int number, pos = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            Scanner in = new Scanner(br);
            while (in.hasNextInt() && pos >= 0) {
                number = in.nextInt();
                numbers[pos] = number;
                pos++;
                System.out.println("Read number: " + number);
            }
            br.close();
        } catch (IOException ioe) {
            System.out.println("IOException while reading file: " + fileName);
            ioe.printStackTrace();
        }

        return pos;
    }

    public static void writeNumbersToFile(String filename, int[] numbers, int size) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filename));
            for (int i = 0; i < size; i+=3) {
                pw.print(numbers[i]);
                if (i+1 < size) {
                    pw.print("," + numbers[i+1]);
                }
                if (i+2 < size) {
                    pw.println("," + numbers[i+2]);
                }
            }
            pw.close();
        } catch (IOException ioe) {
            System.out.println("IOException while writing file: " + filename);
            ioe.printStackTrace();
        }
    }

}
