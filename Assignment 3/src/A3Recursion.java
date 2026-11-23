import java.util.Arrays;

/**
 * A3Recursion
 * Recursive problems
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-10-30
 */
public class A3Recursion {
    /**
     * Main method for the program
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(echo(3));

        String[] places = {"Paris", "Rome", "Tokyo"};
        String[] transport = {"Train", "Plane"};
        printItineraries(places, transport);

        System.out.println("End of processing");
    }

    /**
     * A communication system generates nested messages
     * @param n The number of times to echo
     * @return The echoed message
     */
    public static String echo(int n) {
        if (n < 1 || n > 9) {
            return "";
        } else if (n == 1) {
            return "(" + n + ")";
        } else {
            return "(" + echo(n - 1) + n + echo(n-1) + ")";
        }
    }

    /**
     * Generate all ordered pairs of different destinations, each combined with every transportation
     * method.
     * @param places The list of locations
     * @param transport The list of transportation methods
     */
    public static void printItineraries(String[] places, String[] transport) {
        if (places != null && transport != null && places.length >= 2 && places.length <= 10 && transport.length >= 1 && transport.length <= 5) {
            printLocation(places[0], Arrays.copyOfRange(places, 1, places.length), transport);
            printItineraries(Arrays.copyOfRange(places, 1, places.length), transport);
        }
    }

    /**
     * Prints all possible itineraries for a given location
     * @param location The starting location
     * @param otherPlaces The remaining locations
     * @param transport THe transportation methods
     */
    private static void printLocation(String location, String[] otherPlaces, String[] transport) {
        if (otherPlaces.length > 0) {
            printTransport(location, otherPlaces[0], transport);
            printLocation(location, Arrays.copyOfRange(otherPlaces, 1, otherPlaces.length), transport);
        }
    }

    /**
     * prints all possible iterations for 2 locations
     * @param location1 The first location
     * @param location2 The second location
     * @param transport The transportation methods
     */
    private static void printTransport(String location1, String location2, String[] transport) {
        if (transport.length > 0) {
            System.out.println(location1 + " -> " + location2 + " by " + transport[0]);
            System.out.println(location2 + " -> " + location1 + " by " + transport[0]);
            printTransport(location1, location2, Arrays.copyOfRange(transport, 1, transport.length));
        }
    }
}
