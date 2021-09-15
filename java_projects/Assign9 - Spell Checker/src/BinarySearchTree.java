//Isaac Airmet Assn9
// Binary Search Tree Class
public class BinarySearchTree<T extends Comparable<T>> {
    // Root data field
    private TreeNode<T> root;
    // Display method
    public void display(String message) {
        System.out.println(message);
        this.displayInOrder(root);
    }
    // Helper display method
    private void displayInOrder(TreeNode<T> node) {
        if (node == null) return;

        displayInOrder(node.left);
        System.out.printf("%s \n", node.value);
        displayInOrder(node.right);
    }
    // Search method
    public boolean search(T search) {
        TreeNode<T> node = root;
        boolean found = false;
        while (!found && node != null) {
            if (node.value.compareTo(search) == 0) {
                found = true;
            } else if (node.value.compareTo(search) < 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return found;
    }
    // Insert Method
    public boolean insert(T value) {
        if (root == null) {
            root = new TreeNode<>(value);
        } else {
            // Search/find the insert location
            TreeNode<T> parent = null;
            TreeNode<T> node = root;
            while (node != null) {
                parent = node;
                if (node.value.compareTo(value) < 0) {
                    node = node.right;
                } else if (parent.value.compareTo(value) == 0){
                    return false;
                } else {
                    node = node.left;
                }
            }
            // Add the node to the tree
            TreeNode<T> newNode = new TreeNode<>(value);
            if (parent.value.compareTo(value) < 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
        }
        return true;
    }
    // Method to remove something in the tree
    public boolean remove(T value) {
        // Step 1: find the node to remove
        TreeNode<T> parent = null;
        TreeNode<T> node = root;
        boolean done = false;
        while (!done) {
            if (node == null) {
                return false;
            }
            if (value.compareTo(node.value) < 0) {
                parent = node;
                node = node.left;
            }
            else if (value.compareTo(node.value) > 0) {
                parent = node;
                node = node.right;
            } else {
                done = true;
            }
        }
        // Step 2a: case for no left child
        if (node.left == null) {
            if (parent == null) {
                root = node.right;
            }
            else {
                if (value.compareTo(parent.value) < 0) {
                    parent.left = node.right;
                }
                else {
                    parent.right = node.right;
                }
            }
        }
        else { // Step 2b: case for left child
            TreeNode<T> parentOfRight = node;
            TreeNode<T> rightMost = node.left;
            while (rightMost.right != null) {
                parentOfRight = rightMost;
                rightMost = rightMost.right;
            }
            // Copy the largest value into the node being removed
            node.value = rightMost.value;
            if (parentOfRight.right == rightMost) {
                parentOfRight.right = rightMost.left;
            }
            else {
                parentOfRight.left = rightMost.left;
            }
        }
        return true;
    }
    // Method to count all the nodes
    public int numberNodes() {
        int count = 0;
        count += numberNodes(root);
        return count;
    }
    // Helper methods to get node count
    private int numberNodes(TreeNode<T> node){
        if (node == null) return 0;
        int count = 1;
        count += numberNodes(node.right);
        count += numberNodes(node.left);
        return count;
    }
    // Method to get the number of leaf nodes
    public int numberLeafNodes(){
        int count = 0;
        count += numberLeafNodes(root);
        return count;
    }
    // Helper method to get the number of leaf nodes
    private int numberLeafNodes(TreeNode<T> node){
        int count = 0;
        if (node == null) return 0;
        if (node.right == null && node.left == null) return 1;
        count += numberLeafNodes(node.left);
        count += numberLeafNodes(node.right);
        return count;
    }
    // Method to get the height of the tree
    public int height(){
        return height(root);
    }
    // Helper method to get the height of the tree
    private int height(TreeNode<T> node){
        if (node == null) return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }
    // Tree node class
    private class TreeNode<T> {
        public T value;
        // Left and right nodes in the tree
        public TreeNode<T> left;
        public TreeNode<T> right;
        // Constructor
        public TreeNode(T value) {
            this.value = value;
        }
    }
}
