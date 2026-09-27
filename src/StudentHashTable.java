import java.util.LinkedList;

/**
 * Requirement 6: Use hashing to support efficient student ID searching.
 * Implemented with separate chaining to handle collisions.
 */
public class StudentHashTable {
    private static final int TABLE_SIZE = 101;
    private LinkedList<Student>[] table;

    @SuppressWarnings("unchecked")
    public StudentHashTable() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) table[i] = new LinkedList<>();
    }

    private int hash(String id) {
        return Math.abs(id.toUpperCase().hashCode()) % TABLE_SIZE;
    }

    public void insert(Student student) {
        int index = hash(student.getStudentId());
        for (Student s : table[index]) {
            if (s.getStudentId().equalsIgnoreCase(student.getStudentId())) return;
        }
        table[index].add(student);
    }

    public Student search(String id) {
        int index = hash(id);
        for (Student s : table[index]) {
            if (s.getStudentId().equalsIgnoreCase(id)) return s;
        }
        return null;
    }

    public boolean remove(String id) {
        int index = hash(id);
        return table[index].removeIf(s -> s.getStudentId().equalsIgnoreCase(id));
    }

    public void clear() {
        for (int i = 0; i < TABLE_SIZE; i++) table[i].clear();
    }
}
