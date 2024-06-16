import java.util.*;

//<---(O)-4α--->
class DisjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public DisjointSet(int n) {
        for(int i=0; i<=n; i++) {
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    public int findUpar(int node) {
        if(parent.get(node) == node) {
            return node;
        }
        int uPar = findUpar(parent.get(node));
        parent.set(node, uPar);

        return parent.get(node);
    }

    public void unionByRank(int u, int v) {
        int ulp_u = findUpar(u);
        int ulp_v = findUpar(v);

        if(ulp_u == ulp_v)
            return;

        if(rank.get(ulp_u) > rank.get(ulp_v)) {
            parent.set(ulp_v, ulp_u);
        }
        else if(rank.get(ulp_v) > rank.get(ulp_u)) {
            parent.set(ulp_u, ulp_v);
        }
        else {
            parent.set(ulp_u, ulp_v);
            int r = rank.get(ulp_v);
            rank.set(ulp_v, r+1);
        }
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUpar(u);
        int ulp_v = findUpar(v);

        if(ulp_u == ulp_v) return;
        if(size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v));
        }
        else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}
class Main {
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(7);
        ds.unionBySize(1, 2);
        ds.unionBySize(2, 3);
        ds.unionBySize(4, 5);
        ds.unionBySize(6, 7);
        ds.unionBySize(5, 6);

        if(ds.findUpar(3) == ds.findUpar(7)) {
            System.out.println("Same");
        }
        else
            System.out.println("Not Same");

        ds.unionBySize(3, 7);
        if(ds.findUpar(3) == ds.findUpar(7)) {
            System.out.println("Same");
        }
        else
            System.out.println("Not Same");
    }
}
