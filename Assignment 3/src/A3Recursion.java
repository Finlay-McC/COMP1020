/**
 * A3Recursion
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-10-30
 */
public class A3Recursion {
    public static void main(String[] args) {
        System.out.println(echo(3));

        String[] places = {"Paris", "Rome", "Tokyo"};
        String[] transport = {"Train", "Plane"};
        System.out.println("End of processing");
    }

    public static String echo(int n) {
        if (n < 1 || n > 9) {
            return "";
        } else if (n == 1) {
            return "(" + n + ")";
        } else {
            return "(" + echo(n - 1) + n + echo(n-1) + ")";
        }
    }

    public static void printItineraries(String[] places, String[] transport) {

    }
}
