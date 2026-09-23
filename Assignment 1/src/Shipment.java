/**
 * Shipment
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-23
 */
public class Shipment {
    Hub origin;
    Hub destination;
    Customer customer;
    int price;

    public Shipment(Hub origin, Hub destination, int price) {
        this.origin = origin;
        this.destination = destination;
        this.price = price;
    }

    public boolean isPurchased() {
        return customer != null;
    }

    public void purchase(Customer customer){
        if (this.customer == null) {
            this.customer = customer;
        }
    }

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

    public boolean matches(Hub origin, Hub destination) {
        return this.origin.equals(origin) && this.destination.equals(destination);
    }

    public boolean purchasedBy(Customer customer){
        return this.customer.equals(customer);
    }

    public int getBasePrice(){
        return this.price;
    }

    public String getOriginCity(){
        return this.origin.getCity();
    }

    public String getDestinationCity(){
        return this.destination.getCity();
    }

    public boolean isInternational(){
        return !this.origin.getCountry().equalsIgnoreCase(this.destination.getCountry());
    }

    public String toString(){
        return "Origin: " + this.origin + "\nDestination: " + this.destination + "\nCustomer: " + this.customer +
                "\nPrice: " + this.price;
    }
}
