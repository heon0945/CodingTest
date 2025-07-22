import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;

    static Edge[] edges;
    static int[] parents;
    static int[] stations;

    static public class Edge implements Comparable<Edge>{
        int start, end, weight;

        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int compareTo(Edge o){
            return this.weight - o.weight;
        }
    }

    public static void make(){
        parents = new int[n];

        for(int i = 0; i < n; i++){
            parents[i] = -1;
        }

        //발전소 도시는 같은 그룹으로 묶기
        parents[stations[0]] -= (k-1);
        for(int i = 1; i < k; i++){
            parents[stations[i]] = stations[0];
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

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        edges = new Edge[m];
        stations = new int[k];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            stations[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) -1 ;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(start, end, weight);
        }

        make();

        Arrays.sort(edges);

        int cnt = 0;
        int cost = 0;
        for(Edge edge : edges){

            if(cnt >= n-1) break;

            if(union(edge.start, edge.end)){
                cnt++;
                cost += edge.weight;
            }

        }

        System.out.println(cost);
    }



}
