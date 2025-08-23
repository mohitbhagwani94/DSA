//https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/?envType=problem-list-v2&envId=binary-search-tree

class TreeNode {
    int val;
    TreeNode left, right;
    int size; // size of subtree rooted at this node

    TreeNode(int val) {
        this.val = val;
        this.size = 1; // initially, only this node
    }
}

class AugmentedBST {
    private TreeNode root;

    // Helper to get size of a subtree
    private int size(TreeNode node) {
        return node == null ? 0 : node.size;
    }

    // Insert a value into BST and update size
    public TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);

        if (val < node.val) {
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void insert(int val) {
        root = insert(root, val);
    }

    // Find kth smallest
    public int kthSmallest(TreeNode node, int k) {
        if (node == null) throw new IllegalArgumentException("k is too large");

        int leftSize = size(node.left);

        if (k == leftSize + 1) {
            return node.val;
        } else if (k <= leftSize) {
            return kthSmallest(node.left, k);
        } else {
            return kthSmallest(node.right, k - leftSize - 1);
        }
    }

    public int kthSmallest(int k) {
        return kthSmallest(root, k);
    }

    // Delete a node and update size
    public TreeNode delete(TreeNode node, int val) {
        if (node == null) return null;

        if (val < node.val) {
            node.left = delete(node.left, val);
        } else if (val > node.val) {
            node.right = delete(node.right, val);
        } else {
            // Found node to delete
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Replace with inorder successor
            TreeNode successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            node.val = successor.val;
            node.right = delete(node.right, successor.val);
        }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void delete(int val) {
        root = delete(root, val);
    }
}
public class AugmentedBSTMain {
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
