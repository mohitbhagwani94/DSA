import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> edges = new ArrayList<>();

        for(int i = 0; i <= n; i++)
            edges.add(new ArrayList<>());


        for(int[] time:times) {
            int u = time[0];
            int v = time[1];
            int t = time[2];
            edges.get(u).add(new int[]{v,t});
        }
        System.out.println("Edges: "+edges);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        List<Integer> vis = new ArrayList<>();
        pq.add(new int[]{k,0});
        vis.add(k);

        int maxT = 0;
        while(!pq.isEmpty()){
            int node[] = pq.poll();
            int w = node[1];
            if(vis.contains(node[0]))
                continue;
            vis.add(node[0]);
            System.out.println(vis);
            maxT = w;
            for(int neig[] : edges.get(node[0])) {
                if(!vis.contains(neig[0])){
                    pq.add(new int[]{neig[0],neig[1]+w});
                }
            }
        }
        System.out.println(vis);
        return vis.size() == n ? maxT : -1;
    }
    public static void main(String arg[]){
        NetworkDelayTime s = new NetworkDelayTime();
        int [][]times = new int[][]{{1,2,1},{2,3,1},{1,4,4},{3,4,1}};
        s.networkDelayTime(times,4,1);
    }

}