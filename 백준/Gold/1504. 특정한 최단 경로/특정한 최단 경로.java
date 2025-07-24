import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 2000000000;
    static int n, e;
    static int v1, v2;
    static List<Node>[] graph;

    static class Node implements Comparable<Node>{
        int dest, cost;
        Node(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int[] distFrom1 = dijkstra(1);
        int[] distFromv1 = dijkstra(v1);
        int[] distFromv2 = dijkstra(v2);

        long path1 = (long)distFrom1[v1] + distFromv1[v2] + distFromv2[n];
        long path2 = (long)distFrom1[v2] + distFromv2[v1] + distFromv1[n];

        long result = Math.min(path1, path2);

        System.out.println(result >= INF ? -1 : result);
    }

    static int[] dijkstra(int start){
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){

            Node cur = pq.poll();

            if(dist[cur.dest] < cur.cost) continue;

            for(Node next : graph[cur.dest]){
                int cost = dist[cur.dest] + next.cost;

                if(dist[next.dest] > cost){
                    dist[next.dest] = cost;
                    pq.add(new Node(next.dest, cost));
                }
            }
        }

        return dist;



    }
}
