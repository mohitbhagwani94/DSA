import java.util.*;

public class LIS {
    public int lengthOfLIS(int[] nums) {
        List<Integer> dp = new ArrayList<>();
        dp.add(nums[0]);

        int LIS = 1;
        for (int i = 1; i < nums.length; i++) {
            if (dp.get(dp.size() - 1) < nums[i]) {
                // Extend the LIS
                dp.add(nums[i]);
                LIS++;
            } else {
                // Find the index where nums[i] should be placed
                int idx = Collections.binarySearch(dp, nums[i]);
                if (idx < 0) {
                    idx = -idx - 1;  // convert to insertion point
                }
                dp.set(idx, nums[i]); // replace to maintain smallest possible tails
            }
        }

        return LIS;
    }

    public static void main(String[] args) {
        LIS sol = new LIS();

        // Test case 1
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("LIS length = " + sol.lengthOfLIS(nums1));
        // Expected: 4 (sequence: [2,3,7,18])

        // Test case 2
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("LIS length = " + sol.lengthOfLIS(nums2));
        // Expected: 4 (sequence: [0,1,2,3])

        // Test case 3
        int[] nums3 = {7, 7, 7, 7, 7};
        System.out.println("LIS length = " + sol.lengthOfLIS(nums3));
        // Expected: 1 (sequence: [7])

        // Test case 4
        int[] nums4 = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println("LIS length = " + sol.lengthOfLIS(nums4));
        // Expected: 6 (sequence: [1,3,4,5,6,10] or [1,3,6,7,9,10])
    }
}
