import java.io.*;
import java.util.*;

public class Main {
    static int m, n;
    static int[][] map;
    static boolean[][][] visited;
    static int sx, sy, sd;
    static int ex, ey, ed;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};


    static class Node{
        int x, y;
        int dir;
        int cnt;
        Node(int x, int y, int dir, int cnt){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        visited = new boolean[m][n][4];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;
        sd = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;
        ed = Integer.parseInt(st.nextToken()) - 1;


        System.out.println(bfs(sx, sy, sd));
    }

    public static int bfs(int sx, int sy, int sd){
        Queue<Node> q = new ArrayDeque<>();

        q.add(new Node(sx, sy, sd, 0));
        visited[sx][sy][sd] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == ex && cur.y == ey && cur.dir == ed){
                return cur.cnt;
            }


            boolean block = false;
            //1칸 이동
            int nx = cur.x + dx[cur.dir];
            int ny = cur.y + dy[cur.dir];

            if(!OOB(nx, ny) && !visited[nx][ny][cur.dir]){
                if(map[nx][ny] == 1) block = true;
                else{
                    q.add(new Node(nx, ny, cur.dir, cur.cnt + 1));
                    visited[nx][ny][cur.dir] = true;
                }
            }

            //2칸 이동
            nx = cur.x + 2 * dx[cur.dir];
            ny = cur.y + 2 * dy[cur.dir];
            if(!OOB(nx, ny) && !visited[nx][ny][cur.dir] && !block){
                if(map[nx][ny] == 1) block = true;
                else{
                    q.add(new Node(nx, ny, cur.dir, cur.cnt + 1));
                    visited[nx][ny][cur.dir] = true;
                }
            }

            //3칸 이동
            nx = cur.x + 3 * dx[cur.dir];
            ny = cur.y + 3 * dy[cur.dir];
            if(!OOB(nx, ny) && !visited[nx][ny][cur.dir] && !block){
                if(map[nx][ny] == 1) block = true;
                else{
                    q.add(new Node(nx, ny, cur.dir, cur.cnt + 1));
                    visited[nx][ny][cur.dir] = true;
                }
            }

            //오른쪽 회전
            int nd = -1;
            if(cur.dir == 0) nd = 2;
            else if(cur.dir == 1) nd = 3;
            else if(cur.dir == 2) nd = 1;
            else if(cur.dir == 3) nd = 0;
            if(!visited[cur.x][cur.y][nd]){
                q.add(new Node(cur.x, cur.y, nd, cur.cnt + 1));
                visited[cur.x][cur.y][nd] = true;
            }

            //왼쪽 회전
            if(cur.dir == 0) nd = 3;
            else if(cur.dir == 1) nd = 2;
            else if(cur.dir == 2) nd = 0;
            else if(cur.dir == 3) nd = 1;
            if(!visited[cur.x][cur.y][nd]){
                q.add(new Node(cur.x, cur.y, nd, cur.cnt + 1));
                visited[cur.x][cur.y][nd] = true;
            }
        }

        return -1;
    }

    public static boolean OOB(int x, int y){
        if(x < 0 || x >= m || y < 0 || y >= n) return true;
        return false;
    }
}
