public class Main {
    static final long MOD = 1_000_000_007L;

    public static long sumAndPrefixes(long L, long R) {
        long res = 0;

        for (int b = 0; b <= 60; b++) {
            if (((L >> b) & 1) == 0) continue; // bit b not set in L

            long period = 1L << (b + 1);
            long blockStart = (L / period) * period + (1L << b);
            long blockEnd = blockStart + (1L << b) - 1;
            long endHere = Math.min(R, blockEnd);

            if (endHere >= L) {
                long cnt = endHere - L + 1;
                long bitValue = (1L << b) % MOD;
                res = (res + (bitValue * (cnt % MOD)) % MOD) % MOD;
            }
        }
        return res % MOD;
    }

    public static void main(String[] args) {
        // Embedded test cases
        int T = 3;
        long[][] testCases = {
                {5, 7},
                {1, 1},
                {8, 15}
        };

        for (int t = 0; t < T; t++) {
            long L = testCases[t][0];
            long R = testCases[t][1];
            System.out.println(sumAndPrefixes(L, R));
        }
    }
}