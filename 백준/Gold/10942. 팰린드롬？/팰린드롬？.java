import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] arr;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n+1];

        //팰린드롬 dp
        //dp[i][j] : i ~ j까지 팰린드롬을 이루는지 여부
        dp = new boolean[n+1][n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = true; //한자리 수는 무조건 팰린드롬
        }

        //두 자리 수의 팰린드롬 (두 자리 수가 같으면 팰린드롬)
        for(int i = 1; i <= n-1; i++){
            if(arr[i] == arr[i+1]) dp[i][i+1] = true;
        }

        //세 자리 이상의 팰린드롬 (3~n자리 수의 팰린드롬 판단)
        for(int i = 2; i < n; i++){
            for(int j = 1; j <= n - i; j++){
                if(arr[j] == arr[j+i] && dp[j+1][j+i-1]) dp[j][j+i] = true;
            }
        }

        m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(dp[start][end] ? 1 : 0).append('\n');
        }

        System.out.print(sb);
    }
}