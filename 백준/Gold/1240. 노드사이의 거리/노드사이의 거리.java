import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] dis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dis = new int[n][n];

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            dis[a][b] = d;
            dis[b][a] = d;
        }

        StringBuilder sb = new StringBuilder();
        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            boolean[] visited = new boolean[n];
            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.add(new int[]{start, 0});
            visited[start] = true;

            while(!q.isEmpty()){

                int[] cur = q.poll();

                if(cur[0] == end) {

                    sb.append(cur[1]).append('\n');
                    break;
                }

                for(int i = 0; i < n; i++){
                    if(dis[cur[0]][i] > 0 && !visited[i]){
                        q.add(new int[]{i, cur[1] + dis[cur[0]][i]});
                        visited[i] = true;
                    }
                }
            }

        }

        System.out.println(sb);
    }
}