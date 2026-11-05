package Lab7;
public class Lab7Ex6 {
    public static void main(String args[]) {
        Square sq1, sq2, sq3, sq4, sq5;

        sq1 = new Square(3);
        sq1.fill(new int[] { 8, 1, 6, 3, 5, 7, 4, 9, 2 });
        System.out.println("Square 1 isMagic? " + sq1.isMagic());
        System.out.println("Square 1:\n" + sq1);

        sq2 = new Square(3);
        sq2.fill(new int[] { 1, 1, 1, 5, 5, 5, 0, 9, 9 });
        System.out.println("Square 2 isMagic? " + sq2.isMagic());
        System.out.println("Square 2:\n" + sq2);

        sq3 = new Square(4);
        sq3.fill(new int[] { 10, 3, 13, 8, 5, 16, 2, 11, 4, 9, 7, 14, 15, 6, 12, 1 });
        System.out.println("Square 3 isMagic? " + sq3.isMagic());
        System.out.println("Square 3:\n" + sq3);

        sq4 = new Square(5);
        sq4.fill(new int[] { 21, 3, 4, 12, 25, 15, 17, 6, 19, 8, 10, 24, 13, 2, 16, 18, 7, 20, 9, 11, 1, 14, 22, 5, 23 });
        System.out.println("Square 4 isMagic? " + sq4.isMagic());
        System.out.println("Square 4:\n" + sq4);

        sq5 = new Square(3);
        sq5.fill(new int[] { 1, 2, 3, 3, 2, 1, 1, 1, 11 });
        System.out.println("Square 5 isMagic? " + sq5.isMagic());
        System.out.println("Square 5:\n" + sq5);

        System.out.println("Empty square:\n" + new Square(4));

        System.out.println("\nEnd of processing.");
    }
}
