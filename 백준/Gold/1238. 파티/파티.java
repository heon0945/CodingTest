import java.io.*;
import java.util.*;


public class Main {

    static int n, m, x;
    static int[] time;
    static List<Node>[] from;
    static List<Node>[] to;

    static class Node implements Comparable<Node>{
        int number, weight;
        Node(int number, int weight){
            this.number = number;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken()) - 1;

        time = new int[n];
        from = new List[n];
        to = new List[n];

        for(int i = 0; i < n; i++){
            from[i] = new ArrayList<>();
            to[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            from[b].add(new Node(a, weight));
            to[a].add(new Node(b, weight));
        }


        dijkstraFrom();
        dijkstraTo();

        int maxTime = 0;
        for(int i = 0; i < n; i++){
            maxTime = Math.max(maxTime, time[i]);
        }

        System.out.println(maxTime);
    }

    public static void dijkstraFrom(){
        int start = x;
        int minDist[] = new int[n];
        for(int i = 0; i < n; i++){
            minDist[i] = 10000000;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        minDist[start] = 0;

        while(!pq.isEmpty()){

            Node cur = pq.poll();

            if(cur.weight > minDist[cur.number]) continue;

            for(Node next : from[cur.number]){
                int newDist = next.weight + minDist[cur.number];

                if(newDist < minDist[next.number]){
                    pq.add(new Node(next.number, newDist));
                    minDist[next.number] = newDist;
                }
            }
        }

        for(int i = 0; i < n; i++){
            time[i] += minDist[i];
        }

    }

    public static void dijkstraTo(){
        int start = x;
        int minDist[] = new int[n];
        for(int i = 0; i < n; i++){
            minDist[i] = 10000000;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        minDist[start] = 0;

        while(!pq.isEmpty()){

            Node cur = pq.poll();

            if(cur.weight > minDist[cur.number]) continue;

            for(Node next : to[cur.number]){
                int newDist = next.weight + minDist[cur.number];

                if(newDist < minDist[next.number]){
                    pq.add(new Node(next.number, newDist));
                    minDist[next.number] = newDist;
                }
            }

        }

        for(int i = 0; i < n; i++){
            time[i] += minDist[i];
        }
    }
}
