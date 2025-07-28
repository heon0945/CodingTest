import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int first, last;
    static int prev[];
    static List<Node>[] adj;

    static long minDist[];

    public static class Node implements Comparable<Node>{
        int number;
        long weight;
        public Node(int number, long weight){
            this.number = number;
            this.weight = weight;
        }
        public int compareTo(Node o){
            return Long.compare(this.weight, o.weight);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        adj = new List[n];
        prev = new int[n];

        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, w));
        }

        st = new StringTokenizer(br.readLine());
        first = Integer.parseInt(st.nextToken()) - 1;
        last = Integer.parseInt(st.nextToken()) - 1;

        minDist = new long[n];

        for(int i = 0; i < n; i++){
            minDist[i] = 100000000000L;
        }

        dijkstra();


        StringBuilder sb = new StringBuilder();
        sb.append(minDist[last]).append('\n');

        List<Integer> list = new ArrayList<>();
        list.add(last);
        int cur = last;
        while(cur != first){
            list.add(prev[cur]);
            cur = prev[cur];
        }
        
        sb.append(list.size()).append('\n');

        for(int i = list.size()-1; i >= 0; i--){
            sb.append(list.get(i)+1).append(" ");
        }

        System.out.println(sb);


    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(first, 0));
        prev[first] = -1;
        minDist[first] = 0;

        while(!pq.isEmpty()){

            Node cur = pq.poll();

            if(cur.weight > minDist[cur.number]) continue;

            for(Node next : adj[cur.number]){
                long newDist = minDist[cur.number] + next.weight;
                if(newDist < minDist[next.number]){
                    pq.add(new Node(next.number, newDist));
                    minDist[next.number] = newDist;

                    prev[next.number] = cur.number;
                }
            }


        }


    }

}
