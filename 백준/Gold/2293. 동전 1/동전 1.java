import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int dp[];
    static int coin[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[k+1];
        coin = new int[n];

        for(int i = 0; i < n; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;
        for(int amount : coin){
            for(int i = 0; i <= k; i++){

                if(i - amount < 0) continue;

                dp[i] = dp[i-amount] + dp[i];


            }
        }

        System.out.println(dp[k]);




    }
}
