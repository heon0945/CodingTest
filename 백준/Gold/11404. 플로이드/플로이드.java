import java.io.*;
import java.util.*;

// 1. n x n 크기의 dist[][] 배열 : i -> j로 가는 최소 거리를 저장할 배열
// 2. m개의 버스 정보 바탕으로 dist 1차 업데이트
// 3. n x n x n 삼중 반복으로 k를 거쳐서 i -> k, k -> j로 가는 경우의 최소 거리 판단 dist 2차 업데이트
// 4. 결과 출력

public class Main {
    static final int INF = 100000000;
    static int n, m;
    static int dist[][];

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        StringTokenizer st;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            dist[start][end] = Math.min(dist[start][end], weight);
        }

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(dist[i][k] + dist[k][j]  < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(dist[i][j] == INF) sb.append(0).append(" ");
                else sb.append(dist[i][j]).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb);


    }
}
