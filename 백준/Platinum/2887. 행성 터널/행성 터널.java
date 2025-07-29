import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] parents;
    static Planet[] planets;
    static ArrayList<Edge> edges;

    static class Planet{
        int idx;
        long x, y, z;
        Planet(int idx, long x, long y, long z){
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge implements Comparable<Edge>{
        int from, to;
        long weight;

        Edge(int from, int to, long weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge o){
            return Long.compare(this.weight, o.weight);
        }
    }

    static void make(){
        parents = new int[n];
        for(int i = 0; i < n; i++){
            parents[i] = -1;
        }
    }

    static int find(int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
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

        n = Integer.parseInt(br.readLine());

        planets = new Planet[n];
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long z = Long.parseLong(st.nextToken());
            planets[i] = new Planet(i, x, y, z);
        }

        //edge 만들기
        edges = new ArrayList<>();

        //x축
        Arrays.sort(planets, (a, b) -> Long.compare(a.x, b.x));
        makingEdges();
        //y축
        Arrays.sort(planets, (a, b) -> Long.compare(a.y, b.y));
        makingEdges();
        //z축
        Arrays.sort(planets, (a, b) -> Long.compare(a.z, b.z));
        makingEdges();

        //mst (kruskal)
        make();

        Collections.sort(edges);

        int cnt = 0;
        long cost = 0;
        for(int i = 0; i < edges.size(); i++){

            if(cnt == n-1) break;

            Edge cur = edges.get(i);

            if(union(cur.from, cur.to)){
                cnt++;
                cost += cur.weight;
            }
        }

        System.out.println(cost);

    }

    static void makingEdges(){

        for(int i = 0; i < n-1; i++){
            Planet cur = planets[i];
            Planet next = planets[i+1];

            long weight = Math.min(Math.abs(cur.x - next.x), Math.abs(cur.y - next.y));
            weight = Math.min(weight, Math.abs(cur.z - next.z));
            edges.add(new Edge(cur.idx , next.idx, weight));
        }

    }

}
