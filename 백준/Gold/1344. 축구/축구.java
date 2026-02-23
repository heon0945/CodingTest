import java.io.*;
import java.util.*;

public class Main {

    static int setNum = 18;

    //i번 세트에서 a팀이 j점, b팀이 k점을 획득할 확률
    static double[][][] dp = new double[setNum + 1][setNum + 1][setNum + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //한 세트에 골을 넣을 확률
        double a = Double.parseDouble(br.readLine()) / 100;
        double b = Double.parseDouble(br.readLine()) / 100;

        dp[0][0][0] = 1.0;

        for(int i = 1; i <= setNum; i++){
            for(int j = 0; j <= i; j++){
                for(int k = 0; k <= i; k++){
                    //a만 골 넣은 경우
                    if(j > 0) dp[i][j][k] += dp[i-1][j-1][k] * a * (1-b);
                    //b만 골 넣은 경우
                    if(k > 0) dp[i][j][k] += dp[i-1][j][k-1] * (1-a) * b;
                    //a랑 b 골 넣은 경우
                    if(j > 0 && k > 0) dp[i][j][k] += dp[i-1][j-1][k-1] * a * b;
                    //a랑 b 둘 다 골 못 넣은 경우
                    dp[i][j][k] += dp[i-1][j][k] * (1-a) * (1-b);
                }
            }
        }

        double res = 0;
        for(int i = 0; i <= setNum; i++){
            for(int j = 0; j <= setNum; j++){
                if(isPrime(i) || isPrime(j)) res += dp[setNum][i][j];
            }
        }

        System.out.printf("%.7f", res);

    }

    private static boolean isPrime(int n){
        if(n < 2) return false;
        for(int i = 2; i * i <= n; i++) if(n % i == 0) return false;
        return true;
    }
}
