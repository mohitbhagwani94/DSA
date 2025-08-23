import java.util.*;

class FindCheapestPrice {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> edges = new HashMap<>();

        for(int flight[] : flights){
            int s = flight[0];
            int d = flight[1];
            int p = flight[2];
            edges.computeIfAbsent(s,t->new ArrayList<>()).add(new int[]{d,p});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[2]-b[2]);
        pq.offer(new int[]{src,0,0}); //src,dst,hop

        while(!pq.isEmpty()){
            int curr[] = pq.poll();
            int csrc  = curr[0];
            int cdst  = curr[1];
            int chop = curr[2];
            if(csrc == dst){
                return cdst;
            }
            if(chop > k)
                continue;



            for(int neig[]: edges.get(csrc)) {
                int nd = neig[0];
                int np = neig[1];
                pq.offer(new int[]{nd,np,chop+1});
            }
        }
        return -1;
    }
    public static void main(String arg[]){
        FindCheapestPrice s = new FindCheapestPrice();

        int n=4;
        int [][] flights=new int[][]{{0,1,200},{1,2,100},{1,3,300},{2,3,100}};
        int src=0;
        int dst=3;
        int k=1;
        System.out.println(s.findCheapestPrice(n,flights,src,dst,k));
    }
}
