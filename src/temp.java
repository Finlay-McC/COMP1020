import java.util.ArrayList;

public class temp {

    public static void main(String[] parms) {
        ArrayList<Student> students = new ArrayList<>();

        add(students, "Chris", "Cross", 321, 4.28);
        add(students, "Danny", "Du", 899, 3.27);
        add(students, "Alexandra", "Andrews", 506, 4.02);
        add(students, "Betty", "Boschwitz", 102, 3.58);
        add(students, "Archie", "Andrews", 708, 3.21);
        add(students, "Amelia", "Andrew", 325, 3.18);
        add(students, "Duplicate", "Student", 708, 1.11);

        System.out.println("Before sorting:");
        printList(students);

        sort(students);

        System.out.println("\nAfter sorting:");
        printList(students);

        removeDuplicateNums(students);

        System.out.println("\nAfter removing duplicates:");
        printList(students);

        System.out.println("\nEnd of processing.");
    }

    public static void add(ArrayList<Student> students, String first, String last, int num, double gpa) {
        students.add(new Student(first, last, num, gpa));
    }

    public static void sort(ArrayList<Student> students) {
        Student s;
        int p;
        for (int i = students.size() - 1; i > 0; i--) {
            p = i;
            for (int j = 0; j <= i; j++) {
                if (students.get(p).numberLessThanOther(students.get(j))) {
                    p = j;
                }
            }
            if (p != i) {
                s = students.get(i);
                students.set(i, students.get(p));
                students.set(p, s);

            }

            // System.out.printf("pass # %d: %s\n", i+1, students.toString().replaceAll(":[^:]*\\)", ""));
        }
    }

    public static void removeDuplicateNums(ArrayList<Student> students) {
        int removed = 0;
        for (int i = 0; i < students.size() - 2 - removed; i++) {
            for (int j = i + 1; i < students.size() - 1 - removed; j++) {
                if (students.get(i).numberSameAsOther(students.get(j))) {
                    students.remove(j);
                    removed++;
                }
                System.out.println("Tested: " + students.get(i) + " vs. " + students.get(j));
            }
        }
    }

    public static void printList(ArrayList<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%3d - %s\n", i + 1, students.get(i));
        }
    }
}

class Student {
    private String first, last;
    private int num;
    private double gpa;

    public Student(String first, String last, int num, double gpa) {
        this.first = first;
        this.last = last;
        this.num = num;
        this.gpa = gpa;
    }

    public boolean numberLessThanOther(Student other) {
        // If this student's number is less than the other student's
        // number, then this student comes before the other student.
        return num < other.num;
    }

    public boolean numberSameAsOther(Student other) {
        // If this student's number is less than the other student's
        // number, then this student comes before the other student.
        return num == other.num;
    }

    public String toString() {
        return String.format("%d: %s, %s (GPA=%.2f)", num, last, first, gpa);
    }
}
