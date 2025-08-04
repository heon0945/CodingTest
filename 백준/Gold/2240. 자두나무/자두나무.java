import java.io.*;
import java.util.*;

public class Main {

    static int t, w;
    static int dp[][];
    static int pos[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        dp = new int[t+1][w+1];
        pos = new int[t+1];

        for(int i = 1; i <= t; i++){
            pos[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i <= t; i++){

            dp[i][0] = (pos[i] == 1 ? dp[i-1][0] + 1 : dp[i-1][0]);

            for(int j = 1; j <= w; j++){
                int cur = (j % 2 == 0 ? 1 : 2);

                int get = (cur == pos[i] ? 1 : 0);

                //머물러서 넘어온 경우
                int stay = dp[i-1][j] + get;
                //점프해서 온 경우
                int jump = dp[i-1][j-1] + get;

                dp[i][j] = Math.max(stay, jump);


            }
        }

        int ans = 0;
        for(int i = 0; i <= w; i++){
            ans = Math.max(ans, dp[t][i]);
        }

        System.out.println(ans);
    }
}
