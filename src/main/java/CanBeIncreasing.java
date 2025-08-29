class CanBeIncreasing {
    public boolean canBeIncreasing(int[] nums) {
        int count = 0;
        int p = 0;
        int len = nums.length;
        for(int i = 0; i < len-1; i++){
            if(nums[i]>=nums[i+1]){
                count++;
                p = i;
            }
        }

        if(count > 1) return false;
        else if(count == 1){
            if(p == 0 || p == len - 2) return true;
            if(nums[p - 1] < nums[p + 1] || nums[p]<nums[p+2]) return true;
            else return false;
        }
        return true;
    }
}