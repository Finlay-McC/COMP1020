/**
 * Shipment: A shipment of parcels.
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-23
 */
public class Shipment {
    private final Hub origin;
    private final Hub destination;
    private Customer customer;
    private final int price;

    /**
     * Constructor for the Shipment class
     * @param origin The origin Hub object
     * @param destination The destination Hub object
     * @param price The price of the shipment
     */
    public Shipment(Hub origin, Hub destination, int price) {
        this.origin = origin;
        this.destination = destination;
        this.price = price;
    }

    /**
     * Checks if the shipment has been purchased
     * @return True if the shipment has been purchased, false otherwise
     */
    public boolean isPurchased() {
        return customer != null;
    }

    /**
     * Purchases the shipment for a customer
     * @param customer The customer to purchase the shipment for
     */
    public void purchase(Customer customer){
        if (this.customer == null) {
            this.customer = customer;
        }
    }

    /**
     * Calculates the final price of the shipment based on the customer's loyalty status
     * @return The final price of the shipment
     */
    int getFinalPrice(){
        double finalPrice;
        if (customer.isLoyal()) {
            finalPrice = price * 0.85 + destination.getFee();
            return (int)finalPrice;
        }
        else {
            finalPrice = price + destination.getFee();
            return (int)finalPrice;
        }
    }

    /**
     * Checks if the shipment matches the given origin and destination hubs
     * @param origin The origin hub to check
     * @param destination The destination hub to check
     * @return True if the shipment matches the given hubs, false otherwise
     */
    public boolean matches(Hub origin, Hub destination) {
        return this.origin.equals(origin) && this.destination.equals(destination);
    }

    /**
     * Checks if the shipment has been purchased by the given customer
     * @param customer The customer to check
     * @return True if the shipment has been purchased by the customer, false otherwise
     */
    public boolean purchasedBy(Customer customer){
        if (this.customer != null) {
            return this.customer.equals(customer);
        } else {
            return false;
        }
    }

    /**
     * Getter for the base price of the shipment
     * @return The base price of the shipment
     */
    public int getBasePrice(){
        return this.price;
    }

    /**
     * Getter for the origin city of the shipment
     * @return The origin city of the shipment
     */
    public String getOriginCity(){
        return this.origin.getCity();
    }

    /**
     * Getter for the destination city of the shipment
     * @return the destination city of the shipment
     */
    public String getDestinationCity(){
        return this.destination.getCity();
    }

    /**
     * Checks if the shipment is international
     * @return true if the shipment is international, false otherwise
     */
    public boolean isInternational(){
        return !this.origin.getCountry().equalsIgnoreCase(this.destination.getCountry());
    }

    /**
     * toString method for the Shipment class
     * @return Formatted string representation of the shipment
     */
    public String toString(){
        return "Origin: " + this.origin + "\nDestination: " + this.destination + "\nCustomer: " + this.customer +
                "\nPrice: " + this.price;
    }
}
