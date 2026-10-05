/**
 * Hub: A hub for a parcel delivery service.
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-23
 */
public class Hub {
    private final String code;
    private final String city;
    private final String country;
    private final int fee;

    /**
     * constructor for the hub class
     * @param code 3 letter code that identifies the hub
     * @param city Name of the city the hub is located in
     * @param country The country the hub is located in
     * @param fee The fee charged for delivering parcels to the hub
     */
    public Hub(String code, String city, String country, int fee) {
        this.code = code;
        this.city = city;
        this.country = country;
        this.fee = fee;
    }

    /**
     * Checks if the given code matches the hub's code
     * @param code The code to check
     * @return True if the codes match, false otherwise
     */
    public boolean matchesCode(String code){
        return this.code.equals(code);
    }

    /**
     * Checks if the given city matches the hub's city
     * @param city The city to check
     * @return True if the cities match, false otherwise
     */
    public boolean matchesCity(String city){
        return this.city.equalsIgnoreCase(city);
    }

    /**
     * Getter for the hub's city
     * @return The hub's city
     */
    public String getCity(){
        return this.city;
    }

    /**
     * Getter for the hub's country
     * @return The hub's country
     */
    public String getCountry(){
        return this.country;
    }

    /**
     * Getter for the hub's fee
     * @return The hub's fee
     */
    public int getFee(){
        return this.fee;
    }

    /**
     * toString method for the hub class
     * @return Formatted string representation of the hub
     */
    public String toString(){
        return "Code: " + this.code + "\nCity: " + this.city + "\nCountry: " + this.country;
    }
}
