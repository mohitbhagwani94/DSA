public class ShortestUnsortedSubarray {

    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int left = -1, right = -1;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] < max) {
                right = i;
            } else {
                max = nums[i];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > min) {
                left = i;
            } else {
                min = nums[i];
            }
        }

        return (right == -1) ? 0 : (right - left + 1);
    }

    public static void main(String[] args) {
        // Test cases
        int[] nums1 = {2, 6, 4, 8, 10, 9, 15};
        int[] nums2 = {1, 2, 3, 4};
        int[] nums3 = {1};

        System.out.println("Output for [2,6,4,8,10,9,15]: " + findUnsortedSubarray(nums1)); // Expected: 5
        System.out.println("Output for [1,2,3,4]: " + findUnsortedSubarray(nums2));       // Expected: 0
        System.out.println("Output for [1]: " + findUnsortedSubarray(nums3));             // Expected: 0
    }
}
