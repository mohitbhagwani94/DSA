package practice;

import java.util.*;

public class PowerGridMaintenance {

    class DSU{
        int parent[];
        DSU(int n){
            parent = new int[n+1];
            for(int i = 1;i <= n;i++){
                parent[i] =i;
            }
        }

        public int find(int n){
            if(parent[n]!=n){
                parent[n]=find(parent[n]);
            }
            return parent[n];
        }

        public void union(int x, int y){
            int p1 = find(x);
            int p2 = find(y);

            if(p1!=p2){
                parent[p2] = p1;
            }

        }
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        DSU dsu = new DSU(c);
        int qlen= queries.length;

        for(int[] conn: connections){
            dsu.union(conn[0],conn[1]);
        }

        boolean onlineStation[] = new boolean[c+1];
        int rootParent[] = new int[c+1];
        Map<Integer,TreeSet<Integer>> onlineGrid = new HashMap<>();

        List<Integer> ans = new ArrayList<>();
        for(int i = 1; i <= c; i++){
            onlineStation[i] = true;
            rootParent[i] = dsu.find(i);
            onlineGrid.computeIfAbsent(rootParent[i], k -> new TreeSet<>());
            onlineGrid.get(rootParent[i]).add(i);
        }

        for(int i = 0; i < qlen; i++){
            int type = queries[i][0];
            int x = queries[i][1];
            if(type == 1){
                if(onlineStation[x]){
                    ans.add(x);
                }else{
                    int rootPar = rootParent[x];
                    TreeSet<Integer>set =  onlineGrid.get(rootPar);
                    if(set == null || set.isEmpty()){
                        ans.add(-1);
                    } else {
                        ans.add(set.first());
                    }
                }
            } else {
                if(onlineStation[x]){
                    onlineStation[x] = false;
                    int rootPar = rootParent[i];
                    TreeSet<Integer> set = onlineGrid.get(rootPar);
                    if(set!=null && !set.isEmpty()){
                        set.remove(x);
                    }
                }
            }
        }
        int res[] = new int[ans.size()];
        for(int i = 0; i < ans.size();i++){
            res[i] = ans.get(i);
        }
        return res;
    }

    // Test
    public static void main(String[] args) {
        PowerGridMaintenance pgm = new PowerGridMaintenance();

//        int c1 = 5;
//        int[][] connections1 = {{1,2}, {2,3}, {3,4}, {4,5}};
//        int[][] queries1 = {{1,3},{2,1},{1,1},{2,2},{1,2}};
//        System.out.println(Arrays.toString(pgm.processQueries(c1, connections1, queries1))); // Output: [3, 2, 3]

        int c2 = 3;
        int[][] connections2 = {};
        int[][] queries2 = {{1,1},{2,1},{1,1}};
        System.out.println(Arrays.toString(pgm.processQueries(c2, connections2, queries2))); // Output: [1, -1]
    }
}
