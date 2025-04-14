import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }
    }

    public static int N, M;
    public static long totalSpent;

    public static int parents[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        parents = new int[N];

        for(int i = 0; i < N; i++) parents[i] = -1;

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for(int i = 0; i < M; i++){
            int start = sc.nextInt()-1;
            int end = sc.nextInt()-1;
            int weight = sc.nextInt();
            pq.add(new Edge(start, end, weight));
            totalSpent += weight;
        }

        int cnt = 0; //edge 개수
        long minSpent = 0;
        while(!pq.isEmpty()){
            if(cnt >= N-1){
                break;
            }

            Edge cur = pq.poll();

            if(!union(cur.start, cur.end)) continue;

            cnt++;
            minSpent += cur.weight;
        }

        if(cnt < N-1){
            System.out.println(-1);
            return;
        }

        System.out.println(totalSpent-minSpent);
    }

    public static int find(int a){
        if(parents[a] < 0) return a;


        return parents[a] = find(parents[a]);

    }

    public static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;

        parents[rootA] = rootB;

        return true;
    }

}