import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n][3];

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){

                // 세 가지 우유 모두 안 마시는 경우
                for(int t = 0; t < 3; t++){
                    if(j - 1 >= 0) // 왼쪽
                        dp[i][j][t] = Math.max(dp[i][j][t], dp[i][j-1][t]);
                    if(i - 1 >= 0) // 위쪽
                        dp[i][j][t] = Math.max(dp[i][j][t], dp[i-1][j][t]);
                }

                // 우유 마시는 경우
                int cur = map[i][j]; // 현재 위치에서 파는 우유 타입
                int prev = ( map[i][j] + 2 ) % 3; // 이전 마셨어야 할 우유 타입

                // 첫 우유가 아닌지 확인하고 우유 마시기
                if(dp[i][j][prev] != 0){
                    dp[i][j][cur] = Math.max(dp[i][j][cur], dp[i][j][prev] + 1);
                }

                // 첫 우유면서 딸기 우유라면 마시기
                if(cur == 0 && dp[i][j][0] == 0) dp[i][j][0] = 1;
            }
        }

        int ans = Math.max(dp[n-1][n-1][0], dp[n-1][n-1][1]);
        ans = Math.max(ans, dp[n-1][n-1][2]);

        System.out.println(ans);
    }
}
