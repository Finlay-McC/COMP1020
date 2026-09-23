/**
 * Customer
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-23
 */
public class Customer {
    String name;
    String country;
    int yearJoined;

    public Customer(String name, String country, int yearJoined) {
        this.name = name;
        this.country = country;
        this.yearJoined = yearJoined;
    }

    public String getName(){
        return this.name;
    }

    public boolean matchesName(String name){
        return this.name.equalsIgnoreCase(name);
    }

    public boolean isLoyal(){
        return this.yearJoined <= 2021;
    }

    public String toString(){
        return "Name: " + this.name + "\nCountry: " + this.country + "\nYear Joined: " + this.yearJoined;
    }
}
