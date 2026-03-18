import java.io.*;
import java.util.*;

public class Main {
    static long a, b;
    static long[] dp; //dp[i] : 1 ~ 2 ^ i - 1까지의 1 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        dp = new long[55]; // a, b의 최대 값은 10^16 -> 약 2^53

        dp[0] = 1; // 2^0 = 1 -> 1개

        for(int i = 1; i <= 54; i++){
            //1 ~ (2 ^ i - 1)까지 모든 수를 2진수로 바꾸었을 때 비트 값이 1인 개수 합
            dp[i] = 2 * dp[i-1] + (1L << i); // 1l << 1 = 2 ^ i
        }

        System.out.println(solve(b) - solve(a-1)); //a~b라서 a-1로 계산
    }

    //1 ~ n 까지의 모든 수를 2진수로 바꾸었을 때 비트 값이 1인 개수 합
    static long solve(long n){
        //n의 이진수 형태 마지막 비트가 1인 경우
        long ret = n & 1;

        //n을 2진수로 바꾸었을 때 총 몇 비트
        int digit = (int)(Math.log(n) / Math.log(2));

        for(int i = digit; i > 0; i--){ //i = 0인 경우는 제일 처음에 확인 완료
            if((n & (1L << i)) == 0) continue; // 해당 자리의 비트가 0임

            // i - 1 비트 자리 수 중에서 1의 개수 합
            // i 비트 자리 수 중 n이 마지막이 아닐 수 있음 -> n 이하인 수들의 1의 개수 합
            ret += (dp[i-1] + (n - (1L << i) + 1L));

            //이미 처리한 최고 비트는 제거
            n -= (1L << i);

        }

        return ret;
    }


}