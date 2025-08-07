public class MinOperationsToZero {
    public static int minOperations(int n) {
        int ops = 0;
        while (n != 0) {
            if ((n & 1) == 0) {
                // If n is even, just shift right (divide by 2)
                n >>= 1;
            } else {
                // If last two bits are 11 and n != 3, add 1 (to carry)
                if ((n & 3) == 3 && n != 3) {
                    n += 1;
                } else {
                    // Otherwise, subtract 1
                    n -= 1;
                }
                ops++;
            }
        }
        return ops;
    }

    // Sample usage
    public static void main(String[] args) {
        int n1 = 39;
//        int n2 = 54;
        int n3 = 8;
//
        System.out.println("Minimum operations for " + n1 + ": " + minOperations(n1)); // Output: 3
//        System.out.println("Minimum operations for " + n2 + ": " + minOperations(n2)); // Output: 3
        System.out.println("Minimum operations for " + n3 + ": " + minOperations(n3));
    }
}
