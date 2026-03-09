import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int mod = 1000000000;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new long[n+1][10][1024]; //100자릿수의 길이, 0~9까지 가능한 마지막 수, 0~9까지 포함되었는지 나타내는 이진수

        //1~9부터 각각 한 자리수를 이루는 경우의 수로 초기화
        for(int i = 1; i <= 9; i++){
            dp[1][i][1<<i] = 1;
        }

        //두번째 자리수부터 dp를 이용하여 만들 수 있는 숫자 업데이트
        for(int i = 2; i <= n; i++){
            for(int j = 0; j <= 9; j++){
                for(int k = 1; k < 1024; k++){
                    
                    //k비트(포함한 수)에서 현재 마지막 수로 넣으려고 하는 j 포함
                    int nextBit = k | (1 << j);

                    //계단수로 만들기 위한 조건 (마지막으로 추가하고자 하는 수에 따라 계단수 생성)
                    if(j == 0) dp[i][j][nextBit] += dp[i-1][1][k];
                    else if(j == 9) dp[i][j][nextBit] += dp[i-1][8][k];
                    //1~8을 추가할 때는 1 작은 수, 1 큰 수 두 경우 전부 가능
                    else dp[i][j][nextBit] += (dp[i-1][j-1][k] + dp[i-1][j+1][k]) % mod;

                    //오버플로우 방지하기 위해 mod로 나누어서 저장
                    dp[i][j][nextBit] %= mod;
                }
            }
        }

        long answer = 0;
        //n자리 수 중에 모든 수를 포함하는 경우 (마지막 수가 0~9인 경우 모두 순회) 
        for(int i = 0; i <= 9; i++){
            answer += dp[n][i][1023];
            answer %= mod;
        }

        System.out.println(answer);
    }
}
