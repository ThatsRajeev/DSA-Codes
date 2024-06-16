import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeYT {
    static class Node {
        int data;
        Node leftNode;
        Node rightNode;

        Node(int data) {
            this.data = data;
            this.leftNode = null;
            this.rightNode = null;
        }
    }
    public static class BinaryTree {
        static int idx = -1;
        public Node buildTree(int nodes[]) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }
            Node n = new Node(nodes[idx]);

            n.leftNode = buildTree(nodes);
            n.rightNode = buildTree(nodes);

            return n;
        }
    }

    public static void preorder(Node root) {
        if (root == null) {
            System.out.print(-1 + " ");
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.leftNode);
        preorder(root.rightNode);
    }
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.leftNode);
        System.out.print(root.data + " ");
        inorder(root.rightNode);
    }
    public static void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.leftNode);
        postorder(root.rightNode);
        System.out.print(root.data + " ");

    }
    public static void levelorder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()) {
            Node currNode = q.remove();
            if (currNode == null) {
                System.out.println();
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                System.out.print(currNode.data + " ");
                if(currNode.leftNode != null){
                    q.add(currNode.leftNode);
                }
                if(currNode.rightNode != null){
                    q.add(currNode.rightNode);
                }
            }
        }
    }
    public static int countOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int leftSubtrees = countOfNodes(root.leftNode);
        int rightSubtrees = countOfNodes(root.rightNode);

        return leftSubtrees + rightSubtrees + 1;
    }
    public static int sumOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int leftSubtrees = sumOfNodes(root.leftNode);
        int rightSubtrees = sumOfNodes(root.rightNode);

        return leftSubtrees + rightSubtrees + root.data;
    }
    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.leftNode);
        int rightHeight = height(root.rightNode);

        int myHeight = Math.max(leftHeight, rightHeight) + 1;

        return myHeight;
    }
    public static int dummyDiameter = 0;
    public static int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = diameter(root.leftNode);
        int rightHeight = diameter(root.rightNode);

        int maxHeight = Math.max(leftHeight, rightHeight) + 1;
        int myDiameter = leftHeight + rightHeight + 1;

        if (myDiameter > dummyDiameter) {
            dummyDiameter = myDiameter;
        }
        return maxHeight;
    }
    public static void main(String args[]) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree bt = new BinaryTree();
        Node root = bt.buildTree(nodes);
        postorder(root);
        // System.out.println(root.data);
        // diameter(root);
        // System.out.println(dummyDiameter);
    }
}
