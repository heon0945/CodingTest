import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.fill(dp, 1);
        int maxLen = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j] && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
        }

        System.out.println(n - maxLen);
    }
}