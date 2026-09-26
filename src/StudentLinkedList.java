import java.util.ArrayList;
import java.util.List;

/**
 * Requirement 2: Use a linked list to store and manage student records.
 * Requirement 12: add, update, delete, search, and display operations.
 * Requirement 14: handle invalid inputs, duplicate IDs, missing records, invalid marks.
 */
public class StudentLinkedList {

    private class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private Node head;
    private int size;

    public boolean addStudent(Student student) {
        // Validate Student ID is not empty/null
        if (student.getStudentId() == null || student.getStudentId().trim().isEmpty()) {
            System.out.println("Error: Student ID cannot be empty.");
            return false;
        }

        // Validate marks are within an acceptable range before adding
        if (student.getMarks() < 0 || student.getMarks() > 100) {
            System.out.println("Error: Marks must be between 0 and 100.");
            return false;
        }

        // Prevent duplicate Student IDs
        if (searchById(student.getStudentId()) != null) {
            System.out.println("Error: Student ID " + student.getStudentId() + " already exists.");
            return false;
        }

        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) current = current.next;
            current.next = newNode;
        }
        size++;
        return true;
    }

    public boolean updateStudent(String id, String name, String programme, Double marks) {
        Student s = searchById(id);
        if (s == null) {
            System.out.println("Error: Student ID " + id + " not found.");
            return false;
        }
        if (name != null && !name.trim().isEmpty()) s.setName(name);
        if (programme != null && !programme.trim().isEmpty()) s.setProgramme(programme);
        if (marks != null) {
            if (marks < 0 || marks > 100) {
                System.out.println("Error: Marks must be between 0 and 100.");
                return false;
            }
            s.setMarks(marks);
        }
        return true;
    }

    public Student deleteStudent(String id) {
        Node current = head, prev = null;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(id)) {
                // If deleting the head node, simply move head forward
                if (prev == null) {
                    head = current.next;
                } else {
                    // Otherwise, reconnect the chain by skipping the current node:
                    // link the previous node directly to the node after current
                    prev.next = current.next;
                }
                size--;
                return current.data;
            }
            prev = current;
            current = current.next;
        }
        System.out.println("Error: Student ID " + id + " not found.");
        return null;
    }

    public Student searchById(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(id)) return current.data;
            current = current.next;
        }
        return null;
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("---- All Student Records (Linked List) ----");
        Node current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count++ + ". " + current.data);
            current = current.next;
        }
        System.out.println("Total students: " + size);
    }

    public int getSize() { return size; }

    public List<Student> toList() {
        List<Student> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }
}
