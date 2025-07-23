import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int ans;
    static int map[][];
    static boolean visited[][];

    static int[][] dx = {{0, 0, 1}, {-1, 0, 0}, {-1, 0, 0}, {0, 0, 1}};
    static int[][] dy = {{-1, 0, 0}, {0, 0, -1}, {0, 0, 1}, {1, 0, 0}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);


        System.out.println(ans);
    }

    static void dfs(int cur, int strength){

        //종료 조건 : 마지막 칸인 경우
        if(cur == n * m){
            ans = Math.max(ans, strength);
            return;
        }

        //부메랑 만들기
        int cx = cur / m;
        int cy = cur % m;

        for(int shape = 0; shape < 4; shape++){

            if(!makingBoom(cx, cy, shape)) continue;


            int add = 0;
            
            for(int i = 0; i < 3; i++){

                int nx = cx + dx[shape][i];
                int ny = cy + dy[shape][i];

                visited[nx][ny] = true;

                if(i == 1) add += map[nx][ny] * 2;
                else add += map[nx][ny];

            }

            dfs(cur + 1, strength + add);

            for(int i = 0; i < 3; i++){

                int nx = cx + dx[shape][i];
                int ny = cy + dy[shape][i];
                visited[nx][ny] = false;
            }
        }

        dfs(cur + 1, strength);


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
