import java.util.*;

public class PowerGridMaintenance {

    static class DSU {
        int[] parent;

        public DSU(int size) {
            parent = new int[size + 1]; // 1-based indexing
            for (int i = 1; i <= size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx != ry) {
                parent[ry] = rx;
            }
        }
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        DSU dsu = new DSU(c);

        // 1. Build DSU using connections
        for (int[] conn : connections) {
            dsu.union(conn[0], conn[1]);
        }

        // 2. Map: root -> TreeSet of online stations in the component
        Map<Integer, TreeSet<Integer>> componentOnline = new HashMap<>();
        int[] stationRoot = new int[c + 1]; // 1-based indexing
        boolean[] online = new boolean[c + 1];
        Arrays.fill(online, true);

        for (int i = 1; i <= c; i++) {
            int root = dsu.find(i);
            stationRoot[i] = root;
            componentOnline.putIfAbsent(root, new TreeSet<>());
            componentOnline.get(root).add(i);
        }

        // 3. Process queries and collect results
        List<Integer> resultList = new ArrayList<>();

        for (int[] query : queries) {
            int type = query[0];
            int x = query[1];

            if (type == 1) { // maintenance check
                if (online[x]) {
                    resultList.add(x);
                } else {
                    int root = stationRoot[x];
                    TreeSet<Integer> set = componentOnline.get(root);
                    if (set != null && !set.isEmpty()) {
                        resultList.add(set.first()); // Remember this method first to check the first element of the treeset
                    } else {
                        resultList.add(-1);
                    }
                }
            } else if (type == 2) { // go offline
                if (online[x]) {
                    online[x] = false;
                    int root = stationRoot[x];
                    TreeSet<Integer> set = componentOnline.get(root);
                    if (set != null) {
                        set.remove(x);
                    }
                }
            }
        }

        // Convert List<Integer> to int[]
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    // Test
    public static void main(String[] args) {
        PowerGridMaintenance pgm = new PowerGridMaintenance();

        int c1 = 5;
        int[][] connections1 = {{1,2}, {2,3}, {3,4}, {4,5}};
        int[][] queries1 = {{1,3},{2,1},{1,1},{2,2},{1,2}};
        System.out.println(Arrays.toString(pgm.processQueries(c1, connections1, queries1))); // Output: [3, 2, 3]

        int c2 = 3;
        int[][] connections2 = {};
        int[][] queries2 = {{1,1},{2,1},{1,1}};
        System.out.println(Arrays.toString(pgm.processQueries(c2, connections2, queries2))); // Output: [1, -1]
    }
}
