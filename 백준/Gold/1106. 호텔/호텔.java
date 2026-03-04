import java.io.*;
import java.util.*;

public class Main {
    static int c, n;
    static int[][] city; //비용, 고객수
    static int[] dp;
    static int ub;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        city = new int[n][2];
        ub = c + 101;
        dp = new int[ub+1];
        Arrays.fill(dp, 100000000);

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            int cost = Integer.parseInt(st.nextToken());
            int customers = Integer.parseInt(st.nextToken());

            city[i][0] = cost;
            city[i][1] = customers;
        }

        dp[0] = 0;

        for(int i = 1; i <= ub; i++){
            for(int j = 0; j < n; j++){

                if(i - city[j][1] < 0) continue;

                dp[i] = Math.min(dp[i], dp[i - city[j][1]] + city[j][0]);
            }
        }

        int answer = 100000000;

        for(int i = c; i <= ub; i++){
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
    }
}