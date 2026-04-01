import java.util.*;

class Main {

    static class Edge {
        String node;
        int distance;

        Edge(String node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    static HashMap<String, ArrayList<Edge>> graph = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    // Add node
    static void addNode() {
        System.out.print("Enter node name: ");
        String node = sc.next();
        graph.put(node, new ArrayList<Edge>());
        System.out.println("Node added!");
    }

    // Add edge
    static void addEdge() {
        System.out.print("Enter source node: ");
        String from = sc.next();

        System.out.print("Enter destination node: ");
        String to = sc.next();

        System.out.print("Enter distance: ");
        int dist = sc.nextInt();

        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            System.out.println("Nodes not found!");
            return;
        }

        graph.get(from).add(new Edge(to, dist));
        graph.get(to).add(new Edge(from, dist));

        System.out.println("Edge added!");
    }

    // Show graph
    static void showGraph() {
        for (String node : graph.keySet()) {
            System.out.print(node + " -> ");
            for (Edge e : graph.get(node)) {
                System.out.print("(" + e.node + "," + e.distance + ") ");
            }
            System.out.println();
        }
    }

    // Dijkstra (simple)
    static void findShortestPath() {

        System.out.print("Enter start node: ");
        String start = sc.next();

        System.out.print("Enter end node: ");
        String end = sc.next();

        HashMap<String, Integer> dist = new HashMap<>();
        HashMap<String, String> prev = new HashMap<>();

        for (String node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }

        dist.put(start, 0);

        ArrayList<String> visited = new ArrayList<>();

        while (visited.size() != graph.size()) {

            String minNode = null;
            int minDist = Integer.MAX_VALUE;

            for (String node : dist.keySet()) {
                if (!visited.contains(node) && dist.get(node) < minDist) {
                    minDist = dist.get(node);
                    minNode = node;
                }
            }

            if (minNode == null) break;

            visited.add(minNode);

            for (Edge e : graph.get(minNode)) {
                int newDist = dist.get(minNode) + e.distance;

                if (newDist < dist.get(e.node)) {
                    dist.put(e.node, newDist);
                    prev.put(e.node, minNode);
                }
            }
        }

        // Print path
        ArrayList<String> path = new ArrayList<>();
        String temp = end;

        while (temp != null) {
            path.add(temp);
            temp = prev.get(temp);
        }

        Collections.reverse(path);

        System.out.println("Shortest Path: " + path);
        System.out.println("Total Distance: " + dist.get(end));
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== SMART ROUTE FINDER =====");
            System.out.println("1. Add Node");
            System.out.println("2. Add Edge");
            System.out.println("3. Show Graph");
            System.out.println("4. Find Shortest Path");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addNode();
                    break;
                case 2:
                    addEdge();
                    break;
                case 3:
                    showGraph();
                    break;
                case 4:
                    findShortestPath();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }
}