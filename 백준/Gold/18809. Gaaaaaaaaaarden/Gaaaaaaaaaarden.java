import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int g, r;
    static int map[][];
    static boolean visited[];
    static int gpos[];
    static int rpos[];
    static int ans;

    public static class Pair{
        int x, y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        g = sc.nextInt();
        r = sc.nextInt();

        map = new int[n][m];
        visited = new boolean[n * m];
        gpos = new int[g];
        rpos = new int[r];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = sc.nextInt();
            }
        }

        gcombi(0, 0);

        System.out.println(ans);

    }

    static void gcombi(int cur, int cnt){

        if(cnt == g){
            rcombi(0, 0);
            return;
        }

        for(int i = cur; i < n * m; i++){
            if(visited[i]) continue;
            if(map[i / m][i % m] != 2) continue;

            visited[i] = true;
            gpos[cnt] = i;
            gcombi(i, cnt + 1);
            visited[i] = false;
        }
    }

    static void rcombi(int cur, int cnt){

        if(cnt == r){

            //배양액 위치 선정 완료
            flowers();
            return;
        }

        for(int i = cur; i < n * m; i++){
            if(visited[i]) continue;
            if(map[i / m][i % m] != 2) continue;

            visited[i] = true;
            rpos[cnt] = i;
            rcombi(i, cnt + 1);
            visited[i] = false;
        }
    }

    static void flowers(){
        Queue<Pair> q = new ArrayDeque<>();
        int tmp[][] = new int[n][m];
        int dx[] = {-1, 0, 1, 0};
        int dy[] = {0, -1, 0, 1};

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                tmp[i][j] = map[i][j];
            }
        }

        //초록 배양액
        for(int i = 0; i < g; i++){
            int x = gpos[i] / m;
            int y = gpos[i] % m;
            //System.out.println("Green : x : " + x + " y : " + y);
            tmp[x][y] = -10;
            q.add(new Pair(x, y));
        }

        //빨강 배양액
        for(int i = 0; i < r; i++){
            int x = rpos[i] / m;
            int y = rpos[i] % m;
            //System.out.println("Red : x : " + x + " y : " + y);
            tmp[x][y] = 10;
            q.add(new Pair(x, y));
        }

        int level = 0;
        int flowers = 0;

        while(!q.isEmpty()){
            int sz = q.size();
            level++;

            while(sz > 0){
                Pair cur = q.poll();

                sz--;

                if(tmp[cur.x][cur.y] == 3) continue;

                int color = tmp[cur.x][cur.y];

                for(int i = 0; i < 4; i++){

                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    if(tmp[nx][ny] == 0) continue;
                    if(tmp[nx][ny] == 3) continue;
                    if(color >= 10 && tmp[nx][ny] >= 10) continue;
                    if(color <= -10 && tmp[nx][ny] <= -10) continue;

                    //배양액 없는 칸
                    if(tmp[nx][ny] == 1 || tmp[nx][ny] == 2) {
                        if(color > 0)
                            tmp[nx][ny] = 10 + level;
                        else
                            tmp[nx][ny] = -10 - level;

                        q.add(new Pair(nx, ny));
                    }
                    //이미 다른 배양액 있는 칸
                    else{
                        if(10 + level + tmp[nx][ny] == 0
                        || -10 - level + tmp[nx][ny] == 0){
                            tmp[nx][ny] = 3;
                            flowers++;
                        }
                    }
                }

            }

        }

        ans = Math.max(flowers, ans);

    }


}