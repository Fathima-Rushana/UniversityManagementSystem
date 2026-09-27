import java.util.Scanner;

/**
 * Requirement 13: Menu-driven console interface with input validation.
 * Wires together the linked list, stack, queue, BST, hash table and graph
 * components into one application.
 */
public class Main {
    private static StudentLinkedList studentList = new StudentLinkedList();
    private static ActionStack actionStack = new ActionStack();
    private static ServiceQueue serviceQueue = new ServiceQueue();
    private static StudentBST bst = new StudentBST();
    private static StudentHashTable hashTable = new StudentHashTable();
    private static CampusGraph graph = new CampusGraph();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: addStudent(); break;
                case 2: updateStudent(); break;
                case 3: deleteStudent(); break;
                case 4: studentList.displayAll(); break;
                case 5: addServiceRequest(); break;
                case 6: serviceQueue.processNext(); break;
                case 7: actionStack.displayActions(); break;
                case 8: bst.displayInOrder(); break;
                case 9: searchStudent(); break;
                case 10: addLocation(); break;
                case 11: removeLocation(); break;
                case 12: addConnection(); break;
                case 13: removeConnection(); break;
                case 14: graph.displayConnections(); break;
                case 15: traverseGraph(); break;
                case 16: System.out.println("Exiting... Goodbye!"); break;
                default: System.out.println("Invalid choice. Please enter a number between 1 and 16.");
            }
            System.out.println();
        } while (choice != 16);
        sc.close();
    }

    private static void printMenu() {
        System.out.println("=========================================");
        System.out.println(" University Student Record & Campus Route Management System");
        System.out.println("=========================================");
        System.out.println("1.  Add Student Record");
        System.out.println("2.  Update Student Record");
        System.out.println("3.  Delete Student Record");
        System.out.println("4.  Display All Records using Linked List");
        System.out.println("5.  Add Service Request to Queue");
        System.out.println("6.  Process Next Service Request");
        System.out.println("7.  Display Recent Actions using Stack");
        System.out.println("8.  Display Students using BST");
        System.out.println("9.  Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS or DFS");
        System.out.println("16. Exit");
    }

    // ---------- Student record operations ----------

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) { System.out.println("Error: Student ID cannot be empty."); return; }
        if (studentList.searchById(id) != null) {
            System.out.println("Error: Student ID already exists.");
            return;
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Programme: ");
        String programme = sc.nextLine().trim();
        double marks = readDouble("Enter Marks (0-100): ");
        if (marks < 0 || marks > 100) {
            System.out.println("Error: Marks must be between 0 and 100.");
            return;
        }
        Student student = new Student(id, name, programme, marks);
        if (studentList.addStudent(student)) {
            bst.insert(student);
            hashTable.insert(student);
            actionStack.pushAction("Added student " + id + " (" + name + ")");
            System.out.println("Student added successfully.");
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        String id = sc.nextLine().trim();
        if (studentList.searchById(id) == null) {
            System.out.println("Error: Student ID not found.");
            return;
        }
        System.out.print("Enter new Name (leave blank to keep unchanged): ");
        String name = sc.nextLine().trim();
        System.out.print("Enter new Programme (leave blank to keep unchanged): ");
        String programme = sc.nextLine().trim();
        System.out.print("Enter new Marks (leave blank to keep unchanged): ");
        String marksInput = sc.nextLine().trim();
        Double marks = null;
        if (!marksInput.isEmpty()) {
            try {
                marks = Double.parseDouble(marksInput);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid marks value entered.");
                return;
            }
        }
        if (studentList.updateStudent(id, name, programme, marks)) {
            actionStack.pushAction("Updated student " + id);
            System.out.println("Student updated successfully.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = sc.nextLine().trim();
        Student removed = studentList.deleteStudent(id);
        if (removed != null) {
            bst.delete(id);
            hashTable.remove(id);
            actionStack.pushAction("Deleted student " + id + " (" + removed.getName() + ")");
            System.out.println("Student deleted successfully.");
        }
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        String id = sc.nextLine().trim();
        Student result = hashTable.search(id);
        if (result != null) {
            System.out.println("Found: " + result);
        } else {
            System.out.println("Student ID not found.");
        }
    }

    // ---------- Queue operations ----------

    private static void addServiceRequest() {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Enter Request Description (e.g. transcript request): ");
        String desc = sc.nextLine().trim();
        serviceQueue.addRequest(id, desc);
        System.out.println("Service request added to queue.");
    }

    // ---------- Graph operations ----------

    private static void addLocation() {
        System.out.print("Enter new campus location name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) { System.out.println("Error: Location name cannot be empty."); return; }
        if (graph.addLocation(name)) System.out.println("Location added successfully.");
    }

    private static void removeLocation() {
        System.out.print("Enter campus location name to remove: ");
        String name = sc.nextLine().trim();
        if (graph.removeLocation(name)) System.out.println("Location removed successfully.");
    }

    private static void addConnection() {
        System.out.print("Enter first location: ");
        String from = sc.nextLine().trim();
        System.out.print("Enter second location: ");
        String to = sc.nextLine().trim();
        if (graph.addConnection(from, to)) System.out.println("Connection added successfully.");
    }

    private static void removeConnection() {
        System.out.print("Enter first location: ");
        String from = sc.nextLine().trim();
        System.out.print("Enter second location: ");
        String to = sc.nextLine().trim();
        if (graph.removeConnection(from, to)) System.out.println("Connection removed successfully.");
    }

    private static void traverseGraph() {
        System.out.print("Enter starting location: ");
        String start = sc.nextLine().trim();
        System.out.print("Choose traversal type (BFS/DFS): ");
        String type = sc.nextLine().trim();
        if (type.equalsIgnoreCase("BFS")) {
            graph.bfs(start);
        } else if (type.equalsIgnoreCase("DFS")) {
            graph.dfs(start);
        } else {
            System.out.println("Error: Please enter BFS or DFS.");
        }
    }

    // ---------- Input helpers ----------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
