package Lab6;
public class Lab6Ex4 {
    public static void main(String args[]) {
        printTriangle("*", 9);

        System.out.println("\nEnd of processing.");
    }

    public static void printTriangle(String text, int depth) {
        if (depth > 0) {
            printTriangle(text, depth - 1);
            System.out.print(text);
        }
    }
}