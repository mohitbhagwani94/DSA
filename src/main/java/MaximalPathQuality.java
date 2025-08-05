import java.util.*;

public class MaximalPathQuality {
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        List<int[]>[] graph = new ArrayList[n];

        // Initialize graph
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build the graph
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], t = edge[2];
            graph[u].add(new int[]{v, t});
            graph[v].add(new int[]{u, t});
        }

        // Track the max quality found
        int[] maxQuality = new int[1];
        int[] visited = new int[n];

        dfs(0, 0, 0, values, graph, maxTime, visited, maxQuality);

        return maxQuality[0];
    }

    private void dfs(int node, int timeSpent, int quality, int[] values,
                     List<int[]>[] graph, int maxTime, int[] visited, int[] maxQuality) {
        boolean isFirstVisit = visited[node] == 0;
        visited[node]++;
        if (isFirstVisit) {
            quality += values[node];
        }

        if (node == 0) {
            maxQuality[0] = Math.max(maxQuality[0], quality);
        }

        for (int[] neighbor : graph[node]) {
            int nextNode = neighbor[0];
            int travelTime = neighbor[1];

            if (timeSpent + travelTime <= maxTime) {
                dfs(nextNode, timeSpent + travelTime, quality, values, graph, maxTime, visited, maxQuality);
            }
        }

        visited[node]--; // backtrack
    }
    public static void main(String[] args) {
        MaximalPathQuality solver = new MaximalPathQuality();
        int[] values = {0, 32, 10, 43};
        int[][] edges = {{0, 1, 10}, {1, 2, 15}, {0, 3, 10}};
        int maxTime = 49;

        int result = solver.maximalPathQuality(values, edges, maxTime);
        System.out.println(result); // Output: 75
    }
}
