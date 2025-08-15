/*1004. Max Consecutive Ones III*/

class LongestOnes {
    public int longestOnes(int[] nums, int k) {
        int l = 0;
        int len = nums.length;
        int max1s = 0;
        int cur1s = 0;
        for(int r = 0; r < len; r++) {
            if(k==0 && nums[r] == 0 ){
                while(k == 0) {
                    if (nums[l] == 0) {
                        k++;
                    }
                    cur1s--;
                    l++;
                }
            }
            if(nums[r] == 1)
                cur1s++;
            else if(k >= 0) {
                k--;
                cur1s++;
            }
            max1s = Math.max(max1s,cur1s);


        }
        return max1s;
    }

    public static void main(String args[]){
        int arr[] = new int[]{1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        LongestOnes s = new LongestOnes();
        System.out.println(s.longestOnes(arr,k));
    }

}