import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;

    static Edge[] edges;
    static int[] parents;


    public static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;
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
        parents = new int[n+1];

        for(int i = 0; i <= n; i++){
            parents[i] = -1;
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

        edges = new Edge[m+1];

        for(int i = 0; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(weight == 0) weight = 1; //오르막길
            else weight = 0; //내리막길

            edges[i] = new Edge(start, end, weight);
        }

        Arrays.sort(edges);

        int max = maximum();
        int min = minimum();

        //System.out.println("min : " + min);
        //System.out.println("max : " + max);

        System.out.println(max*max - min*min);

    }

    static int minimum(){
        make();

        int cnt = 0;
        int cost = 0;

        for(int i = 0; i <= m; i++){

            if(cnt >= n) break;

            Edge cur = edges[i];

            if(union(cur.start, cur.end)){
                cnt++;

                if(cur.weight == 1){
                    cost++;
                }
            }

        }

        return cost;
    }

    static int maximum(){
        make();

        int cnt = 0;
        int cost = 0;

        for(int i = m; i >= 0; i--){

            if(cnt >= n) break;

            Edge cur = edges[i];

            if(union(cur.start, cur.end)){
                cnt++;

                if(cur.weight == 1){
                    cost++;
                }
            }


        }

        return cost;
    }
}
