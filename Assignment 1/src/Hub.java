/**
 * Hub
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-23
 */
public class Hub {
    String code;
    String city;
    String country;
    int fee;

    public Hub(String code, String city, String country, int fee) {
        this.code = code;
        this.city = city;
        this.country = country;
        this.fee = fee;
    }

    public boolean matchesCode(String code){
        return this.code.equals(code);
    }

    public boolean matchesCity(String city){
        return this.city.equalsIgnoreCase(city);
    }

    public String getCity(){
        return this.city;
    }

    public String getCountry(){
        return this.country;
    }

    public int getFee(){
        return this.fee;
    }

    public String toString(){
        return "Code: " + this.code + "\nCity: " + this.city + "\nCountry: " + this.country;
    }
}
