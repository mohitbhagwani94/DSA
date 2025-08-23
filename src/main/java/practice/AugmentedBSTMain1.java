package practice;

public class AugmentedBSTMain1 {
    public static void main(String[] args) {
        AugmentedBST bst = new AugmentedBST();
        bst.insert(5);
        bst.insert(3);
        bst.insert(6);
        bst.insert(2);
        bst.insert(4);
        bst.insert(1);

        System.out.println(bst.kthSmallest(3)); // Output: 3

        bst.delete(3);
        System.out.println(bst.kthSmallest(3)); // Output: 4
    }
}
class TreeNode{
    int val;
    int size;
    TreeNode left;
    TreeNode right;

    TreeNode(int val){
        this.val = val;
        this.size = 1;
    }
}

class AugmentedBST {
    TreeNode root;
    public void insert(int val) {
        root = insert(root,val);
    }

    public TreeNode insert(TreeNode node, int val){
            if(node == null)
                return new TreeNode(val);

            if(val>=node.val)
                node.right = insert(node.right,val);
            else
                node.left = insert(node.left,val);

            node.size = 1 + getSize(node.left) + getSize(node.right);
            return node;
    }

    public int getSize(TreeNode node) {
        return node!=null ? node.size : 0;
    }


    public int kthSmallest(int k){
        return findKthSmallest(root,k);

    }

    public int findKthSmallest(TreeNode node, int k){
        if (node == null)
            throw new IllegalArgumentException("k is too large");

        int leftCounter = getSize(node.left);

        if(leftCounter + 1 == k )
            return node.val;
        else if(k <= node.size)
            return findKthSmallest(node.left,k);
        else
            return findKthSmallest(node.right,k - leftCounter-1);
    }


    public void delete(int k){
        delete(root,k);
    }

    public TreeNode delete(TreeNode node, int v){
        if(node == null)
            return null;

        if(v < node.val){
            node.left = delete(node.left,v);
        } else if(v > node.val) {
            node.right = delete(node.right,v);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            TreeNode successor = node.right;
            while(successor.left != null){
                successor = successor.left;
            }
            node.val = successor.val;
            node.right = delete(node.right,successor.val);
        }
        node.size = 1 + getSize(node.left) +getSize(node.right);
        return node;
    }
}


