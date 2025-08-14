import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int k;
    static boolean[] visited;
    static long[][] dist;
    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dist = new long[n][n];
        visited = new boolean[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                dist[i][j] = Long.parseLong(st.nextToken());
            }
        }

        for(int m = 0; m < n; m++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(i == j) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][m] + dist[m][j]);
                }
            }
        }

        visited[k] = true;
        ans = Long.MAX_VALUE;

        dfs(0, k, 0);

        System.out.println(ans);
    }

    static void dfs(int order, int cur, long sum){

        if(sum > ans) return;

        if(order == n-1){
            ans = Math.min(ans, sum);
            return;
        }

        for(int i = 0; i < n; i++){
            if(visited[i]) continue;
            visited[i] = true;
            dfs(order + 1, i, sum + dist[cur][i]);
            visited[i] = false;
        }


    }
}