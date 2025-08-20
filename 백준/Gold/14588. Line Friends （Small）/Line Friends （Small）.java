import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 1000;
    static int n;
    static int floyd[][];
    static int q;
    static List<Edge> edges;

    static class Edge{
        int start, end;
        Edge(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        floyd = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                floyd[i][j] = INF;
            }
        }

        edges = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            edges.add(new Edge(start, end));

            for(int j = 0; j < i; j++){
                Edge target = edges.get(j);
                if(target.end < start || target.start > end) continue;
                floyd[i][j] = 1;
                floyd[j][i] = 1;
            }
        }

        for(int m = 0; m < n; m++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][m] + floyd[m][j]);
                }
            }
        }

        q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(q-- > 0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            if(floyd[a][b] == INF) sb.append(-1).append('\n');
            else sb.append(floyd[a][b]).append('\n');
        }

        System.out.println(sb);
    }
}