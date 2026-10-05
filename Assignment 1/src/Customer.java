/**
 * Customer: A customer of the parcel delivery service.
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-23
 */
public class Customer {
    private final String name;
    private final String country;
    private final int yearJoined;

    /**
     * Constructor for the Customer class
     * @param name The name of the customer
     * @param country The country the customer is from
     * @param yearJoined The year the customer joined the service
     */
    public Customer(String name, String country, int yearJoined) {
        this.name = name;
        this.country = country;
        this.yearJoined = yearJoined;
    }

    /**
     * Getter for the customer's name
     * @return The customer's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Checks if the customer's name matches the given name
     * @param name The name to check
     * @return True if the names match, false otherwise
     */
    public boolean matchesName(String name){
        return this.name.equalsIgnoreCase(name);
    }

    /**
     * Checks if the customer is a loyal customer (joined 2021 or earlier)
     * @return True if the customer is loyal, false otherwise
     */
    public boolean isLoyal(){
        return this.yearJoined <= 2021;
    }

    /**
     * toString method for the Customer class
     * @return Formatted string representation of the customer
     */
    public String toString(){
        return "Name: " + this.name + "\nCountry: " + this.country + "\nYear Joined: " + this.yearJoined;
    }
}
