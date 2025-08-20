import java.io.*;
import java.util.*;

public class Main {


    static int p, w;
    static int c, v;
    static Edge[] edges;

    static class Edge implements Comparable<Edge> {
        int start, end;
        int width;
        Edge(int start, int end, int width){
            this.start = start;
            this.end = end;
            this.width = width;
        }

        public int compareTo(Edge o){
            return o.width - this.width;
        }
    }

    // --------------- union-find -----------------
    static int[] parents;

    public static void make(){
        parents = new int[p];
        for(int i = 0; i < p; i++){
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
    // --------------- union-find -----------------



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        make();


        edges = new Edge[w];
        for(int i = 0; i < w; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(start, end, width);
        }

        Arrays.sort(edges);

        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < w; i++) {
            Edge cur = edges[i];

            union(cur.start, cur.end);

            ans = Math.min(ans, cur.width);

            if (find(c) == find(v)) break;

        }

        System.out.println(ans);
    }
}
