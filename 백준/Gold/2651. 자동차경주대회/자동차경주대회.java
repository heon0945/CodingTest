import java.io.*;
import java.util.*;

public class Main {

    static int len, n;
    static int dist[];
    static int time[];
    static int parents[];
    static long dp[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        len = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        dist = new int[n + 2];
        time = new int[n + 2];
        parents = new int[n + 2];
        dp = new long[n + 2];

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n + 1; i++){
            dist[i] = Integer.parseInt(st.nextToken()) + dist[i-1];
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            time[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= n + 1; i++){
            for(int j = i -1; j >= 0; j--){
                if(dist[i] - dist[j] > len) break;

                if(dp[j] + time[j] < dp[i]){
                    dp[i] = dp[j] + time[j];
                    parents[i] = j;
                }
            }
        }
        

        StringBuilder sb = new StringBuilder();
        sb.append(dp[n+1]).append('\n');

        List<Integer> path = new ArrayList<>();
        int cur = parents[n+1];

        while(cur != 0){
            path.add(cur);
            cur = parents[cur];
        }

        sb.append(path.size()).append('\n');
        for(int i = path.size()-1; i >= 0; i--){
            sb.append(path.get(i)).append(" ");
        }

        System.out.println(sb);

    }
}
