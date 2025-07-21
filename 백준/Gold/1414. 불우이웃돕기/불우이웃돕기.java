import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int graph[][];
    static int minEdge[];
    static boolean visited[];

    static int total;

    public static class Vertex implements Comparable<Vertex>{
        int num;
        int weight;

        public Vertex(int num, int weight){
            this.num = num;
            this.weight = weight;
        }

        public int compareTo(Vertex o){
            return this.weight - o.weight;
        }

    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];

        minEdge = new int[n];
        visited = new boolean[n];

        for(int i = 0; i < n; i++){

            String str = br.readLine();

            for(int j = 0; j < n; j++){

                char c = str.charAt(j);

                int weight;
                if(c == '0') continue;
                else if( c < 'a') weight = c - 'A' + 27;
                else weight = c - 'a' + 1;

                total += weight;

                if(graph[i][j] == 0) graph[i][j] = weight;
                else graph[i][j] = Math.min(graph[i][j], weight);

                if(graph[j][i] == 0) graph[j][i] = weight;
                else graph[j][i] = Math.min(graph[j][i], weight);
            }
        }


        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        int cnt = 0;
        int cost = 0;

        pq.add(new Vertex(0, 0)); // 현재 노드, 현재까지 진행된 길이

        while(!pq.isEmpty() && cnt != n){

            Vertex cur = pq.poll();

            if(visited[cur.num]) continue;

            cost += cur.weight;
            cnt++;
            visited[cur.num] = true;

            for(int i = 0; i < n; i++){
                if(visited[i]) continue;
                if(graph[cur.num][i] <= 0) continue;
                pq.add(new Vertex(i, graph[cur.num][i]));
            }

        }

        if(cnt == n)
            System.out.println(total - cost);
        else
            System.out.println(-1);

    }

}
