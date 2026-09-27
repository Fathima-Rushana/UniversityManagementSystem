import java.util.LinkedList;
import java.util.Queue;

/**
 * Requirement 4: Use a queue to manage student service requests
 * in order of arrival.
 */
public class ServiceQueue {
    private Queue<String> requests = new LinkedList<>();

    public void addRequest(String studentId, String description) {
        requests.add("Student " + studentId + ": " + description);
    }

    public String processNext() {
        if (requests.isEmpty()) {
            System.out.println("No service requests in queue.");
            return null;
        }
        String next = requests.poll();
        System.out.println("Processed request: " + next);
        return next;
    }

    public void displayQueue() {
        if (requests.isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("---- Pending Service Requests (in order) ----");
        int i = 1;
        for (String r : requests) {
            System.out.println(i++ + ". " + r);
        }
    }
}
