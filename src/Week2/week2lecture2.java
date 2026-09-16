package Week2;

/**
 * Week2lecture2
 *
 * COMP 1020 Fall 2026 A01
 * @author Finlay McCorrister
 * @since 2026-09-16
 */
public class week2lecture2 {

    public static void main(String[] args) {

       int num = 0xf;
       int[] intArray = new int[10];
       for (int i = 0; i < intArray.length; i++) {
           intArray[i] = i;
       }
       System.out.println(num);

       while (num > -10) {
           if (num >= 2) {
               System.out.println("greater than 0!");
           } else {
               System.out.println("less than 0!");
               break;
           }
           num--;
       }
    }
}