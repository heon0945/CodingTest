import java.io.*;
import java.util.*;

// 가중치(집하장 간 소요 시간)가 양수, 양방향
// 플로이드 워셜 알고리즘 or n번의 다익스트라 알고리즘 (모든 점을 출발점으로 해서)
// 플로이드 워셜 알고리즘 + 첫번째 노드를 기억

public class Main {

    static final int INF = 10000000;
    static int n, m;
    static int dist[][]; //i -> j 최소 거리 저장
    static int ans[][]; // i -> j 거쳐야 하는 첫번째 노드 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n][n];
        ans = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            // 양방향 통로라 둘 다 업데이트
            dist[start][end] = weight;
            dist[end][start] = weight;

            // 경유 없이 바로 갈 수 있는 경우
            ans[start][end] = end + 1;
            ans[end][start] = start + 1;
        }

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){

                    if(dist[i][k] + dist[k][j] < dist[i][j]){
                        // 경유해서 가는 최단 경로
                        dist[i][j] = dist[i][k] + dist[k][j];
                        // 새로운 최단 경로를 찾은 경우, 경유하는 경로의 첫번째 노드로 업데이트
                        ans[i][j] = ans[i][k];
                    }

                }
            }
        }


        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j) sb.append("- ");
                else sb.append(ans[i][j]).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}