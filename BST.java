public class BST {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }
    public static Node add(int data, Node root) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data < root.data) {
            root.left = add(data, root.left);
        } else {
            root.right = add(data, root.right);
        }
        return root;
    }
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
    public static boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (root.data == key) {
            return true;
        }
        if (key < root.data) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }
    public static Node delete(Node root, int val) {
        if (root.data < val) {
            root.right = delete(root.right, val);
        }
        else if (root.data > val) {
            root.left = delete(root.left, val);
        }
        else {
            //Case 1: No Child
            if (root.left == null && root.right == null) {
                return null;
            }
            //Case 2: One Child
            if (root.left == null || root.right == null) {
                if (root.left != null) {
                    return root.left;
                } else {
                    return root.right;
                }
            }
            //Case 3: Two Children
            else {
                Node inorderSuccessor = root.right;
                while(inorderSuccessor.left != null) {
                    inorderSuccessor = inorderSuccessor.left;
                }
                root.data = inorderSuccessor.data;
                root.right = delete(root.right, inorderSuccessor.data);
            }
        }
        return root;
    }
    public static void main(String args[]) {
        int arr[] = {5, 1, 3, 4, 2, 7};
        Node root = null;

        for (int i=0; i<arr.length; i++) {
            root = add(arr[i], root);
        }

        inorder(root);
        System.out.println();

        delete(root, 4);
        inorder(root);
    }
}
