public class FindParent {

    public static int findParent(int processNumber) {
        if (processNumber == 1) return -1;  // Root node

        long curr = 1;
        long next = 2;  // Next available child process number

        while (true) {
            long childStart = next;
            long childEnd = next + curr - 1;

            if (processNumber >= childStart && processNumber <= childEnd) {
                return (int) curr;
            }

            next = childEnd + 1;
            curr++;
        }
    }
    public static void main(String[] args) {
        int processNumber = 100;
        int parent = findParent(processNumber);
        System.out.println("Parent of process " + processNumber + " is: " + parent);
    }
}
