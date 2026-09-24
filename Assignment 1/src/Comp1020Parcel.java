import java.util.Scanner;

/**
 * Comp1020Parcel
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-23
 */
public class Comp1020Parcel {
    public static final int YEAR = 2026;

    // ---------- Hubs ----------
    public static final String[] HUB_CODES = {
            "WPG", "YYZ", "YVR", "YUL", "JFK", "LAX", "LHR", "NRT"
    };

    public static final String[] HUB_CITIES = {
            "Winnipeg", "Toronto", "Vancouver", "Montreal",
            "New York", "Los Angeles", "London", "Tokyo"
    };

    public static final String[] HUB_COUNTRIES = {
            "Canada", "Canada", "Canada", "Canada",
            "USA", "USA", "UK", "Japan"
    };

    public static final int[] HUB_FEES = {
            5, 8, 7, 6, 10, 12, 15, 0
    };

    // ---------- Shipments ----------
    public static final String[] SHIPMENT_ORIGINS = {
            "WPG", "WPG", "WPG", "WPG", "YYZ", "YVR",
            "YYZ", "JFK", "YUL", "LAX", "WPG", "LHR"
    };

    public static final String[] SHIPMENT_DESTINATIONS = {
            "YYZ", "YYZ", "YYZ", "JFK", "WPG", "LAX",
            "LHR", "LAX", "NRT", "WPG", "YVR", "YYZ"
    };

    public static final int[] SHIPMENT_PRICES = {
            40, 35, 35, 90, 45, 110,
            250, 75, 333, 101, 50, 199
    };

    // ---------- Customers ----------
    public static final String[] CUSTOMER_NAMES = {
            "Alice", "Bob", "Chen", "Dana"
    };

    public static final String[] CUSTOMER_COUNTRIES = {
            "Canada", "USA", "Canada", "UK"
    };

    public static final int[] CUSTOMER_YEARS_JOINED = {
            2015, 2024, 2021, 2022
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        Hub[] hubs = createHubs(HUB_CODES, HUB_CITIES, HUB_COUNTRIES, HUB_FEES);
        Customer[] customers = new Customer[100];
        for (int i = 0; i < CUSTOMER_NAMES.length; i++) {
            customers[i] = new Customer(CUSTOMER_NAMES[i], CUSTOMER_COUNTRIES[i], CUSTOMER_YEARS_JOINED[i]);
        }

        System.out.println("Enter a customer name: ");
        input = sc.next();
        if (input.sEmpty()){
            exitProgram();
        }
        for (Customer customer : customers ) {
            if (customer.matchesName(input)) {
                printShipments(customer, createShipments(SHIPMENT_ORIGINS, SHIPMENT_DESTINATIONS, SHIPMENT_PRICES, hubs));
            }
        }

        exitProgram();
    }

    private static void exitProgram() {
        System.out.println("End of processing");
        System.exit(0);
    }

    public static Hub[] createHubs(String[] codes, String[] cities, String[] countries, int[] fees){
        Hub[] hubs = new Hub[codes.length];
        for (int i = 0; i < codes.length; i++) {
            hubs[i] = new Hub(codes[i], cities[i], countries[i], fees[i]);
        }
        return hubs;
    }

    public static Hub findHub(String city, Hub[] hubs){
        for (Hub hub : hubs) {
            if (hub.matchesCity(city)) {
                return hub;
            }
        }
        return null;
    }

    public static Customer findCustomer(String name, Customer[] customers, int size) {
        for (int i = 0; i < size; i++) {
            if (customers[i].matchesName(name)) {
                return customers[i];
            }
        }
        return null;
    }

    public static int addCustomer(Customer customer, Customer[] customers, int size){
        if (size == customers.length) {
            return size;
        }
        for (int i = 0; i < size; i++) {
            if (customers[i].matchesName(customer.getName())) {
                return size;
            }
        }
        customers[size] = customer;
        return size + 1;
    }

    public static Shipment[] createShipments(String[] originCodes, String[] destinationCodes, int[] prices, Hub[] hubs){
        Shipment[] shipments = new Shipment[originCodes.length];
        for (int i = 0; i < originCodes.length; i++) {
            Hub origin = findHub(originCodes[i], hubs);
            Hub destination = findHub(destinationCodes[i], hubs);
            if (origin == null || destination == null) {
                return null;
            }
            shipments[i] = new Shipment(origin, destination, prices[i]);
        }
        return shipments;
    }

    public static int findAvailableShipments(Hub origin, Hub destination, Shipment[] in, int inSize, Shipment[] out){
        int copied = 0;
        for (int i = 0; i < inSize; i++) {
            if (in[i].matches(origin, destination) && !in[i].isInternational()) {
                out[i] = in[i];
                copied++;
            }
        }
        return copied;
    }

    public static Shipment findCheapestShipment(Shipment[] shipments, int size){
        Shipment cheapest = null;
        for (int i = 0; i < size; i++) {
            if (cheapest == null || shipments[i].getBasePrice() < cheapest.getBasePrice()) {
                cheapest = shipments[i];
            }
        }
        return cheapest;
    }

    public static void printShipments(Customer customer, Shipment[] shipments){
        System.out.println(customer.getName() + "'s shipments:");
        int numShipments = 0;
        for (Shipment shipment : shipments) {
            if (shipment.purchasedBy(customer)) {
                numShipments++;
                System.out.printf("\nShipment #%d Origin: %s Destination: %s Price: $%.2f International: %s\n",
                        numShipments, shipment.getOriginCity(),shipment.getDestinationCity(),(double)shipment.getFinalPrice(),
                        shipment.isInternational());
            }
        }
    }
}
