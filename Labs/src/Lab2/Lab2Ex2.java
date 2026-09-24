package Lab2;

/**
 * Lab2Ex2
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-24
 */
public class Lab2Ex2 {
    public static void main(String[] args) {
        Car[] cars = createCars();
        printCars(cars);
        printMake("Toyota", cars);
        printMake("Ford", cars);
        printMake("Ladas", cars);
        printYearRange(2000, 2010, cars);
        printYearRange(1900, 2000, cars);
        printYearRange(2012, 2020, cars);
        goodMileage(true, cars);
        goodMileage(false, cars);

        System.out.println("End of processing");
    }

    public static void printCars(Car[] cars){
        for (Car car : cars) {
            System.out.println(car.toString());
        }
    }

    public static void printMake(String make, Car[] cars){
        for (Car car : cars){
            if (car.matchMake(make)){
                System.out.println(car.toString());
            }
        }
    }

    public static void printYearRange(int start, int end, Car[] cars){
        for (Car car : cars){
            if (car.getYear() >= start && car.getYear() <= end){
                System.out.println(car.toString());
            }
        }
    }

    public static void goodMileage(boolean getGood, Car[] cars){
        if (getGood){
            for (Car car : cars){
                if (car.getMilage() <= 10){
                    System.out.println(car.toString());
                }
            }
        } else {
            for (Car car : cars){
                if (car.getMilage() > 10){
                    System.out.println(car.toString());
                }
            }
        }
    }
    public static Car[] createCars(){
        Car[] cars = {new Car("Toyota","Corolla", 1999, 8.5),
                new Car("Toyota", "Camry", 2015, 13.3),
                new Car("Ford", "Mustang", 2018, 10),
                new Car("Ford", "Focus", 2002, 9.6),
                new Car("Ford", "Model T", 1920, 20.5),
                new Car("Honda", "Fit", 2008, 7.6),
                new Car("Honda", "Fit", 2003, 7.8),
                new Car("Honda", "Civic", 2010, 10.5),
                new Car("Chevrolet","S10",1997,14.1),
                new Car("Voltswagon","Beetle", 1952,11.4),
        };
        return cars;
    }
}
