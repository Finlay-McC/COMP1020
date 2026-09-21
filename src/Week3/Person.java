package Week3;

/**
 * Person
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-09-21
 */
public class Person {
    private String name;
    private int age;

    public Person(){
        this.name = "Newborn";
        this.age = 0;
    }

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void haveBirthday(){
        this.age++;
    }

    public int getNumLetterInName(){
        return name.length();
    }

    public String toString(){
        return this.name + " is " + this.age + " years old.";
    }
}
