import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int start, end;
    static int[] dist;
    static List<Node>[] adj;
    static final int INF = 1000000000;
    static class Node implements Comparable<Node>{
        int idx, cost;
        Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        adj = new List[n];
        dist = new int[n];

        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
            dist[i] = INF;
        }

        StringTokenizer st;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            adj[start].add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){

            Node cur = pq.poll();

            if(dist[cur.idx] < cur.cost) continue;

            for(Node n : adj[cur.idx]){
                int newCost = n.cost + dist[cur.idx];
                if(dist[n.idx] > newCost){
                    dist[n.idx] = newCost;
                    pq.add(new Node(n.idx, newCost));
                }
            }


        }

        System.out.println(dist[end]);

    }
}
