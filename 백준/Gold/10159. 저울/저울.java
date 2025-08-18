import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static boolean[][] bigger; // bigger[a][b] : a가 b보다 무겁다
    static boolean[][] smaller; // smaller[a][b] : a가 b보다 가볍다

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        bigger = new boolean[n+1][n+1];
        smaller = new boolean[n+1][n+1];

        StringTokenizer st;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a > b 관계 입력
            bigger[a][b] = true;   // a가 b보다 무겁다
            smaller[b][a] = true;  // b가 a보다 가볍다 (역방향)
        }

        // 플로이드 워셜 알고리즘
        // m : 경유지 역할
        for(int m = 1; m <= n; m++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    // 최단 거리 갱신 대신 경유하여 비교 가능한지 판단
                    // i > m 이고 m > j라면 i > j
                    if(bigger[i][m] && bigger[m][j]) bigger[i][j] = true;
                    // i < m 이고 m < j라면 i < j
                    if(smaller[i][m] && smaller[m][j]) smaller[i][j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){
            int unknown = 0;
            for(int j = 1; j <= n; j++){
                if(i == j) continue;

                // i와 j 사이 비교 불가한 경우
                if(!bigger[i][j] && !smaller[i][j]) unknown++;
            }

            sb.append(unknown).append('\n');
        }

        System.out.println(sb);
    }
}