package Lab3;
import java.io.*;
import java.util.Scanner;

public class Lab3Ex1 {
    public static final int MAX_INTEGERS = 20;
    public static String FILE_NAME = "Labs/src/Lab3/numbers1.txt";

    public static void main(String[] args) {
        int[] numbers = new int[MAX_INTEGERS];
        int numbersSize = 0;

        numbersSize = readNumbersToArray(FILE_NAME, numbers);

        if (numbersSize <= 0) {
            System.out.println("No array was read.");
        } else {
            System.out.println("The following array was read:");
            printNumbers(numbers, numbersSize);
        }

        System.out.println("\n*** End of program. ***");
    }

    public static int readNumbersToArray(String fileName, int[] numbers) {
        int number, pos = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            Scanner in = new Scanner(br);
            while (in.hasNextInt() && pos < numbers.length) {
                number = in.nextInt();
                numbers[pos] = number;
                pos++;
                System.out.println("Read number: " + number);
                while (!in.hasNextInt()) {
                    if (in.hasNext()) {
                        in.nextLine();
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException ioe) {
            System.out.println("IOException while reading file: " + fileName);
            ioe.printStackTrace();
        }


        return pos;
    }

    public static void printNumbers(int[] numbers, int size) {
        System.out.print("[ ");
        for (int i = 0; i < size; i++)
            System.out.print(numbers[i] + " ");
        System.out.println("]");
    }
}
