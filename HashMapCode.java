import java.util.ArrayList;
import java.util.LinkedList;

public class HashMapCode {
    public static class HashMap<K, V> {
        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
        LinkedList<Node> buckets[];
        int n;  //no of nodes
        int N;  //no of buckets

        @SuppressWarnings("unchecked")
        HashMap() {
            this.buckets = new LinkedList[4];
            this.N = 4;

            for(int i=0; i<4; i++) {
                this.buckets[i] = new LinkedList<>();
            }
        }

        private int hashFunction(K key) {
            int bi = key.hashCode();
            return Math.abs(bi) % N;
        }

        @SuppressWarnings("unchecked")
        private void rehash() {
            LinkedList<Node> oldBucket[] = buckets;
            buckets = new LinkedList[N*2];
            N = N*2;

            for(int i=0; i<N; i++) {
                this.buckets[i] = new LinkedList<>();
            }

            for(int i=0; i<oldBucket.length; i++) {
                LinkedList<Node> ll = oldBucket[i];
                for(int j=0; j<ll.size(); j++) {
                    Node node = ll.get(j);
                    put(node.key, node.value);
                }
            }
        }

        public void put(K key, V value) {
            int bi = hashFunction(key);  //bucket index
            LinkedList<Node> ll = buckets[bi];

            for(int i=0; i<ll.size(); i++) {
                if (ll.get(i).key == key) {
                    ll.get(i).value = value;   //update value
                    return;
                }
            }
            buckets[bi].add(new Node(key, value));  //add new node
            n++;

            double lambda = (double)(n/N);
            if (lambda > 2.0) {
                rehash();
            }
        }
        public V get(K key) {
            int bi = hashFunction(key);
            LinkedList<Node> ll = buckets[bi];

            for(int i=0; i<ll.size(); i++) {
                if (ll.get(i).key == key) {
                    return ll.get(i).value;
                }
            }
            return null;
        }
        public V remove(K key) {
            int bi = hashFunction(key);
            LinkedList<Node> ll = buckets[bi];

            for(int i=0; i<ll.size(); i++) {
                if (ll.get(i).key == key) {
                    Node node = ll.remove(i);
                    n--;
                    return node.value;
                }
            }
            return null;
        }
        public boolean isEmpty() {
            return n==0;
        }
        public ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<>();

            for(int i=0; i<buckets.length; i++) {
                LinkedList<Node> ll = buckets[i];
                for(int j=0; j<ll.size(); j++) {
                    Node node = ll.get(j);
                    keys.add(node.key);
                }
            }
            return keys;
        }
    }
    public static void main(String args[]) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("India", 190);
        map.put("China", 200);
        map.put("US", 50);


        ArrayList<String> keys = map.keySet();
        for(int i=0; i<keys.size(); i++) {
            System.out.println(keys.get(i) +" " + map.get(keys.get(i)));
        }

        map.remove("US");
        System.out.println(map.get( "US"));
    }
}
