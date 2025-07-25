import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int map[][];

    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};

    static class Point implements Comparable<Point>{
        int x, y, cnt;
        Point(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        public int compareTo(Point o){
            return this.cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs());

    }

    public static int bfs(){

        boolean[][] visited = new boolean[n][m];
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0, 0, 0));
        visited[0][0] = true;

        while(!pq.isEmpty()){

            Point cur = pq.poll();

            if(cur.x == n-1 && cur.y == m-1) return cur.cnt;

            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(visited[nx][ny]) continue;

                if(map[nx][ny] == 0){
                    pq.add(new Point(nx, ny, cur.cnt));
                    visited[nx][ny] = true;
                }
                else{
                    pq.add(new Point(nx, ny, cur.cnt + 1));
                    visited[nx][ny] = true;
                }
            }



        }

        return -1;
    }
}
