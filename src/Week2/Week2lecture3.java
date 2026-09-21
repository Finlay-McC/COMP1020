package Week2;
import java.util.Arrays;

/**
 * Week2lecture3
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-18
 */
public class Week2lecture3 {
    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,5};
        int[] numbers2 = new int[numbers.length];

        for (int i = 0; i < numbers2.length; i++) {
//            System.out.println(numbers2[i]);
            numbers2[i] = numbers[i];
        }

        numbers[0] = 10000;

        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(numbers2));

        for (int element : numbers) {
            System.out.println(element);
        }

        System.out.println("End of processing");
    }
}
