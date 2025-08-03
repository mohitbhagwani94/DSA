import java.util.*;

public class PowerTwoReducer {
    public static int getMinOperations(long n) {
        Set<Long> visited = new HashSet<>();
        Queue<long[]> queue = new LinkedList<>();
        queue.offer(new long[]{n, 0});
        visited.add(n);

        while (!queue.isEmpty()) {
            long[] current = queue.poll();
            long value = current[0];
            int steps = (int) current[1];

            if (value == 0) return steps;

            for (int i = 0; i < 60; i++) {
                long power = 1L << i;

                // Try subtracting
                long nextSub = value - power;
                if (nextSub >= 0 && visited.add(nextSub)) {
                    queue.offer(new long[]{nextSub, steps + 1});
                }

                // Try adding
                long nextAdd = value + power;
                if (nextAdd < (1L << 60) && visited.add(nextAdd)) {
                    queue.offer(new long[]{nextAdd, steps + 1});
                }
            }
        }

        return -1; // Should never reach here
    }

    public static void main(String[] args) {
        System.out.println(getMinOperations(5));   // Output: 2
        System.out.println(getMinOperations(7));   // Output: 2
        System.out.println(getMinOperations(21));  // Output: 3
    }
}
