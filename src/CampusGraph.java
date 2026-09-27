import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Requirements 7-11: Use a graph to represent campus locations and their
 * connections, using an adjacency list. Provides add/remove operations,
 * neighbour display, and BFS/DFS traversal.
 */
public class CampusGraph {
	private Map<String, List<String>> adjList = new LinkedHashMap<>();

	public boolean addLocation(String name) {
		if (adjList.containsKey(name)) {
			System.out.println("Error: Location '" + name + "' already exists.");
			return false;
		}
		adjList.put(name, new ArrayList<>());
		return true;
	}

	public boolean removeLocation(String name) {
		if (!adjList.containsKey(name)) {
			System.out.println("Error: Location '" + name + "' not found.");
			return false;
		}
		adjList.remove(name);
		for (List<String> neighbours : adjList.values()) {
			neighbours.remove(name);
		}
		return true;
	}

	public boolean addConnection(String from, String to) {
		if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
			System.out.println("Error: Both locations must exist before connecting them.");
			return false;
		}
		if (adjList.get(from).contains(to)) {
			System.out.println("Error: Connection already exists.");
			return false;
		}
		adjList.get(from).add(to);
		adjList.get(to).add(from);
		return true;
	}

	public boolean removeConnection(String from, String to) {
		if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
			System.out.println("Error: One or both locations not found.");
			return false;
		}
		boolean removed = adjList.get(from).remove(to);
		adjList.get(to).remove(from);
		if (!removed) System.out.println("Error: Connection did not exist.");
		return removed;
	}

	public void displayConnections() {
		if (adjList.isEmpty()) {
			System.out.println("No campus locations added yet.");
			return;
		}
		System.out.println("---- Campus Network (Adjacency List) ----");
		for (String location : adjList.keySet()) {
			System.out.println(location + " -> " + adjList.get(location));
		}
	}

	public void bfs(String start) {
		if (!adjList.containsKey(start)) {
			System.out.println("Error: Location '" + start + "' not found.");
			return;
		}
		Set<String> visited = new LinkedHashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.add(start);
		visited.add(start);
		System.out.print("BFS Traversal from " + start + ": ");
		while (!queue.isEmpty()) {
			String current = queue.poll();
			System.out.print(current + " ");
			for (String neighbour : adjList.get(current)) {
				if (!visited.contains(neighbour)) {
					visited.add(neighbour);
					queue.add(neighbour);
				}
			}
		}
		System.out.println();
	}

	public void dfs(String start) {
		if (!adjList.containsKey(start)) {
			System.out.println("Error: Location '" + start + "' not found.");
			return;
		}
		Set<String> visited = new LinkedHashSet<>();
		System.out.print("DFS Traversal from " + start + ": ");
		dfsRec(start, visited);
		System.out.println();
	}

	private void dfsRec(String current, Set<String> visited) {
		visited.add(current);
		System.out.print(current + " ");
		for (String neighbour : adjList.get(current)) {
			if (!visited.contains(neighbour)) {
				dfsRec(neighbour, visited);
			}
		}
	}

	public boolean hasLocation(String name) {
		return adjList.containsKey(name);
	}
}