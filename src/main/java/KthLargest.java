//https://leetcode.com/problems/kth-largest-element-in-a-stream/description/?envType=problem-list-v2&envId=binary-search-tree
import java.util.PriorityQueue;

class KthLargest {
    PriorityQueue<Integer> pq;
    int k;

    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>();
        int n;
        for(int num:nums)
            n = add(num);
        this.k = k;
    }

    public int add(int val) {
        pq.offer(val);
        if(pq.size()>k)
            pq.poll();
        return pq.peek();
    }

    public static void main(String arg[]){
        int k = 3;
        int arr[] = new int[]{4,5,8,2};
        int n1 = 3;
        int n2 = 5;
        int n3 = 10;
        int n4 = 9;
        int n5 = 4;
        KthLargestUsingBST s = new KthLargestUsingBST(k,arr);


        System.out.println(s.add(n1));
        System.out.println(s.add(n2));
        System.out.println(s.add(n3));
        System.out.println(s.add(n4));
        System.out.println(s.add(n5));

    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */