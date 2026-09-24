package Lab2;

/**
 * Car
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-24
 */
public class Car {
    String make;
    String model;
    int year;
    double milage;

    Car (String make, String model, int year, double milage){
        this.make = make;
        this.model = model;
        this.year = year;
        this.milage = milage;
    }

    public String toString(){
        return "Make: " + this.make + "\nModel: " + this.model + "\nYear: " + this.year + "\nMilage: " + this.milage;
    }

    public int getYear(){
        return this.year;
    }

    public double getMilage(){
        return this.milage;
    }

    public boolean matchMake(String make){
        return this.make.equalsIgnoreCase(make);
    }
}
