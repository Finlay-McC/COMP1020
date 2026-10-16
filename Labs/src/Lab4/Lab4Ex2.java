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
public class Lab4Ex2 {
    public static final int MAX_INTEGERS = 10;
    public static String IN_FILE_NAME = "Labs/src/Lab4/data.txt";

    public static void main(String[] args) {
        int[] numbers = readNumbersToFilledArray(IN_FILE_NAME);

        System.out.println("End of processing");
    }

    public static int[] readNumbersToFilledArray(String fileName) {
        int[] numbers = new int[MAX_INTEGERS];
        int number, pos = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            Scanner in = new Scanner(br);
            if (pos == numbers.length){
                numbers = doubleArraySize(numbers);
            }
            while (in.hasNextInt()) {
                if (pos == numbers.length){
                    numbers = doubleArraySize(numbers);
                }
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
        int[] fullArray = new int[pos];
        for (int i = 0; i < pos; i++) {
            fullArray[i] = numbers[i];
        }

        return fullArray;
    }

    private static int[] doubleArraySize(int[] numbers) {
        int[] newNumbers = new int[numbers.length * 2];
        System.arraycopy(numbers, 0, newNumbers, 0, numbers.length);
        for (int i = numbers.length; i < newNumbers.length; i++) {
            newNumbers[i] = 0;
        }
        return newNumbers;
    }
}
