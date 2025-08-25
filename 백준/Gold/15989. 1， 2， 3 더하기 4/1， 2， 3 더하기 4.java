import java.io.*;
import java.util.*;

public class Main {

    static int t;
    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());

        while(t-- > 0){
            n = Integer.parseInt(br.readLine());

            dp = new int[n+1];

            Arrays.fill(dp, 1);

            // 2 사용
            for(int i = 2; i <= n; i++){
                dp[i] = dp[i-2] + dp[i];
            }

            // 3 사용
            for(int i = 3; i <= n; i++){
                dp[i] = dp[i-3] + dp[i];
            }

            sb.append(dp[n]).append('\n');
        }

        System.out.println(sb);
    }
}
