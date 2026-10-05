import java.util.Scanner;

/**
 * Assignment 1: Comp1020Parcel
 * A simplified parcel delivery system.
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-23
 */
public class Comp1020Parcel {
    // Constants:

    public static final int YEAR = 2026;

    // ---------- Hubs ----------
    public static final String[] HUB_CODES = {"WPG", "YYZ", "YVR", "YUL", "JFK", "LAX", "LHR", "NRT"};
    public static final String[] HUB_CITIES = {"Winnipeg", "Toronto", "Vancouver", "Montreal", "New York",
            "Los Angeles", "London", "Tokyo"};
    public static final String[] HUB_COUNTRIES = {"Canada", "Canada", "Canada", "Canada", "USA", "USA", "UK", "Japan"};
    public static final int[] HUB_FEES = {5, 8, 7, 6, 10, 12, 15, 0};

    // ---------- Shipments ----------
    public static final String[] SHIPMENT_ORIGINS = {"WPG", "WPG", "WPG", "WPG", "YYZ", "YVR", "YYZ", "JFK", "YUL",
            "LAX", "WPG", "LHR"};
    public static final String[] SHIPMENT_DESTINATIONS = {"YYZ", "YYZ", "YYZ", "JFK", "WPG", "LAX", "LHR", "LAX", "NRT",
            "WPG", "YVR", "YYZ"};
    public static final int[] SHIPMENT_PRICES = {40, 35, 35, 90, 45, 110, 250, 75, 333, 101, 50, 199};

    // ---------- Customers ----------
    public static final String[] CUSTOMER_NAMES = {"Alice", "Bob", "Chen", "Dana"};
    public static final String[] CUSTOMER_COUNTRIES = {"Canada", "USA", "Canada", "UK"};
    public static final int[] CUSTOMER_YEARS_JOINED = {2015, 2024, 2021, 2022};

    /**
     * Main method for the program, reads inputs and controls output.
     * @param args
     */
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        Hub[] hubs = createHubs(HUB_CODES, HUB_CITIES, HUB_COUNTRIES, HUB_FEES);
        Shipment[] shipments = createShipments(SHIPMENT_ORIGINS, SHIPMENT_DESTINATIONS, SHIPMENT_PRICES, hubs);
        Customer[] customers = new Customer[100];
        for (int i = 0; i < CUSTOMER_NAMES.length; i++) {
            customers[i] = new Customer(CUSTOMER_NAMES[i], CUSTOMER_COUNTRIES[i], CUSTOMER_YEARS_JOINED[i]);
        }
        int customerSize = CUSTOMER_NAMES.length;
        Customer currentCustomer;
        boolean found = false;
        String country;
        int yearJoined;
        String originCity;
        String destinationCity;
        Shipment[] availableShipments = new Shipment[shipments.length];
        int availableLength;
        int purchased = 0;

