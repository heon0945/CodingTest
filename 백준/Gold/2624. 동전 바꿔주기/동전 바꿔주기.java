import java.io.*;
import java.util.*;

public class Main {

    static int t;
    static int k;
    static Coin[] coins;
    static int[] dp;

    static class Coin implements Comparable<Coin>{
        int p, n;
        Coin(int p, int n){
            this.p = p;
            this.n = n;
        }
        public int compareTo(Coin o){
            return this.p - o.p;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        coins = new Coin[k];

        StringTokenizer st;
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            coins[i] = new Coin(p, n);
        }


        dp = new int[t+1];
        dp[0] = 1;

        int max = 0;
        for(Coin coin : coins){

            for(int i = t; i >= 0; i--){
                for(int j = 1; j <= coin.n; j++){
                    if(i - coin.p * j >= 0)
                    dp[i] = dp[i] + dp[i - coin.p * j];
                }
            }
        }

        System.out.println(dp[t]);
    }
}
