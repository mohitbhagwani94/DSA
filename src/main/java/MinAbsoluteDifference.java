import java.util.*;

     class MinAbsoluteDifference {
        public int minAbsoluteDifference(int[] nums, int x) {
            TreeSet<Integer> set = new TreeSet<>();
            int ans = Integer.MAX_VALUE;

            for (int i = x; i < nums.length; i++) {
                set.add(nums[i - x]); // add element that is at least x indices away

                // find successor (>= nums[i])
                Integer ceil = set.ceiling(nums[i]);
                if (ceil != null) {
                    ans = Math.min(ans, Math.abs(nums[i] - ceil));
                }

                // find predecessor (<= nums[i])
                Integer floor = set.floor(nums[i]);
                if (floor != null) {
                    ans = Math.min(ans, Math.abs(nums[i] - floor));
                }
            }
            return ans;
        }
         public static void main(String[] args) {
             MinAbsoluteDifference sol = new MinAbsoluteDifference();

             // Test Case 1
             int[] nums1 = {4, 3, 2, 4};
             int x1 = 2;
             System.out.println("Output: " + sol.minAbsoluteDifference(nums1, x1)); // Expected 0

             // Test Case 2
             int[] nums2 = {5, 3, 2, 10, 15};
             int x2 = 1;
             System.out.println("Output: " + sol.minAbsoluteDifference(nums2, x2)); // Expected 1

             // Test Case 3
             int[] nums3 = {1, 2, 3, 4};
             int x3 = 3;
             System.out.println("Output: " + sol.minAbsoluteDifference(nums3, x3)); // Expected 3

             // Edge Case: Only two elements
             int[] nums4 = {8, 1};
             int x4 = 1;
             System.out.println("Output: " + sol.minAbsoluteDifference(nums4, x4)); // Expected 7

             // Large numbers
             int[] nums5 = {1000000000, 1, 999999999};
             int x5 = 1;
             System.out.println("Output: " + sol.minAbsoluteDifference(nums5, x5)); // Expected 1
         }
    }

