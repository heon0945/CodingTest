import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1000000000;
    static int n, m;
    static boolean[] broken; //부서진 돌
    static int[][] dp; // i, k : i번째 돌에 k번의 점프로 도달한 적이 있는 경우 true (이전 점프 길이 기억)

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        broken = new boolean[n+1];

        for(int i = 0; i < m; i++){
            broken[Integer.parseInt(br.readLine())] = true;
        }

        int maxJump = (int)Math.sqrt(2 * n) + 2;
        dp = new int[n+1][maxJump + 1];

        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= maxJump; j++){
                dp[i][j] = INF;
            }
        }

        if(!broken[2])
            dp[2][1] = 1; //시작 지점

        for(int i = 2; i <= n; i++){
            for(int j = 0; j <= maxJump; j++){
                if(dp[i][j] == INF) continue;

                for(int d = -1; d <= 1; d++){
                    int next = i + j + d;
                    if(next <= i) continue; //앞으로 전진 못하는 경우
                    if(next > n) continue; //목적지를 벗어나는 경우

                    if(broken[next]) continue; //해당 돌이 부서진 경우

                    dp[next][j + d] = Math.min(dp[next][j + d], dp[i][j] + 1);
                }
            }
        }

        int ans = INF;
        for(int i = 1; i <= maxJump; i++){
            ans = Math.min(ans, dp[n][i]);
        }

        if(ans == INF) System.out.println(-1);
        else System.out.println(ans);
    }

}
