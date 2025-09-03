import java.util.LinkedList;
import java.util.Queue;

class Center{

    int index;
    int capacity;
    int remainCap;
    int processed;
    Boolean closed;

    Center(int ind, int cap){
        this.index = ind;
        this.capacity = cap;
        this.remainCap = cap;
        this.processed = 0;
        this.closed = false;
    }

    public void reset(){
        remainCap = capacity;
    }

    public boolean processPackage(){
        if(!closed && remainCap > 0){
            remainCap--;
            processed++;
            return true;
        }
        return false;
    }

}

public class DistributionCentersQueue {

    public static int centerMostPackageProcess(int[] centerCapacities, String[] dailyLog) {
        Queue<Center> queue = new LinkedList<>();
        int size = centerCapacities.length;
        Center offline[] = new Center[size];
        for(int i = 0; i < size; i++) {
            Center c = new Center(i,centerCapacities[i]);
            offline[i] = c;
            queue.offer(c);
        }

        int maxProcessed = Integer.MIN_VALUE;
        int maxProcessIdx = -1 ;

        for(String str: dailyLog){
            if(str.equals("PACKAGE")){

                Center curr = queue.peek();
                if(curr.remainCap == 0){
                    Center ex = queue.poll();
                    ex.reset();
                    queue.offer(ex);
                    curr = queue.peek();
                }

                while(curr.closed) {
                    queue.poll();
                    curr = queue.peek();
                }

                if(curr.remainCap > 0 ){
                    curr.processPackage();
                    if(maxProcessed <= curr.processed){
                        maxProcessed = curr.processed;
                        maxProcessIdx = curr.index;
                    }
                    continue;
                }

            } else if(str.startsWith("CLOSURE")) {
                int idx = Integer.parseInt(str.split(" ")[1]);
                Center curr = offline[idx];
                curr.closed = true;
            }
        }

        return maxProcessIdx;
    }
    public static void main(String[] args) {
        int[] capacities1 = {2, 3};
        String[] log1 = {"PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE"};
        System.out.println(centerMostPackageProcess(capacities1, log1)); // Expected 1

        int[] capacities2 = {1, 2, 2};
        String[] log2 = {"PACKAGE", "CLOSURE 1", "PACKAGE", "PACKAGE", "PACKAGE", "CLOSURE 0", "PACKAGE"};
        System.out.println(centerMostPackageProcess(capacities2, log2)); // Expected 2

        int[] capacities3 = {2, 2, 2};
        String[] log3 = {"PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE"};
        System.out.println(centerMostPackageProcess(capacities3, log3)); // Expected 2
    }
}
