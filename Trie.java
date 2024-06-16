public class Trie {
    static class Node {
        Node children[];
        boolean eow;

        Node() {
            children = new Node[26];
            for(int i=0; i<26; i++) {
                children[i] = null;
            }
            eow = false;
        }
    }
    static Node root = new Node();

    public static void insert(String str) {
        Node curr = root;

        for(int i=0; i<str.length(); i++) {
            int idx = str.charAt(i) - 'a';

            if(curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }
            if (i == str.length()-1) {
                curr.children[idx].eow = true;
            }
            curr = curr.children[idx];
        }
    }
    public static boolean search(String str) {
        Node curr = root;

        for (int i=0; i<str.length(); i++) {
            int idx = str.charAt(i) - 'a';

            if (curr.children[idx] == null) {
                return false;
            }
            if (curr.children[idx].eow == false && i == str.length()-1) {
                return false;
            }
            curr = curr.children[idx];
        }
        return true;
    }
    public static String longString = "";

    public static void longest(Node root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        Node curr = root;

        for (int i=0; i<26; i++) {

            if(curr.children[i] != null && curr.children[i].eow==true) {
                sb.append((char)(i + 'a'));

                if(sb.length() > longString.length()) {
                    longString = sb.toString();
                }

                longest(root.children[i], sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
    public static void main(String args[]) {
        String words[] = {"a", "banana", "app", "appl", "ap", "apply", "apple"};

        for(int i=0; i<words.length; i++) {
            insert(words[i]);
        }

        // System.out.println(search("apple"));
        StringBuilder sb = new StringBuilder("");
        longest(root, sb);

        System.out.println(longString);

    }
}
