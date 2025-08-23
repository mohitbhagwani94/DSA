//https://leetcode.com/problems/kth-largest-element-in-a-stream/description/?envType=problem-list-v2&envId=binary-search-tree
class KthLargestUsingBST {
    class TreeNode{
        int val;
        int noOfChilds;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
            this.noOfChilds =1;
        }
    }

    int k;
    TreeNode root;

    public KthLargestUsingBST(int k, int[] nums) {
        this.k = k;
        for(int num:nums)
            root = insert(root,num);
    }

    public TreeNode insert(TreeNode root, int num) {
        if(root == null)
            return new TreeNode(num);

        if(num > root.val){
            root.right = insert(root.right,num);
        } else {
            root.left =  insert(root.left,num);
        }
        root.noOfChilds++;
        return root;
    }

    public int add(int val) {
        root = insert(root,val);
        int n = findKthLargest(root,k);
        return n;
    }

    public int findKthLargest(TreeNode node, int k) {
        int rightcount = node.right != null ?  node.right.noOfChilds:0;

        if(rightcount + 1 == k) {
            return node.val;
        } else if(rightcount >= k) {
            return findKthLargest(node.right,k);
        }else{
            return findKthLargest(node.left, k-rightcount-1);
        }
    }

    public static void main(String arg[]) {
        int k = 3;
        int arr[] = new int[]{4, 5, 8, 2};
        int n1 = 3;
        int n2 = 5;
        int n3 = 10;
        int n4 = 9;
        int n5 = 4;
        KthLargestUsingBST s = new KthLargestUsingBST(k, arr);
        System.out.println(s.add(n1));
        System.out.println(s.add(n2));
        System.out.println(s.add(n3));
        System.out.println(s.add(n4));
        System.out.println(s.add(n5));
    }
}
