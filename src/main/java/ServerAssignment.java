import java.util.*;

public class ServerAssignment {

    public static int[] getServerIndex(int n, int[] arrival, int[] burstTime) {
        int len = arrival.length;
        int result[] = new int[len];

        List<int[]> requests = new ArrayList<>();
        for (int i = 0; i < len; i++)
            requests.add(new int[]{arrival[i], burstTime[i], i});

        //Sorted request to the arrival time
        requests.sort(Comparator.comparingInt(a -> a[0]));

        //priority Queue of a busy server [endTime, SeverIndex]
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // Treeset of the available servers
        TreeSet<Integer> free = new TreeSet<>();

        for (int i = 1; i <= n; i++)
            free.add(i);

        for (int[] req : requests) {
            int arr = req[0];
            int bTime = req[1];
            int index = req[2];

            //Free  up servers
            if (!busy.isEmpty() && busy.peek()[0] <= arr) {
                free.add(busy.poll()[1]);
            }

            if (free.isEmpty()) {
                result[index] = -1;
            } else {
                int assignedServer = free.first();
                free.remove(assignedServer);
                busy.add(new int[]{arr + bTime, assignedServer});
                result[index] = assignedServer;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        int[] arrival = {2, 4, 1, 8, 9};
        int[] burstTime = {7, 9, 2, 4, 5};

        int[] assigned = getServerIndex(n, arrival, burstTime);

        for (int server : assigned) {
            System.out.println(server);
        }
    }
}
