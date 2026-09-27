import java.util.Stack;

/**
 * Requirement 3: Use a stack to maintain recent actions, deleted records,
 * or an undo/history feature.
 */
public class ActionStack {
    private Stack<String> actions = new Stack<>();

    public void pushAction(String description) {
        actions.push(description);
    }

    public String popAction() {
        if (actions.isEmpty()) {
            System.out.println("No recent actions to undo.");
            return null;
        }
        return actions.pop();
    }

    public void displayActions() {
        if (actions.isEmpty()) {
            System.out.println("No recent actions recorded.");
            return;
        }
        System.out.println("---- Recent Actions (most recent first) ----");
        for (int i = actions.size() - 1; i >= 0; i--) {
            System.out.println((actions.size() - i) + ". " + actions.get(i));
        }
    }

    public boolean isEmpty() {
        return actions.isEmpty();
    }
}
