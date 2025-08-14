package LLD.ATM;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> count = new HashMap<>();
        for(int num:nums){
            count.put(num,count.getOrDefault(num,0)+1);
        }

        List<int[]> arr = new ArrayList<>();

        for(Map.Entry<Integer,Integer> entry : count.entrySet()){
            arr.add(new int[]{entry.getValue(),entry.getKey()});
        }

        arr.sort((a,b)->b[0]-a[0]);

        int [] res = new int[k];
        for(int i=0;i<k;i++){
            res[i] = arr.get(i)[1];
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.offer();
        return res;
    }
}
