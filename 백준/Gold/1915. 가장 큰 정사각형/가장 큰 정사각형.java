import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int map[][];

    static int dp[][];

    static int ans;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        dp = new int[n+1][m+1];

        for(int i = 1; i <= n; i++){
            String str = br.readLine();
            for(int j = 1; j <= m; j++){
                map[i][j] = str.charAt(j-1) - '0';
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){

                if(map[i][j] != 1) continue;

                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]);
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                dp[i][j] = dp[i][j] + 1;

                ans = Math.max(ans, dp[i][j]);
            }
        }

        System.out.println(ans * ans);

    }

}
