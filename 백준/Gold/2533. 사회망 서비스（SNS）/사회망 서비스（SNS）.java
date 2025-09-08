import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static boolean[] visited;
    static List<Integer>[] graph;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        visited = new boolean[n];
        graph = new ArrayList[n];

        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            graph[a].add(b);
            graph[b].add(a);
        }

        dp = new int[n][2];
        //루트 1로 설정
        traversal(0);

        System.out.println(Math.min(dp[0][0], dp[0][1]));

    }

    public static void traversal(int cur){

        visited[cur] = true;
        dp[cur][0] = 0;
        dp[cur][1] = 1;

        List<Integer> list = graph[cur];
        for(int next : list){

            if(visited[next]) continue;

            traversal(next);
            dp[cur][0] += dp[next][1];
            dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
        }

    }
}