        boolean running = true;
        while (running) {
            System.out.println("Enter a customer name: ");
            input = sc.next();
            if (input.isEmpty()){
                running = false;
                break;
            }
            currentCustomer = findCustomer(input, customers, customerSize);
            if (currentCustomer == null) {
                System.out.println("Customer not found, please enter the country of the customer:");
                country = sc.next();
                System.out.println("Please enter the year the customer joined:");
                yearJoined = sc.nextInt();
                if (yearJoined > YEAR || yearJoined < (YEAR - 100)){
                    System.out.println("Invalid input...");
                    continue;
                }
                customerSize = addCustomer(new Customer(input, country, yearJoined), customers, customerSize);
                currentCustomer = customers[customerSize - 1];
            }
            printShipments(currentCustomer, shipments);

            System.out.println("Enter the origin city of the shipment: ");
            originCity = sc.next();
            if (originCity.isEmpty()){
                continue;
            }
            System.out.println("Enter the destination city of the shipment: ");
            destinationCity = sc.next();
            if (destinationCity.isEmpty()){
                continue;
            }
            availableLength = findAvailableShipments(findHub(originCity, hubs), findHub(destinationCity, hubs), shipments, shipments.length, availableShipments);
            if (availableLength == 0) {
                System.out.println("No shipments available, returning to start...");
                continue;
            }
            Shipment cheapestShipment = findCheapestShipment(availableShipments, availableLength);
            cheapestShipment.purchase(currentCustomer);
            purchased++;
            printShipments(currentCustomer, shipments);
            if (purchased == shipments.length){
                System.out.println("All shipments purchased, exiting...");
                running = false;
                break;
            }
        }
        exitProgram();
    }

    /**
     * Exits the program.
     */
    private static void exitProgram() {
        System.out.println("End of processing");
        System.exit(0);
    }

    /**
     * Creates an array of Hun objects filled with the given data.
     *
     * @param codes: An array of Strings containing the hub codes.
     * @param cities: An array of Strings containing the hub cities.
     * @param countries: An array of Strings containing the hub countries.
     * @param fees: An array of ints containing the hub fees.
     * @return The filled array of Hub objects.
     */
    public static Hub[] createHubs(String[] codes, String[] cities, String[] countries, int[] fees){
        Hub[] hubs = new Hub[codes.length];
        for (int i = 0; i < codes.length; i++) {
            hubs[i] = new Hub(codes[i], cities[i], countries[i], fees[i]);
        }
        return hubs;
    }

    /**
     * Finds a Hub object in the given array of Hub objects based on the given String city.
     * @param city The city to search for as a String.
     * @param hubs The array of Hub objects to search in.
     * @return The Hub object with the given city, or null if not found.
     */
    public static Hub findHub(String city, Hub[] hubs){
        for (Hub hub : hubs) {
            if (hub.matchesCity(city)) {
                return hub;
            }
        }
        return null;
    }

    /**
     * Finds a customer in the given array of customers based on the given name.
     * @param name The name of the customer to find.
     * @param customers The array of Customer objects to search in.
     * @param size The size of the customers array.
     * @return The Customer object with the given name, or null if not found.
     */
    public static Customer findCustomer(String name, Customer[] customers, int size) {
        for (int i = 0; i < size; i++) {
            if (customers[i].matchesName(name)) {
                return customers[i];
            }
        }
        return null;
    }

    /**
     * Adds a customer to the given array of customers if the customer does not already exist.
     * @param customer The new Customer object to add.
     * @param customers The array of Customer objects.
     * @param size The current size of the customers array.
     * @return The new size of the customers array.
     */
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

    /**
     * Creates an array of Shipment objects based on the given data.
     * @param originCodes Array of Strings containing origin hub codes.
     * @param destinationCodes Array of Strings containing destination hub codes.
     * @param prices Array of ints containing shipment prices.
     * @param hubs Array of Hub objects.
     * @return The filled array of Shipment objects, or null if any hub code is invalid.
     */
    public static Shipment[] createShipments(String[] originCodes, String[] destinationCodes, int[] prices, Hub[] hubs){
        Shipment[] shipments = new Shipment[originCodes.length];
        Hub origin = null;
        Hub destination = null;
        for (int i = 0; i < originCodes.length; i++) {
            for (Hub hub : hubs) {
                if (hub.matchesCode(originCodes[i])) {
                    origin = hub;
                }
                if (hub.matchesCode(destinationCodes[i])) {
                    destination = hub;
                }
            }
            if (origin == null || destination == null) {
                return null;
            }
            shipments[i] = new Shipment(origin, destination, prices[i]);
        }
        return shipments;
    }

    /**
     * Finds available shipments from the given array of shipments that match the given origin and destination hubs.
     * @param origin The desired origin hub.
     * @param destination The desired destination hub.
     * @param in The array of shipments to search.
     * @param inSize The size of the in array.
     * @param out The array to store the available shipments in.
     * @return The number of available shipments copied from in to out.
     */
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

    /**
     * Finds the cheapest shipment from the given array of shipments.
     * @param shipments Array of Shipment objects to search.
     * @param size Filled size of the shipments array.
     * @return
     */
    public static Shipment findCheapestShipment(Shipment[] shipments, int size){
        Shipment cheapest = null;
        for (int i = 0; i < size; i++) {
            if (cheapest == null || shipments[i].getBasePrice() < cheapest.getBasePrice()) {
                cheapest = shipments[i];
            }
        }
        return cheapest;
    }

    /**
     * Prints the shipments of a customer.
     * @param customer The customer to print shipments for.
     * @param shipments The array of Shipment objects to search.
     */
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
