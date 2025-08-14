package DSA;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean hasDuplicate(int[] nums) {

        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int len = nums.length;
        nums.clone();

        for(int i=0;i<len;i++){
            if(map.containsKey(nums[i])){
                return false;
            }
            map.put(nums[i],1);
        }
        return true;
    }
}
