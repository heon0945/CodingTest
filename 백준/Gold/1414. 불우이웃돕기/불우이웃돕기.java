import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static List<Edge> tmp;
    static Edge[] edges;
    static int[] parents;
    static int total;

    public static class Edge implements Comparable<Edge>{
        int start, end;
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
        parents = new int[n];

        for(int i = 0; i < n; i++){
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        tmp = new ArrayList<>();

        for(int i = 0; i < n; i++){
            String str = br.readLine();

            for(int j = 0; j < n; j++){
                char c = str.charAt(j);

                if(c == '0') continue;

                int weight = 0;
                if(c < 'a')
                    weight = c - 'A' + 27;
                else
                    weight = c - 'a' + 1;
                total += weight;

                tmp.add(new Edge(i, j, weight));
                tmp.add(new Edge(j, i, weight));
            }
        }

        Collections.sort(tmp);

        edges = new Edge[tmp.size()];

        for(int i = 0; i < tmp.size(); i++){
            edges[i] = tmp.get(i);
        }

        //union find 시작
        make();

        //크루스칼 알고리즘 (MST)
        int cnt = 0; // 현재 선택된 간선 수
        int cost = 0; // 최소 비용

        for(int i = 0; i < edges.length; i++){
            if(cnt >= n-1) break;

            Edge cur = edges[i];

            //만약 아직 선택되지 않은 간선이라면 포함
            if(union(cur.start, cur.end)){
                cnt++;
                cost += cur.weight;
            }
        }


        if(cnt == n-1)
            System.out.println(total - cost);
        else
            System.out.println(-1);

    }
}