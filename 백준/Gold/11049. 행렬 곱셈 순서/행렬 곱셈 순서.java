import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        matrix = new int[n][2];
        dp = new int[n][n];

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        //구간 (행렬 곱을 할 구간, 즉 행렬 개수)
        for(int k = 1; k < n; k++){
            //몇번째 행렬부터 시작
            for(int i = 0; i < n-k; i++){
                dp[i][i+k] = Integer.MAX_VALUE;

                //구간에 포함된 행렬 곱셈 시작
                //(구간을 두 개로 쪼개어 행렬 두 개의 곱셈으로 치환 -> j 기준으로 쪼갬)
                for(int j = i; j < i + k; j++){
                    int val = dp[i][j] + dp[j+1][i+k]
                            //A 행렬 i ~ j, B행렬 j ~ i + k
                            + (matrix[i][0] * matrix[j][1] * matrix[i+k][1]);

                    dp[i][i+k] = Math.min(dp[i][i+k], val);
                }
            }
        }

        System.out.println(dp[0][n-1]);
    }
}
