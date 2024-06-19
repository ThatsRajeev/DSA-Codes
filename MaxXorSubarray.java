public class MaxXorSubarray {
    public class Node {
        Node links[];

        Node() {
            links = new Node[2];
        }

        boolean doesExist(int i) {
            return links[i] != null;
        }

        void createNode(int i, Node node) {
            links[i] = node;
        }
    }
    public static Node root;

    public class Trie {
        Trie() {
            root = new Node();
        }

        void insert(int n) {
            Node curr = root;
            for(int i=31; i>=0; i--) {
                int bit = (n >> i) & 1;

                if(!curr.doesExist(bit)) {
                    curr.createNode(bit, new Node());
                }
                curr = curr.links[bit];
            }
        }

        int findMax(int n) {
            Node curr = root;
            int maxi = 0;
            for(int i=31; i>=0; i--) {
                int bit = (n >> i) & 1;

                if(curr.doesExist(1-bit)) {
                    maxi = maxi | (1 << i);
                    curr = curr.links[1-bit];
                } else {
                    curr = curr.links[bit];
                }
            }
            return maxi;
        }
    }
}
