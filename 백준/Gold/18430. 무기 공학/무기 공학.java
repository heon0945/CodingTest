import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int ans;
    static int map[][];
    static boolean visited[][];

    static int[][] boom; //부메랑에 포함된 칸

    static int[][] dx = {{0, 0, 1}, {-1, 0, 0}, {-1, 0, 0}, {0, 0, 1}};
    static int[][] dy = {{-1, 0, 0}, {0, 0, -1}, {0, 0, 1}, {1, 0, 0}};

    public static class Pair{
        int x, y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        boom = new int[12][3];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, -1);
        dfs(0, 0, 0);
        dfs(0, 0, 1);
        dfs(0, 0, 2);
        dfs(0, 0, 3);


        System.out.println(ans);
    }

    static void dfs(int cur, int strength, int shape){

        //종료 조건 : 마지막 칸인 경우
        if(cur == n * m){
            ans = Math.max(ans, strength);
            return;
        }

        //부메랑 만들지 않기
        if(shape == -1){
            dfs(cur + 1, strength, -1);
            dfs(cur + 1, strength, 0);
            dfs(cur + 1, strength, 1);
            dfs(cur + 1, strength, 2);
            dfs(cur + 1, strength, 3);
        }
        //부메랑 만들기
        else{

            int cx = cur / m;
            int cy = cur % m;
            if(!makingBoom(cx, cy, shape)) return;

            //부메랑 만들어지는 경우

            int add = 0;
            for(int i = 0; i < 3; i++){

                int nx = cx + dx[shape][i];
                int ny = cy + dy[shape][i];
                visited[nx][ny] = true;

                if(i == 1) add += map[nx][ny] * 2;
                else add += map[nx][ny];


            }

            dfs(cur + 1, strength + add, -1);
            dfs(cur + 1, strength + add, 0);
            dfs(cur + 1, strength + add, 1);
            dfs(cur + 1, strength + add, 2);
            dfs(cur + 1, strength + add, 3);

            for(int i = 0; i < 3; i++){

                int nx = cx + dx[shape][i];
                int ny = cy + dy[shape][i];
                visited[nx][ny] = false;
            }


        }




    }

    static boolean makingBoom(int cx, int cy, int shape){

        for(int i = 0; i < 3; i++){

            int nx = cx + dx[shape][i];
            int ny = cy + dy[shape][i];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m) return false;
            if(visited[nx][ny]) return false;
        }

        return true;
    }

}
