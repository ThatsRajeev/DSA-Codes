import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    public static class Edge {
        int src;
        int dst;
        int wgt;

        public Edge(int src, int dst, int wgt) {
        this.src = src;
        this.dst = dst;
        this.wgt = wgt;
        }
    }
    public static void createGraph(ArrayList<Edge> graph[]) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));

        graph[2].add(new Edge(2, 4, 3));

        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    public static void breadFirstSearch(ArrayList<Edge> graph[], int V) {
        Queue<Integer> q = new LinkedList<>();
        boolean visited[] = new boolean[V];
        q.add(0);

        for(int i=0; i<visited.length; i++) {
            visited[i] = false;
        }

        while(!q.isEmpty()) {
            int curr = q.remove();

            if(!visited[curr]) {
                System.out.println(curr);
                visited[curr] = true;

                for(int i=0; i<graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
                    q.add(e.dst);
                }
            }
        }
    }

    public static void depthFirstSearch(ArrayList<Edge> graph[], boolean visited[], int curr) {
        if(visited[curr]) {
            return;
        }

        System.out.print(curr + "");
        visited[curr] = true;

        for (int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            depthFirstSearch(graph, visited, e.dst);
        }
    }

    public static boolean isCycleDirected(ArrayList<Edge> graph[], boolean vis[], int curr, boolean rec[]) {

        vis[curr] = true;
        rec[curr] = true;

            for(int i=0; i<graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                if(rec[e.dst]) {
                    return true;
                }
                else if(!vis[e.dst]) {
                    if (isCycleDirected(graph, vis, e.dst, rec)) {
                        return true;
                    }
                }
            }
            rec[curr] = false;
            return false;
        }

    public static void topSort(ArrayList<Edge> graph[], boolean vis[], int curr, Stack<Integer> s) {

        vis[curr] = true;

        for(int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);

            if(!vis[e.dst]) {
                topSort(graph, vis, e.dst, s);
            }
            s.push(curr);
        }
    }

    public static boolean isCycleUndirected(ArrayList<Edge> graph[], boolean vis[], int curr, int par) {
        vis[curr] = true;

        for(int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);

            if(vis[e.dst] && e.dst != par) {
                return true;
            }
            else if(!vis[e.dst]) {
                if(isCycleUndirected(graph, vis, e.dst, curr)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static class Pair implements Comparable<Pair> {
        int node;
        int dist;

        public Pair(int n, int d) {
            this.node = n;
            this.dist = d;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.dist - p2.dist;
        }
    }

    //O(E + ElogV)
    public static void dijkstra(ArrayList<Edge> graph[], int src, int V) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean visited[] = new boolean[V];
        int dist[] = new int[V];

        for(int i=0; i<V; i++) {
            if(i!=src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        pq.add(new Pair(src, 0));

        while(!pq.isEmpty()) {
            Pair curr = pq.remove();

            if(!visited[curr.node]) {
                visited[curr.node] = true;

                for(int i=0; i<graph[curr.node].size(); i++) {
                    Edge e = graph[curr.node].get(i);
                    int u = e.src;
                    int v = e.dst;

                    if(dist[v] > dist[u] + e.wgt) {
                        dist[v] = dist[u] + e.wgt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }
        for(int i=0; i<V; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    public static void bellmanford(ArrayList<Edge> graph[], int src, int V) {
        int dist[] = new int[V];
        for(int i=0; i<V; i++) {
            if(i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        for (int i=0; i<V-1; i++) { //O(V)
            //O(E)
            for(int j=0; j<V; j++) {
                for(int k=0; k<graph[j].size(); k++) {
                    Edge e = graph[j].get(k);
                    int u = e.src;
                    int v = e.dst;

                    if(dist[u] != Integer.MAX_VALUE && dist[u] + e.wgt < dist[v]) {
                        dist[v] = dist[u] + e.wgt;
                    }
                }
            }
        }
        //-ve cycle
        for(int j=0; j<V; j++) {
            for(int k=0; k<graph[j].size(); k++) {
                Edge e = graph[j].get(k);
                int u = e.src;
                int v = e.dst;

                if(dist[u] != Integer.MAX_VALUE && dist[u] + e.wgt < dist[v]) {
                    dist[v] = dist[u] + e.wgt;
                    System.out.println("Negative cycle exists");
                }
            }
        }
        for(int i=0; i<dist.length; i++) {
            System.out.print(dist[i] + " " );
        }
    }

    public static void prims(ArrayList<Edge> graph[], int V) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(); //non-mst
        boolean visited[] = new boolean[V]; //mst
        int mstCost = 0;

        pq.add(new Pair(0, 0));

        while(!pq.isEmpty()) {
            Pair curr = pq.remove();
            if(!visited[curr.node]) {
                visited[curr.node] = true;
                mstCost += curr.dist;

                for(int i=0; i<graph[curr.node].size(); i++) {
                    Edge e = graph[curr.node].get(i);
                    if(!visited[e.dst]) {
                        pq.add(new Pair(e.dst, e.wgt));
                    }
                }
            }
        }
        System.out.println("min cost of mst = "+ mstCost);
    }

    public static void kosaraju (ArrayList<Edge> graph[], int V) {
        //Step 1
        Stack<Integer> s = new Stack<>();
        boolean vis[] = new boolean[V];
        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                topSort(graph, vis, i, s);
            }
        }

        //Step2
        @SuppressWarnings("unchecked")
        ArrayList<Edge> transpose[] = new ArrayList[V];
        for(int i=0; i<graph.length; i++) {
            vis[i] = false;
            transpose[i] = new ArrayList<>();
        }

        for(int i=0; i<graph.length; i++) {
            for(int j=0; j<graph[i].size(); i++) {
                Edge e = graph[i].get(j);
                transpose[e.dst].add(new Edge(e.dst, e.src, e.wgt));
            }
        }

        //Step3
        while(!s.isEmpty()) {
            int curr = s.pop();
            if(!vis[curr]) {
                depthFirstSearch(transpose, vis, curr);
                System.out.println();
            }
        }
    }
    public static void dfsModified(ArrayList<Edge> graph[], int curr,
        boolean vis[], int dt[], int low[], int time, int par) {

            vis[curr] = true;
            dt[curr] = low[curr] = ++time;

            for(int i=0; i<graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                if(e.dst == par) {
                    continue;
                } else if(!vis[e.dst]) {
                    dfsModified(graph, e.dst, vis, dt, low, time, curr);
                    low[curr] = Math.min(low[curr], low[e.dst]);
                    if(dt[curr] <low[e.dst]) {
                        System.out.println("bridge is : " + curr + "---" + e.dst);
                    } else {
                        low[curr] = Math.min(low[curr], dt[e.dst]);
                    }
                }
            }
        }

    public static void getBridge(ArrayList<Edge> graph[], int V) {
        int dt[] = new int[V];
        int low[] = new int[V];
        int time = 0;
        boolean vis[] = new boolean[V];

        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                dfsModified(graph, i, vis, dt, low, time, -1);
            }
        }
    }
    //(V + E)
    public static void dfsModified2(ArrayList<Edge> graph[], int curr, int par,
        int dt[], int low[], boolean vis[], int time, boolean ap[]) {

            vis[curr] = true;
            dt[curr] = low[curr] = ++time;
            int children = 0;

            for(int i=0; i<graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                int neigh = e.dst;

                if(par == neigh) {
                    continue;
                } else if(vis[neigh]) {
                    low[curr] = Math.min(low[curr], dt[neigh]);
                } else {
                    dfsModified2(graph, neigh, curr, dt, low, vis, time, ap);
                    low[curr] = Math.min(low[curr], low[neigh]);
                    if(dt[curr] <= low[neigh] && par != -1) {
                        ap[curr] = true;
                    }
                    children++;
                }
            }
            if(par == -1 && children > 1) {
                ap[curr] = true;
            }
        }

    public static void getAP(ArrayList<Edge> graph[], int V) {
        int dt[] = new int[V];
        int low[] = new int[V];
        int time = 0;
        boolean vis[] = new boolean[V];
        boolean ap[] = new boolean[V];

        for(int i=0; i<V; i++) {
            dfsModified2(graph, i, -1, dt, low, vis, time, ap);
        }

        for(int i=0; i<V; i++) {
            if(ap[i]) {
                System.out.println("AP: "+ i);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String args[]) {
        int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        // // for graphs broken into components
        // boolean vis[] = new boolean[V];
        // for(int i=0; i<V; i++) {
        //     if(vis[i] == false) {
        //         breadFirstSearch(graph, V);
        //     }
        // }

        // breadFirstSearch(graph, V);
        // depthFirstSearch(graph, visited, 0);

        // System.out.println(isCycleDirected(graph, new boolean[V], 0, new boolean[V]));

        // boolean vis[] = new boolean[V];
        // Stack<Integer> s = new Stack<>();
        // for(int i=0; i<V; i++) {
        //     if(!vis[i]) {
        //         topSort(graph, vis, i, s);
        //     }
        // }
        // while(!s.isEmpty()) {
        //     System.out.print(s.pop() + " ");
        // }

        // System.out.println(isCycleUndirected(graph, new boolean[V], 0, -1));;

        getAP(graph, V);
    }
}
