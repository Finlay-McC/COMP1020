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

    public static void main(String[] args) {


        System.out.println("End of processing");
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
                System.out.printf("Shipment #%d\nOrigin: %s\nDestination: %s\nPrice: $%.2f\nInternational: %s\n\n",
                        numShipments, shipment.getOriginCity(),shipment.getDestinationCity(),(double)shipment.getFinalPrice(),
                        shipment.isInternational());
            }
        }
    }
}
