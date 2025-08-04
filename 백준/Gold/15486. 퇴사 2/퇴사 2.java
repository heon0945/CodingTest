import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static long[] dp;

    static Consult[] schedule;
    static class Consult {
        int time;
        int cost;
        Consult(int time, int cost){
            this.time = time;
            this.cost = cost;
        }
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        schedule = new Consult[n+1];
        dp = new long[n+1];

        StringTokenizer st;
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            schedule[i] = new Consult(time, cost);
        }

        for(int i = 1; i <= n; i++){
            dp[i] = Math.max(dp[i], dp[i-1]);

            int end = i + schedule[i].time - 1;
            if(end >= n + 1) continue;

            dp[end] = Math.max(dp[end], dp[i-1] + schedule[i].cost);
        }

        System.out.println(dp[n]);

    }
}
