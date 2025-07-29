import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;
    static List<Node>[] path;
    static int[] dest;
    static long[] minDist;

    static class Node implements Comparable<Node>{
        int to;
        long cost;
        Node(int to, long cost){
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return Long.compare(this.cost, o.cost);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        path = new List[n];
        for(int i = 0; i < n; i++){
            path[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st  = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            long weight = Long.parseLong((st.nextToken()));

            //역방향 다익스트라
            path[end].add(new Node(start, weight));
        }

        dest = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            dest[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        dijkstra();

        long maxDist = 0;
        int maxIdx = -1;
        for(int i = 0; i < n; i++){
            if(maxDist < minDist[i]){
                maxDist = minDist[i];
                maxIdx = i;
            }
        }

        System.out.println(maxIdx + 1);
        System.out.println(maxDist);


    }

    public static void dijkstra(){

        minDist = new long[n];
        for(int i = 0; i < n; i++){
            minDist[i] = 50000000000L;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < k; i++){
            int cur = dest[i];
            minDist[cur] = 0;
            pq.add(new Node(cur, 0));
        }

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.cost > minDist[cur.to]) continue;

            for(Node next : path[cur.to]){
                long newDist = next.cost + minDist[cur.to];
                if(minDist[next.to] > newDist){
                    pq.add(new Node(next.to, newDist));
                    minDist[next.to] = newDist;
                }
            }

        }

    }
}
