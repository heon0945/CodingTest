import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[] wn;
    static int[] wk;
    static boolean[][] ans;
    static int MAX_WEIGHT = 40001;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        wn = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            wn[i] = Integer.parseInt(st.nextToken());
        }

        k = Integer.parseInt(br.readLine());
        wk = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            wk[i] = Integer.parseInt(st.nextToken());
        }

        ans = new boolean[n + 1][MAX_WEIGHT];


        dfs(0, 0);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < k; i++){
            if(ans[n][wk[i]]) sb.append("Y");
            else sb.append("N");
            sb.append(" ");
        }
        System.out.println(sb);

    }

    static void dfs(int cur, int weight){
        if(ans[cur][weight]) return;

        ans[cur][weight] = true;

        if(cur == n){
            return;
        }

        dfs(cur + 1, weight);
        dfs(cur + 1, Math.abs(weight - wn[cur]));
        dfs(cur + 1, weight + wn[cur]);
    }
}
