import java.util.*;

public class Algorithms {
    private final CountryRegistry registry;

    public Algorithms(CountryRegistry registry) {
        this.registry = registry;
    }

    public List<City> Astar(City start, City goal) {
        PriorityQueue<TreeNode> openList = new PriorityQueue<>();
        Set<City> closedSet = new HashSet<>();

        TreeNode startNode = new TreeNode(start);
        startNode.setG(0);
        startNode.setH(registry.getHeuristic(start, goal));
        startNode.setF(startNode.getG() + startNode.getH());

        openList.add(startNode);

        while (!openList.isEmpty()) {
            TreeNode current = openList.poll();
            City currentCity = current.getParent();

            if (currentCity.equals(goal)) {
                return reconstructPath(current);
            }

            closedSet.add(currentCity);

            for (City neighbor : registry.getNeighbors(currentCity)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeG = current.getG() + registry.getDistance(currentCity, neighbor);

                TreeNode neighborNode = new TreeNode(neighbor);
                neighborNode.setG(tentativeG);
                neighborNode.setH(registry.getHeuristic(neighbor, goal));
                neighborNode.setF(neighborNode.getG() + neighborNode.getH());
                neighborNode.setParentNode(current);

                openList.add(neighborNode);
            }
        }
        return Collections.emptyList();
    }

    public List<City> bestFirstSearch(City start, City goal) {
        PriorityQueue<TreeNode> openList = new PriorityQueue<>(Comparator.comparingInt(TreeNode::getH));
        Set<City> visited = new HashSet<>();

        TreeNode startNode = new TreeNode(start);
        startNode.setH(registry.getHeuristic(start, goal));
        openList.add(startNode);

        while (!openList.isEmpty()) {
            TreeNode current = openList.poll();
            City currentCity = current.getParent();

            if (currentCity.equals(goal)) {
                return reconstructPath(current);
            }
            visited.add(currentCity);

            for (City neighbor : registry.getNeighbors(currentCity)) {
                if (visited.contains(neighbor)) {
                    continue;
                }

                TreeNode neighborNode = new TreeNode(neighbor);
                neighborNode.setH(registry.getHeuristic(neighbor, goal));
                neighborNode.setParentNode(current);

                openList.add(neighborNode);
            }
        }
        return Collections.emptyList();
    }

    private List<City> reconstructPath(TreeNode node) {
        List<City> path = new ArrayList<>();
        while (node != null) {
            path.add(node.getParent());
            node = node.getParentNode(); // Correct backtracking
        }
        Collections.reverse(path);
        return path;
    }
}
