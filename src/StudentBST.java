/**
 * Requirement 5: Use a BST (or AVL tree) to organize/search student
 * records by Student ID.
 */
public class StudentBST {

    private class Node {
        Student data;
        Node left, right;
        Node(Student data) { this.data = data; }
    }

    private Node root;

    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private Node insertRec(Node node, Student student) {
        if (node == null) return new Node(student);
        int cmp = student.getStudentId().compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) node.left = insertRec(node.left, student);
        else if (cmp > 0) node.right = insertRec(node.right, student);
        // equal IDs are rejected earlier at the linked-list level
        return node;
    }

    public Student search(String id) {
        Node current = root;
        while (current != null) {
            int cmp = id.compareToIgnoreCase(current.data.getStudentId());
            if (cmp == 0) return current.data;
            current = cmp < 0 ? current.left : current.right;
        }
        return null;
    }

    public boolean delete(String id) {
        if (search(id) == null) return false;
        root = deleteRec(root, id);
        return true;
    }

    private Node deleteRec(Node node, String id) {
        if (node == null) return null;
        int cmp = id.compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) node.left = deleteRec(node.left, id);
        else if (cmp > 0) node.right = deleteRec(node.right, id);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.getStudentId());
        }
        return node;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No records in tree.");
            return;
        }
        System.out.println("---- Students Sorted by ID (BST In-Order) ----");
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.println(node.data);
        inOrderRec(node.right);
    }

    public void clear() { root = null; }
}
