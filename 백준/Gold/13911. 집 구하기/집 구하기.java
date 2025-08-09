import java.io.*;
import java.util.*;


public class Main {

    static int v, e;
    static List<Vertex>[] adj;
    static int x, y;
    static Set<Integer> macs;
    static Set<Integer> starbucks;

    static long macDist[];
    static long starDist[];

    static class Vertex implements Comparable<Vertex> {
        int idx;
        long weight;

        Vertex(int idx, long weight){
            this.idx = idx;
            this.weight = weight;
        }

        public int compareTo(Vertex o){
            return Long.compare(this.weight, o.weight);
        }
    }

    static class Node implements Comparable<Node>{
        int idx;
        long macDist;
        long starDist;
        Node(int idx, long macDist, long starDist){
            this.idx = idx;
            this.macDist = macDist;
            this.starDist = starDist;
        }
        public int compareTo(Node o){
            return Long.compare(this.macDist + this.starDist, o.macDist + o.starDist);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        // 정점과 간선 정보 입력
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        // 간선 정보로 그래프 완성하기
        adj = new List[v];
        for(int i = 0; i < v; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            long weight = Long.parseLong(st.nextToken());

            adj[first].add(new Vertex(second, weight));
            adj[second].add(new Vertex(first, weight));
        }

        // 맥도날드 관련 입력
        st = new StringTokenizer(br.readLine());
        int macCnt = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        macs = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < macCnt; i++){
            macs.add(Integer.parseInt(st.nextToken()) - 1);
        }

        // 스타벅스 관련 입력
        st = new StringTokenizer(br.readLine());
        int starCnt = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        starbucks = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < starCnt; i++){
            starbucks.add(Integer.parseInt(st.nextToken()) - 1);
        }

        dijkstraMac();
        dijkstraStar();

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < v; i++){
            if(macs.contains(i) || starbucks.contains(i)) continue;
            pq.add(new Node(i, macDist[i], starDist[i]));
        }

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.macDist <= x && cur.starDist <= y) {
                System.out.println(cur.macDist + cur.starDist);
                System.exit(0);
            }
        }

        System.out.println(-1);


    }

    public static void dijkstraMac(){

        macDist = new long[v];
        Arrays.fill(macDist, 10000000000L);
        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        for(Integer idx : macs) {
            pq.add(new Vertex(idx, 0));
            macDist[idx] = 0;
        }

        while(!pq.isEmpty()){

            Vertex cur = pq.poll();

            if(cur.weight > macDist[cur.idx]) continue;

            for(Vertex next : adj[cur.idx]){
                long newDist = cur.weight + next.weight;

                if(macDist[next.idx] > newDist){
                    pq.add(new Vertex(next.idx, newDist));
                    macDist[next.idx] = newDist;
                }
            }
        }

    }

    public static void dijkstraStar(){

        starDist = new long[v];
        Arrays.fill(starDist, 10000000000L);
        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        for(Integer idx : starbucks) {
            pq.add(new Vertex(idx, 0));
            starDist[idx] = 0;
        }

        while(!pq.isEmpty()){

            Vertex cur = pq.poll();

            if(cur.weight > starDist[cur.idx]) continue;

            for(Vertex next : adj[cur.idx]){
                long newDist = cur.weight + next.weight;

                if(starDist[next.idx] > newDist){
                    pq.add(new Vertex(next.idx, newDist));
                    starDist[next.idx] = newDist;
                }
            }
        }

    }
}