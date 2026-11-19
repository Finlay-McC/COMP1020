package Lab8;
public class Lab8 {
    public static void main(String args[]) {
        IntLinkedList list = createList();
        IntLinkedList list2 = new IntLinkedList();

        // Exercise 1:
        System.out.println("Exercise 1, expected output is: [ 0 111 222 333 444 ]");
        System.out.println(list);
        System.out.println("Exercise 1, expected output is: [ ]");
        System.out.println(list2);

        // Exercise 2:
        System.out.println("\nsum():");
        System.out.println(list.sum());
        System.out.println(list2.sum());
        list.add(1);
        System.out.println(list.sum());
        list = createList();

        // Exercise 3:
        System.out.println("\nremoveFirst():");
        list.removeFirst();
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
        list = createList();

        // Exercise 4:
        System.out.println("\nreplace():");
        System.out.println(list.replace(0, -999));
        System.out.println(list);
        System.out.println(list.replace(4, 555));
        System.out.println(list);
        System.out.println(list.replace(-1, 12345));
        System.out.println(list);
        System.out.println(list.replace(5, 12345));
        System.out.println(list);
        list = createList();

        // Exercise 5:
        System.out.println("\naddToEnd():");
        list2.addToEnd(1);
        System.out.println(list2);
        list2.addToEnd(2);
        System.out.println(list2);
        list2.addToEnd(3);
        System.out.println(list2);
        list.addToEnd(999);
        System.out.println(list);
        list = createList();

        // Exercise 6:
        System.out.println("\nslice():");
        list2 = list.slice(1, 2);
        System.out.println(list + "," + list2);
        list = createList();
        list2 = list.slice(3, 10);
        System.out.println(list + "," + list2);
        list = createList();
        list2 = list.slice(0, 5);
        System.out.println(list + "," + list2);
        list = createList();
        list2 = list.slice(6, 1);
        System.out.println(list + "," + list2);
        list = createList();
        list2 = list.slice(0, 0);
        System.out.println(list + "," + list2);

        // Exercise 7:
        System.out.println("\nremoveN():");
        list = createList();
        list.removeN(0, 1);
        System.out.println(list);
        list = createList();
        list.removeN(1, 2);
        System.out.println(list);
        list = createList();
        list.removeN(3, 1);
        list.removeN(1, 1);
        System.out.println(list);
        list = createList();
        list.removeN(3, 5);
        System.out.println(list);

        System.out.println("\nEnd of processing.");
    }

    public static IntLinkedList createList() {
        IntLinkedList list = new IntLinkedList();

        list.add(444);
        list.add(333);
        list.add(222);
        list.add(111);
        list.add(0);

        return list;
    }
}

class IntLinkedList {
    private Node top;

    public IntLinkedList() {
        this.top = null;
    }

    public void add(int data) {
        top = new Node(data, top);
    }

    // Exercise 1:
    public String toString() {
        Node curr = top;
        String result = "[ ";

        while (curr != null) {
            result += curr.data + " ";
            curr = curr.next;
        }

        return result + "]";
    }

    // Exercise 2:
    public int sum() {
        int result = 0;
        Node curr = top;
        while (curr != null) {
            result += curr.data;
            curr = curr.next;
        }


        return result;
    }

    // Exercise 3:
    public void removeFirst() {
        if (top != null) {
            top = top.next;
        }
    }

    // Exercise 4:
    public boolean replace(int pos, int value) {
        Node curr = top;
        boolean result = false;

        while (curr != null && pos > 0) {
            curr = curr.next;
            pos--;
        }

        if (curr != null && pos == 0) {
            curr.data = value;
            result = true;
        }

        return result;
    }

    // Exercise 5:
    public void addToEnd(int value) {
        Node curr = top, prev = null;
        while (curr != null) {
            prev = curr;
            curr = curr.next;
        }
        if (prev != null) {
            prev.next = new Node(value, null);
        } else {
            top = new Node(value, null);
        }
    }

    // Exercise 6:
    public IntLinkedList slice(int start, int len) {
        IntLinkedList slice = new IntLinkedList();
        Node curr = top, prev = null;
        int pos = 0;

        while (curr != null && len > 0) {
            if (pos >= start) {
                slice.addToEnd(curr.data);
                if (prev != null) {
                    prev.next = curr.next;
                } else {
                    top = curr.next;
                }
                len--;
            } else {
                prev = curr;
            }
            curr = curr.next;
            pos++;
        }
        return slice;
    }

    // Exercise 7:
    public void removeN(int start, int n) {
        IntLinkedList remove = slice(start, n);
    }

    class Node {
        public int data;
        public Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}