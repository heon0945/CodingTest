import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 1000000003;
    static int n, k;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        if(k > n / 2){
            System.out.println(0);
            return;
        }

        // n개의 색상환에서 k개의 색을 고르는 경우의 수
        dp = new int[n+1][k+1];

        for(int i = 0; i <= n; i++){
            dp[i][0] = 1; // 아무것도 안 고르기
            if(i >= 1 && k >= 1) dp[i][1] = i; // 하나만 고르기
        }

        // 점화식: f(i, j) = f(i-1, j) + f(i-2, j-1)
        for(int i = 2; i <= n; i++){
            for(int j = 2; j <= k; j++){
                dp[i][j] = (int)((dp[i-1][j] + (long)dp[i-2][j-1]) % MOD);
            }
        }

        // 원 → 선 두 경우 합치기
        int ans = dp[n - 1][k]; // 1번을 선택하지 않는 경우
        ans = (ans + dp[n - 3][k - 1]) % MOD; // 1번을 선택하는 경우

        System.out.println(ans);

    }
}
