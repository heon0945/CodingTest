import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] heights;
    static boolean[][] visited;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        heights = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                heights[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j]) continue;
                if(heights[i][j] == 0) continue;

                if(bfs(i, j)) {
                    //System.out.println(i + " " + j);
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    public static boolean bfs(int x, int y){
        ArrayDeque<int[]> q = new ArrayDeque<>();

        int h = heights[x][y];
        boolean flag = true;

        q.add(new int[]{x, y});
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0; i < 8; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(heights[nx][ny] > h) {
                    flag = false;
                    continue;
                }

                if(heights[nx][ny] < h) continue;

                if(visited[nx][ny]) continue;

                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        return flag;

    }
}