package Week3;

/**
 * Main
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-21
 */
public class Main {
    public static void main(String[] args) {
        Person joe = new Person();
        Person jane = new Person();

        joe.name = "Joseph";
        joe.age = 25;
        System.out.println(joe);
        joe.haveBirthday();
        System.out.println(joe);

        System.out.println("\nEnd of processing");
    }
}
