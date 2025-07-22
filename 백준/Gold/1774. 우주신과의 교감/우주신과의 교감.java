import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static List<Edge> edges;
    static int[] parents;
    static Pair[] pos;

    static boolean[] visited;
    static int[] selected;

    public static class Pair{
        int x, y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static class Edge implements Comparable<Edge>{
        int start, end;
        double weight;

        public Edge(int start, int end, double weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int compareTo(Edge o){
            return Double.compare(this.weight, o.weight);
        }
    }

    public static int find(int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;

        if(parents[rootA] < parents[rootB]){
            parents[rootA] += parents[rootB];
            parents[rootB] = rootA;
        }
        else{
            parents[rootB] += parents[rootA];
            parents[rootA] = rootB;
        }
        return true;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        pos = new Pair[n];
        parents = new int[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pos[i] = new Pair(x, y);
        }

        //make
        for(int i = 0; i < n; i++){
            parents[i] = -1;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            union(start, end);
        }

        //edge 만들기
        visited = new boolean[n];
        selected = new int[2];
        combi(0, 0);

        //MST
        Collections.sort(edges);

        int cnt = 0;
        double cost = 0;

        for(Edge edge : edges){

            if(cnt >= n-1) break;


            int start = edge.start;
            int end = edge.end;

            if(union(start, end)){
                cnt++;
                cost += edge.weight;
            }

        }

        System.out.printf("%.2f", cost);

    }

    public static void combi(int cnt, int cur){
        if(cnt == 2){
            int start = selected[0];
            int end = selected[1];

            double x = Math.abs(pos[start].x - pos[end].x);
            double y = Math.abs(pos[start].y - pos[end].y);

            double dist = Math.sqrt(x * x + y * y);

            edges.add(new Edge(start, end, dist));

            return;
        }

        for(int i = cur; i < n; i++){
            if(visited[i]) continue;

            visited[i] = true;
            selected[cur] = i;
            combi(cnt + 1, cur + 1);
            visited[i] = false;
        }
    }
}
